package com.ae.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ae.entity.TravelModeLookup;

@Repository
public interface TravelModeRepository extends JpaRepository<TravelModeLookup, Long> {
	List<TravelModeLookup> getAllTravelModes();
}
