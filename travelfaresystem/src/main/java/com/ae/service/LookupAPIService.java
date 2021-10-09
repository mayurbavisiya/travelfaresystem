package com.ae.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ae.beans.APIResponse;


@Service
@Transactional
public interface LookupAPIService {

	APIResponse findAllStations();

	APIResponse findAllZones();

	APIResponse findAllStationZones();

	APIResponse findAllPossibleFares();

	APIResponse findAllModes();
}
