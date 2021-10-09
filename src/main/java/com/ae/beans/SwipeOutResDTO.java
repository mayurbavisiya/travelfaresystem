package com.ae.beans;

public class SwipeOutResDTO {

	private Long cardId;
	private String entryStationName;
	private String exitStationName;
	private String entryZoneName;
	private String exitZoneName;
	private Double fare;
	private Double avaialbleBalance;

	public Long getCardId() {
		return cardId;
	}

	public void setCardId(Long cardId) {
		this.cardId = cardId;
	}

	public String getEntryStationName() {
		return entryStationName;
	}

	public void setEntryStationName(String entryStationName) {
		this.entryStationName = entryStationName;
	}

	public String getExitStationName() {
		return exitStationName;
	}

	public void setExitStationName(String exitStationName) {
		this.exitStationName = exitStationName;
	}

	public String getEntryZoneName() {
		return entryZoneName;
	}

	public void setEntryZoneName(String entryZoneName) {
		this.entryZoneName = entryZoneName;
	}

	public String getExitZoneName() {
		return exitZoneName;
	}

	public void setExitZoneName(String exitZoneName) {
		this.exitZoneName = exitZoneName;
	}

	public Double getFare() {
		return fare;
	}

	public void setFare(Double fare) {
		this.fare = fare;
	}

	public Double getAvaialbleBalance() {
		return avaialbleBalance;
	}

	public void setAvaialbleBalance(Double avaialbleBalance) {
		this.avaialbleBalance = avaialbleBalance;
	}

}
