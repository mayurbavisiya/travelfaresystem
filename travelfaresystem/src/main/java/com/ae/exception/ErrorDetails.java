package com.ae.exception;

public class ErrorDetails {
	private String responseDesc;
	private String responseCode;

	public ErrorDetails(String responseCode, String responseDesc) {
		super();
		this.responseDesc = responseDesc;
		this.responseCode = responseCode;
	}

	public String getResponseDesc() {
		return responseDesc;
	}

	public void setResponseDesc(String responseDesc) {
		this.responseDesc = responseDesc;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
}
