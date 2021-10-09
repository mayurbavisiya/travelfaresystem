package com.ae.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ae.entity.TrainFareLookup;
import com.ae.entity.StationZoneMappingLookup;
import com.ae.entity.StationsLookup;
import com.ae.entity.ZonesLookup;

@Service
@Transactional
public interface LookupService {

	List<StationsLookup> findAllStations();

	List<ZonesLookup> findAllZones();

	List<StationZoneMappingLookup> findAllStationZones();

	List<TrainFareLookup> findAllPossibleFares();

}
