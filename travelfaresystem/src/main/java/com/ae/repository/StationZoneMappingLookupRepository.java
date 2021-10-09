package com.ae.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ae.entity.StationZoneMappingLookup;

public interface StationZoneMappingLookupRepository extends JpaRepository<StationZoneMappingLookup, Long> {
	List<StationZoneMappingLookup> findAllStationZones();
}
