package com.ae.serviceimpl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ae.beans.APIResponse;
import com.ae.beans.SwipeInReqDTO;
import com.ae.beans.SwipeInResDTO;
import com.ae.beans.SwipeOutReqDTO;
import com.ae.beans.SwipeOutResDTO;
import com.ae.entity.BusFareLookup;
import com.ae.entity.Card;
import com.ae.entity.StationZoneMappingLookup;
import com.ae.entity.StationsLookup;
import com.ae.entity.Trip;
import com.ae.entity.ZonesLookup;
import com.ae.exception.ExceptionUtility;
import com.ae.exception.ValidationException;
import com.ae.repository.BusFareRepository;
import com.ae.repository.CardRepository;
import com.ae.repository.StationZoneMappingLookupRepository;
import com.ae.repository.StationsLookupRepository;
import com.ae.repository.TrainFareRepository;
import com.ae.repository.TravelModeRepository;
import com.ae.repository.TripRepository;
import com.ae.repository.ZonesLookupRepository;
import com.ae.service.TravelfareService;
import com.ae.util.Utility;

@Service
public class BuisnessAPIServiceImpl implements TravelfareService {
	private static final Logger loggerInfo = Logger.getLogger("travelFareSysInfo");

	final static double minimumBalanceRequired = Double.valueOf(Utility.getMasterDataProperty("maxFare"));
	static HashMap<Long, String> stationData;
	static HashMap<Integer, List<Integer>> stationZoneMappingData;
	static HashMap<Integer, String> stationZoneNameMappingData;
	static HashMap<Long, String> zonesData;
	static HashMap<Integer, String> travelModeData;

	static int fareArrSize = Integer.parseInt(Utility.getMasterDataProperty("totalZones")) + 1;
	static double[][] fareArr = new double[fareArrSize][fareArrSize];

	public static HashMap<Long, String> getStationData() {
		return stationData;
	}

	public static HashMap<Integer, List<Integer>> getStationZoneMappingData() {
		return stationZoneMappingData;
	}

	public static HashMap<Integer, String> getStationZoneNameMappingData() {
		return stationZoneNameMappingData;
	}

	public static HashMap<Long, String> getZonesData() {
		return zonesData;
	}

	public static HashMap<Integer, String> getTravelModeData() {
		return travelModeData;
	}

	public static void setStationData(HashMap<Long, String> stationData) {
		BuisnessAPIServiceImpl.stationData = stationData;
	}

	public static void setStationZoneMappingData(HashMap<Integer, List<Integer>> stationZoneMappingData) {
		BuisnessAPIServiceImpl.stationZoneMappingData = stationZoneMappingData;
	}

	public static void setStationZoneNameMappingData(HashMap<Integer, String> stationZoneNameMappingData) {
		BuisnessAPIServiceImpl.stationZoneNameMappingData = stationZoneNameMappingData;
	}

	public static void setZonesData(HashMap<Long, String> zonesData) {
		BuisnessAPIServiceImpl.zonesData = zonesData;
	}

	public static void setTravelModeData(HashMap<Integer, String> travelModeData) {
		BuisnessAPIServiceImpl.travelModeData = travelModeData;
	}

