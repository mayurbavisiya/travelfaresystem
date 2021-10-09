package com.ae.beans;

public class TripDTO {

	private Long id;

	private Long cardId;
	private String entryStationName;
	private String exitStationName;
	private String mode;
	private Double fare;
	private String tripstartDate;
	private String tripEndDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCardId() {
		return cardId;
	}

	public void setCardId(Long cardId) {
		this.cardId = cardId;
	}

	public String getEntryStationName() {
		return entryStationName;
	}

	public String getExitStationName() {
		return exitStationName;
	}

	public String getMode() {
		return mode;
	}

	public void setEntryStationName(String entryStationName) {
		this.entryStationName = entryStationName;
	}

	public void setExitStationName(String exitStationName) {
		this.exitStationName = exitStationName;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public Double getFare() {
		return fare;
	}

	public void setFare(Double fare) {
		this.fare = fare;
	}

	public String getTripstartDate() {
		return tripstartDate;
	}

	public void setTripstartDate(String tripstartDate) {
		this.tripstartDate = tripstartDate;
	}

	public String getTripEndDate() {
		return tripEndDate;
	}

	public void setTripEndDate(String tripEndDate) {
		this.tripEndDate = tripEndDate;
	}

}
