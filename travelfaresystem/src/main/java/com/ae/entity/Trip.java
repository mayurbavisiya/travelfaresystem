package com.ae.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity(name = "trip")
public class Trip {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "ID")
	private Long id;

	@Column(name = "CARD_ID")
	private Long cardId;

	@Column(name = "ENTRY_STATION_ID")
	private Integer entryStationId;

	@Column(name = "EXIT_STATION_ID")
	private Integer exitStationId;

	@Column(name = "MODE")
	private Integer mode;

	@Column(name = "FARE")
	private Double fare;

	@Column(name = "TRIP_START_DATE")
	private Date tripstartDate;

	@Column(name = "TRIP_END_DATE")
	private Date tripEndDate;

	@Column(name = "IS_FINISHED")
	private Integer isFinished;

	@Column(name = "FINISHED_BY")
	private Integer finishedBy;

	public Long getId() {
		return id;
	}

	public Long getCardId() {
		return cardId;
	}

	public Integer getEntryStationId() {
		return entryStationId;
	}

	public Integer getExitStationId() {
		return exitStationId;
	}

	public Integer getMode() {
		return mode;
	}

	public Double getFare() {
		return fare;
	}

	public Date getTripstartDate() {
		return tripstartDate;
	}

	public Date getTripEndDate() {
		return tripEndDate;
	}

	public Integer getIsFinished() {
		return isFinished;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setCardId(Long cardId) {
		this.cardId = cardId;
	}

	public void setEntryStationId(Integer entryStationId) {
		this.entryStationId = entryStationId;
	}

	public void setExitStationId(Integer exitStationId) {
		this.exitStationId = exitStationId;
	}

	public void setMode(Integer mode) {
		this.mode = mode;
	}

	public void setFare(Double fare) {
		this.fare = fare;
	}

	public void setTripstartDate(Date tripstartDate) {
		this.tripstartDate = tripstartDate;
	}

	public void setTripEndDate(Date tripEndDate) {
		this.tripEndDate = tripEndDate;
	}

	public void setIsFinished(Integer isFinished) {
		this.isFinished = isFinished;
	}

	public Integer getFinishedBy() {
		return finishedBy;
	}

	public void setFinishedBy(Integer finishedBy) {
		this.finishedBy = finishedBy;
	}

}
