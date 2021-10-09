package com.ae.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ae.entity.ZonesLookup;

@Repository
public interface ZonesLookupRepository extends JpaRepository<ZonesLookup, Long> {
	List<ZonesLookup> findAllZones();
}
