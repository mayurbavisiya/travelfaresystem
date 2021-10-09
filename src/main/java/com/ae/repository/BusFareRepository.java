package com.ae.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ae.entity.BusFareLookup;

@Repository
public interface BusFareRepository extends JpaRepository<BusFareLookup, Long> {
	BusFareLookup findAllPossibleBusFares();
}
