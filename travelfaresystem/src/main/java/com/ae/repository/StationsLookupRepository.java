package com.ae.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ae.entity.StationsLookup;

public interface StationsLookupRepository extends JpaRepository<StationsLookup, Long> {

	List<StationsLookup> findAllStations();

}
