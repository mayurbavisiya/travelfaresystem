package com.ae.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NamedQuery;

@Entity(name = "train_fare_master")
@NamedQuery(name = "TrainFareLookup.findAllPossibleTrainFares", query = "SELECT f FROM train_fare_master f")
public class TrainFareLookup {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "ID")
	private Long id;

	@Column(name = "ENTRY_ZONE_ID")
	private Integer entryZoneId;

	@Column(name = "EXIT_ZONE_ID")
	private Integer exitZoneId;

	@Column(name = "FARE")
	private Double fare;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getEntryZoneId() {
		return entryZoneId;
	}

	public void setEntryZoneId(int entryZoneId) {
		this.entryZoneId = entryZoneId;
	}

	public int getExitZoneId() {
		return exitZoneId;
	}

	public void setExitZoneId(int exitZoneId) {
		this.exitZoneId = exitZoneId;
	}

	public Double getFare() {
		return fare;
	}

	public void setFare(Double fare) {
		this.fare = fare;
	}

}
