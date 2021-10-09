package com.ae.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NamedQuery;

@Entity(name = "station_zone_mapping_master")
@NamedQuery(name = "StationZoneMappingLookup.findAllStationZones", query = "SELECT s FROM station_zone_mapping_master s")
// @NamedQuery(name = "StationZoneMappingLookup.findStationZone", query =
// "SELECT s FROM station_zone_mapping_master s where s.stationId=:stationId ")
public class StationZoneMappingLookup {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "ID")
	private Long id;

	@Column(name = "STATION_ID")
	private Integer stationId;

	@Column(name = "ZONE_ID")
	private Integer zoneId;

	public Long getId() {
		return id;
	}

	public Integer getStationId() {
		return stationId;
	}

	public Integer getZoneId() {
		return zoneId;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setStationId(Integer stationId) {
		this.stationId = stationId;
	}

	public void setZoneId(Integer zoneId) {
		this.zoneId = zoneId;
	}

}
