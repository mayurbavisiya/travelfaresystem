package com.ae.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ValidationException extends Exception {

	private String messageCode;
	private String messageDesc;

	public ValidationException(String msg) {
		super(msg);
		this.messageCode = ExceptionUtility.getCode(msg);
	}

	public ValidationException(String messageCode, String messageDesc) {
		super(messageDesc);
		this.messageCode = messageCode;
		this.messageDesc = messageDesc;
	}

	public String getMessageCode() {
		return messageCode;
	}

	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
	}

	public String getMessageDesc() {
		return messageDesc;
	}

	public void setMessageDesc(String messageDesc) {
		this.messageDesc = messageDesc;
	}

}
