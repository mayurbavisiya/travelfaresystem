package com.ae.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ae.beans.APIResponse;
import com.ae.beans.FareDTO;
import com.ae.beans.ModesDTO;
import com.ae.beans.StationZoneMappingDTO;
import com.ae.beans.StationsDTO;
import com.ae.beans.ZonesDTO;
import com.ae.exception.ExceptionUtility;
import com.ae.repository.StationZoneMappingLookupRepository;
import com.ae.repository.StationsLookupRepository;
import com.ae.repository.TrainFareRepository;
import com.ae.repository.TravelModeRepository;
import com.ae.repository.ZonesLookupRepository;
import com.ae.service.LookupAPIService;

@Service
public class LookupAPIServiceImpl implements LookupAPIService {

	@Autowired
	StationsLookupRepository stationsLookupRepository;

	@Autowired
	ZonesLookupRepository zonesLookupRepository;

	@Autowired
	StationZoneMappingLookupRepository stationZoneMappingLookupRepository;

	@Autowired
	TrainFareRepository fairRepository;

	@Autowired
	TravelModeRepository travelModeRepository;

	@Override
	public APIResponse findAllStations() {
		List<StationsDTO> stations = stationsLookupRepository.findAllStations().stream().map(x -> {
			StationsDTO dto = new StationsDTO();
			BeanUtils.copyProperties(x, dto);
			return dto;
		}).collect(Collectors.toList());
		APIResponse response = new APIResponse(ExceptionUtility.getCode("successCode"),
				ExceptionUtility.getCode("successMsg"));
		response.setStations(stations);
		return response;
	}

	@Override
	public APIResponse findAllZones() {
		List<ZonesDTO> zones = zonesLookupRepository.findAllZones().stream().map(x -> {
			ZonesDTO dto = new ZonesDTO();
			BeanUtils.copyProperties(x, dto);
			return dto;
		}).collect(Collectors.toList());
		APIResponse response = new APIResponse(ExceptionUtility.getCode("successCode"),
				ExceptionUtility.getCode("successMsg"));
		response.setZones(zones);
		return response;
	}

	@Override
	public APIResponse findAllModes() {
		List<ModesDTO> modes = travelModeRepository.getAllTravelModes().stream().map(x -> {
			ModesDTO dto = new ModesDTO();
			BeanUtils.copyProperties(x, dto);
			dto.setName(x.getDesc());
			return dto;
		}).collect(Collectors.toList());
		APIResponse response = new APIResponse(ExceptionUtility.getCode("successCode"),
				ExceptionUtility.getCode("successMsg"));
		response.setModes(modes);
		return response;
	}

	@Override
	public APIResponse findAllStationZones() {
		HashMap<Integer, String> stationZoneNameMappingData = BuisnessAPIServiceImpl.stationZoneNameMappingData;
		List<StationZoneMappingDTO> mapping = stationsLookupRepository.findAllStations().stream().map(x -> {
			StationZoneMappingDTO dto = new StationZoneMappingDTO();
			dto.setStationId(x.getId().intValue());
			dto.setZone(stationZoneNameMappingData.get(x.getId().intValue()));
			return dto;
		}).collect(Collectors.toList());

		APIResponse response = new APIResponse(ExceptionUtility.getCode("successCode"),
				ExceptionUtility.getCode("successMsg"));
		response.setStationZonesMapping(mapping);
		return response;
	}

	@Override
	public APIResponse findAllPossibleFares() {
		HashMap<Long, String> zonesData = BuisnessAPIServiceImpl.zonesData;
		List<FareDTO> fares = fairRepository.findAllPossibleTrainFares().stream().map(x -> {
			FareDTO dto = new FareDTO();
			dto.setEntryZone(zonesData.get(Long.valueOf(x.getEntryZoneId())));
			dto.setExitZone(zonesData.get(Long.valueOf(x.getExitZoneId())));
			dto.setFair(x.getFare());
			return dto;
		}).collect(Collectors.toList());
		APIResponse response = new APIResponse(ExceptionUtility.getCode("successCode"),
				ExceptionUtility.getCode("successMsg"));
		response.setFairs(fares);
		return response;
	}

}
