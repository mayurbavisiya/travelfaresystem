package com.ae.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ae.entity.Trip;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {

	@Query(value = "SELECT * FROM trip where card_id=:cardId and is_finished = 0", nativeQuery = true)
	Trip getOpenTripByCardId(@Param("cardId") Long cardId);

	@Query(value = "SELECT * FROM trip where card_id=:cardId", nativeQuery = true)
	List<Trip> getAllTripsByCardId(@Param("cardId") Long cardId);

	@Procedure(value = "SWIPED_OUT_TRIPS")
	void swipedOutTrips();
}
