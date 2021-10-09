package com.ae.beans;

public class SwipeInReqDTO {

	private Long cardId;
	private int EntryStation;
	private int mode;

	public Long getCardId() {
		return cardId;
	}

	public void setCardId(Long cardId) {
		this.cardId = cardId;
	}

	public int getEntryStation() {
		return EntryStation;
	}

	public void setEntryStation(int entryStation) {
		EntryStation = entryStation;
	}

	public int getMode() {
		return mode;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}

}