	@PostConstruct
	public void initialize() {
		stationData = new HashMap<>();
		stationZoneMappingData = new HashMap<>();
		zonesData = new HashMap<>();
		stationZoneNameMappingData = new HashMap<>();
		travelModeData = new HashMap<>();

		List<StationsLookup> stations = stationsLookupRepository.findAllStations();
		stations.forEach(x -> {
			stationData.put(x.getId(), x.getName());
		});

		List<StationZoneMappingLookup> stationsZonesMapping = stationZoneMappingLookupRepository.findAllStationZones();
		stationsZonesMapping.forEach(x -> {
			stationZoneMappingData.computeIfAbsent(x.getStationId(), k -> new ArrayList<>()).add(x.getZoneId());
		});

		List<ZonesLookup> zones = zonesLookupRepository.findAllZones();
		zones.forEach(x -> {
			zonesData.put(x.getId(), x.getDescription());
		});

		for (Map.Entry<Integer, List<Integer>> enSet : stationZoneMappingData.entrySet()) {
			StringBuilder sb = new StringBuilder();
			List<Integer> zonesForStation = enSet.getValue();
			zonesForStation.forEach(x -> {
				sb.append(zonesData.get(Long.valueOf(x))).append(", ");
			});
			stationZoneNameMappingData.put(enSet.getKey(), sb.toString().substring(0, sb.length() - 2));
		}

		travelModeRepository.getAllTravelModes().forEach(x -> {
			travelModeData.put(x.getId().intValue(), x.getDesc());
		});

		trainFareRepository.findAllPossibleTrainFares().forEach(x -> {
			fareArr[x.getEntryZoneId()][x.getExitZoneId()] = x.getFare();
			fareArr[x.getExitZoneId()][x.getEntryZoneId()] = x.getFare();
		});
	}

	@Autowired
	ZonesLookupRepository zonesLookupRepository;

	@Autowired
	StationsLookupRepository stationsLookupRepository;

	@Autowired
	CardRepository cardRepository;

	@Autowired
	TripRepository tripRepository;

	@Autowired
	StationZoneMappingLookupRepository stationZoneMappingLookupRepository;

	@Autowired
	BusFareRepository busFareRepository;

	@Autowired
	TrainFareRepository trainFareRepository;

	@Autowired
	TravelModeRepository travelModeRepository;

	@Override
	public APIResponse swipeIn(SwipeInReqDTO dto) throws ValidationException {

		// Check is there any previous trip is open on this cardId if Yes you
		// can't swipe in again
		Trip tripEntity = tripRepository.getOpenTripByCardId(dto.getCardId());
		if (tripEntity != null)
			throw new ValidationException("cardIsalreadySwipedInCode", "cardIsalreadySwipedInDesc");

		Double availableBalance = this.checkBalance(dto.getCardId()).getBalance();
		// If its less than required minimum balance then throw error
		if (availableBalance < minimumBalanceRequired)
			throw new ValidationException("noMinimumBalanceCode", "noMinimumBalanceDesc");
		// Save new trip for the card
		Trip trip = new Trip();
		trip.setCardId(dto.getCardId());
		trip.setEntryStationId(dto.getEntryStation());
		trip.setMode(dto.getMode());
		trip.setTripstartDate(new Date());
		trip.setIsFinished(0);
		tripRepository.save(trip);

		// Create output DTO
		SwipeInResDTO resDTO = new SwipeInResDTO();
		resDTO.setAvailableBalance(availableBalance);
		resDTO.setEntryStation(stationData.get(Long.valueOf(dto.getEntryStation())));

		resDTO.setZone(stationZoneNameMappingData.get(dto.getEntryStation()));
		APIResponse response = new APIResponse(ExceptionUtility.getCode("successCode"),
				ExceptionUtility.getCode("successMsg"));
		response.setSwipeInResponse(resDTO);
		return response;
	}

