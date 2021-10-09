package com.ae.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ae.beans.APIResponse;
import com.ae.service.LookupAPIService;

@RestController
@RequestMapping("/api/lookup")
@CrossOrigin(origins = "*")
public class LookupAPI {

	@Autowired
	LookupAPIService lookupAPIService;

	
	@GetMapping("/stations")
	public ResponseEntity<APIResponse> getAllStations() {
		return ResponseEntity.ok().body(lookupAPIService.findAllStations());
	}

	@GetMapping("/zones")
	public ResponseEntity<APIResponse> findAllZones() {
		return ResponseEntity.ok().body(lookupAPIService.findAllZones());
	}
	
	@GetMapping("/modes")
	public ResponseEntity<APIResponse> findAllModes() {
		return ResponseEntity.ok().body(lookupAPIService.findAllModes());
	}

	@GetMapping("/zoneForStations")
	public ResponseEntity<APIResponse> findAllStationZones() {
		return ResponseEntity.ok().body(lookupAPIService.findAllStationZones());
	}

	@GetMapping("/fares")
	public ResponseEntity<APIResponse> findAllPossibleFares() {
		return ResponseEntity.ok().body(lookupAPIService.findAllPossibleFares());
	}
}
