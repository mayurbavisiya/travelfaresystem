package com.ae.beans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class APIResponse {

	private String responseCode;
	private String responseMsg;

	@JsonInclude(Include.NON_NULL)
	private UserDTO cardDetails;

	@JsonInclude(Include.NON_NULL)
	private List<StationsDTO> stations;

	@JsonInclude(Include.NON_NULL)
	private List<ModesDTO> modes;

	@JsonInclude(Include.NON_NULL)
	private List<ZonesDTO> zones;

	@JsonInclude(Include.NON_NULL)
	private List<StationZoneMappingDTO> stationZonesMapping;

	@JsonInclude(Include.NON_NULL)
	private List<FareDTO> fairs;

	@JsonInclude(Include.NON_NULL)
	private Double balance;

	@JsonInclude(Include.NON_NULL)
	private SwipeInResDTO swipeInResponse;

	@JsonInclude(Include.NON_NULL)
	SwipeOutResDTO swipeOutResponse;

	@JsonInclude(Include.NON_NULL)
	List<TripDTO> trips;

	public APIResponse(String responseCode, String responseMsg) {
		this.responseCode = responseCode;
		this.responseMsg = responseMsg;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public String getResponseMsg() {
		return responseMsg;
	}

	public List<StationsDTO> getStations() {
		return stations;
	}

	public List<ZonesDTO> getZones() {
		return zones;
	}

	public List<StationZoneMappingDTO> getStationZonesMapping() {
		return stationZonesMapping;
	}

	public List<FareDTO> getFairs() {
		return fairs;
	}

	public Double getBalance() {
		return balance;
	}

	public SwipeInResDTO getSwipeInResponse() {
		return swipeInResponse;
	}

	public SwipeOutResDTO getSwipeOutResponse() {
		return swipeOutResponse;
	}

	public List<TripDTO> getTrips() {
		return trips;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public void setResponseMsg(String responseMsg) {
		this.responseMsg = responseMsg;
	}

	public UserDTO getCardDetails() {
		return cardDetails;
	}

	public void setCardDetails(UserDTO cardDetails) {
		this.cardDetails = cardDetails;
	}

	public void setStations(List<StationsDTO> stations) {
		this.stations = stations;
	}

	public void setZones(List<ZonesDTO> zones) {
		this.zones = zones;
	}

	public void setStationZonesMapping(List<StationZoneMappingDTO> stationZonesMapping) {
		this.stationZonesMapping = stationZonesMapping;
	}

	public void setFairs(List<FareDTO> fairs) {
		this.fairs = fairs;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public void setSwipeInResponse(SwipeInResDTO swipeInResponse) {
		this.swipeInResponse = swipeInResponse;
	}

	public void setSwipeOutResponse(SwipeOutResDTO swipeOutResponse) {
		this.swipeOutResponse = swipeOutResponse;
	}

	public void setTrips(List<TripDTO> trips) {
		this.trips = trips;
	}

	public List<ModesDTO> getModes() {
		return modes;
	}

	public void setModes(List<ModesDTO> modes) {
		this.modes = modes;
	}

}
