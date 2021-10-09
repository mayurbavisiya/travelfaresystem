package com.ae.beans;

public class FareDTO {

	private String entryZone;
	private String exitZone;
	private Double fair;

	public String getEntryZone() {
		return entryZone;
	}

	public String getExitZone() {
		return exitZone;
	}

	public void setEntryZone(String entryZone) {
		this.entryZone = entryZone;
	}

	public void setExitZone(String exitZone) {
		this.exitZone = exitZone;
	}

	public Double getFair() {
		return fair;
	}

	public void setFair(Double fair) {
		this.fair = fair;
	}
}