	@Override
	public APIResponse swipeOut(SwipeOutReqDTO dto) throws ValidationException {

		Card card = cardRepository.findById(dto.getCardId())
				.orElseThrow(() -> new ValidationException("cardNotFoundCode", "cardNotFoundDesc"));

		Trip trip = tripRepository.getOpenTripByCardId(dto.getCardId());
		if (trip == null)
			throw new ValidationException("cardIsalreadySwipedOutCode", "cardIsalreadySwipedOutDesc");

		List<StationZoneMappingLookup> mappingList = stationZoneMappingLookupRepository.findAllStationZones();

		int entryStation = trip.getEntryStationId(), exitStation = dto.getExitStation(), mode = trip.getMode();
		Set<Integer> entryZones = new HashSet<>(), exitZones = new HashSet<>();

		for (StationZoneMappingLookup en : mappingList) {
			if (en.getStationId() == exitStation)
				exitZones.add(en.getZoneId());
			if (en.getStationId() == entryStation)
				entryZones.add(en.getZoneId());
		}

		Double tripfare = this.calculateTripFair(entryZones, exitZones, mode);
		// Close trip
		trip.setExitStationId(exitStation);
		trip.setFare(tripfare);
		trip.setTripEndDate(new Date());
		trip.setIsFinished(1);
		trip.setFinishedBy(1);
		tripRepository.save(trip);
		loggerInfo.info("trip saved with tripId:" + trip.getId());
		// update balance in card table for cardId
		card = updateCardBalance(card, tripfare);
		loggerInfo.info("balance updated with cardId: " + card.getId());
		SwipeOutResDTO resDTO = new SwipeOutResDTO();
		resDTO.setAvaialbleBalance(card.getBalance());
		resDTO.setCardId(card.getId());
		BigDecimal bd = new BigDecimal(tripfare).setScale(2, RoundingMode.HALF_DOWN);
		resDTO.setFare(bd.doubleValue());
		resDTO.setEntryStationName(
				stationData.containsKey(Long.valueOf(entryStation)) ? stationData.get(Long.valueOf(entryStation)) : "");
		resDTO.setExitStationName(
				stationData.containsKey(Long.valueOf(exitStation)) ? stationData.get(Long.valueOf(exitStation)) : "");
		resDTO.setEntryZoneName(stationZoneNameMappingData.containsKey(entryStation)
				? stationZoneNameMappingData.get(entryStation) : "");
		resDTO.setExitZoneName(
				stationZoneNameMappingData.containsKey(exitStation) ? stationZoneNameMappingData.get(exitStation) : "");

		APIResponse response = new APIResponse(ExceptionUtility.getCode("successCode"),
				ExceptionUtility.getCode("successMsg")); 
		response.setSwipeOutResponse(resDTO);
		return response;
	}

	private Card updateCardBalance(Card card, Double tripfare) throws ValidationException {
		card.setBalance(card.getBalance() - tripfare);
		cardRepository.save(card);
		return card;
	}

	private Double calculateTripFair(Set<Integer> entryZones, Set<Integer> exitZones, int mode) {
		// Mode 1: Bus, 2: Train
		Double tripFair = 0.0;
		Set<Integer> zonesOfJourney = new HashSet<>();
		zonesOfJourney.addAll(entryZones);
		zonesOfJourney.addAll(exitZones);
		if (mode == 1) {
			BusFareLookup fares = busFareRepository.findAllPossibleBusFares();
			tripFair = fares.getFare();
		} else {
			if (zonesOfJourney.size() == Integer.parseInt(Utility.getMasterDataProperty("totalZones"))
					|| (zonesOfJourney.contains(Integer.parseInt(Utility.getMasterDataProperty("cityStartZone")))
							&& (zonesOfJourney
									.contains(Integer.parseInt(Utility.getMasterDataProperty("cityEndZone")))))) {
				return Double.valueOf(Utility.getMasterDataProperty("maxFare"));
			} else {
				Double price = Double.MAX_VALUE;
				for (int enZone : entryZones) {
					for (int exZone : exitZones) {
						price = Math.min(price, Math.min(fareArr[enZone][exZone], fareArr[exZone][enZone]));
					}
				}
				tripFair = price;
			}
		}
		return tripFair;
	}

	@Override
	public APIResponse checkBalance(Long cardId) throws ValidationException {
		// Get the available balance for input cardId
		Card card = cardRepository.findById(cardId)
				.orElseThrow(() -> new ValidationException("cardNotFoundCode", "cardNotFoundDesc"));

		double availableBalance = card.getBalance() != null ? card.getBalance() : 0.0;
		APIResponse response = new APIResponse(ExceptionUtility.getCode("successCode"),
				ExceptionUtility.getCode("successMsg"));
		response.setBalance(availableBalance);
		return response;
	}

}
