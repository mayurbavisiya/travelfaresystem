package com.ae.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ae.entity.TrainFareLookup;

@Repository
public interface TrainFareRepository extends JpaRepository<TrainFareLookup, Long> {
	List<TrainFareLookup> findAllPossibleTrainFares();
	
//	TrainFareLookup 
}
