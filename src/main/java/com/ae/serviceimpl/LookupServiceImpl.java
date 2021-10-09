package com.ae.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ae.entity.TrainFareLookup;
import com.ae.entity.StationZoneMappingLookup;
import com.ae.entity.StationsLookup;
import com.ae.entity.ZonesLookup;
import com.ae.repository.TrainFareRepository;
import com.ae.repository.StationZoneMappingLookupRepository;
import com.ae.repository.StationsLookupRepository;
import com.ae.repository.ZonesLookupRepository;
import com.ae.service.LookupService;

@Service
public class LookupServiceImpl implements LookupService {

	@Autowired
	StationsLookupRepository stationsLookupRepository;

	@Autowired
	ZonesLookupRepository zonesLookupRepository;

	@Autowired
	StationZoneMappingLookupRepository stationZoneMappingLookupRepository;

	@Autowired
	TrainFareRepository fairRepository;

	@Override
	public List<StationsLookup> findAllStations() {
		return stationsLookupRepository.findAllStations();
	}

	@Override
	public List<ZonesLookup> findAllZones() {
		return zonesLookupRepository.findAllZones();
	}

	@Override
	public List<StationZoneMappingLookup> findAllStationZones() {
		return stationZoneMappingLookupRepository.findAllStationZones();
	}

	@Override
	public List<TrainFareLookup> findAllPossibleFares() {
		return fairRepository.findAllPossibleTrainFares();
	}

}
