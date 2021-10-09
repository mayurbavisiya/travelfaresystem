package com.ae.serviceimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ae.beans.APIResponse;
import com.ae.beans.TripDTO;
import com.ae.beans.UserDTO;
import com.ae.entity.Card;
import com.ae.entity.Trip;
import com.ae.entity.User;
import com.ae.exception.ExceptionUtility;
import com.ae.repository.CardRepository;
import com.ae.repository.StationsLookupRepository;
import com.ae.repository.TripRepository;
import com.ae.repository.UserRepository;
import com.ae.service.UserService;
import com.ae.util.Utility;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	CardRepository cardRepository;

	@Autowired
	StationsLookupRepository stationsLookupRepository;

	@Autowired
	TripRepository tripRepository;

	@Autowired
	BuisnessAPIServiceImpl buisnessAPIServiceImpl;

	@Override
	public APIResponse createUser(UserDTO dto) {

		User user = new User();
		user.setMobileNumber(dto.getMobileNumber());
		user.setIdentificationNumber(dto.getIdentificationNumber());
		user.setName(dto.getName());
		user.setCreatedDate(new Date());

		Card card = new Card();
		card.setBalance(30.00);
		card.setCreatedDate(new Date());

		card.setUser(user);
		cardRepository.save(card);

		BeanUtils.copyProperties(user, dto);
		dto.setCardId(card.getId());
		dto.setUserId(card.getUser().getId());
		APIResponse apiResponse = new APIResponse(ExceptionUtility.getCode("successCode"),
				ExceptionUtility.getCode("successMsg"));
		apiResponse.setCardDetails(dto);
		return apiResponse;
	}

	@Override
	public APIResponse travelHistory(Long cardId) {
		List<Trip> trips = tripRepository.getAllTripsByCardId(cardId);
		List<TripDTO> tripsDTO = new ArrayList<>();
		Map<Integer, String> travelModes = BuisnessAPIServiceImpl.getTravelModeData();
		Map<Long, String> stationsData = BuisnessAPIServiceImpl.getStationData();
		
		trips.forEach(x -> {
			TripDTO dto = new TripDTO();
			BeanUtils.copyProperties(x, dto);
			dto.setFare(dto.getFare() != null ? dto.getFare() : 0.0);
			dto.setTripEndDate(x.getTripEndDate() != null ? Utility.getStringFromDate(x.getTripEndDate(), true) : "");
			dto.setTripstartDate(x.getTripstartDate() != null ? Utility.getStringFromDate(x.getTripstartDate(), true) : "");
			dto.setMode(travelModes.get(x.getMode()));
			dto.setEntryStationName(x.getEntryStationId() != null ? stationsData.get(Long.valueOf(x.getEntryStationId())) : "");
			dto.setExitStationName(x.getExitStationId() != null ? stationsData.get(Long.valueOf(x.getExitStationId())): "");
			tripsDTO.add(dto);
		});
		APIResponse apiResponse = new APIResponse(ExceptionUtility.getCode("successCode"),
				ExceptionUtility.getCode("successMsg"));
		apiResponse.setTrips(tripsDTO);
		return apiResponse;
	}

}
