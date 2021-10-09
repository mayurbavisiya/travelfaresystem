package com.ae.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@RestController
public class APIExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(APIExceptionHandler.class);

	@ExceptionHandler(value = { IllegalArgumentException.class, IllegalStateException.class })
	protected ResponseEntity<ErrorDetails> handleConflict(RuntimeException ex, WebRequest request) {
		logger.error("exception Occurred,", ex);
		ErrorDetails errorDetails = new ErrorDetails(ExceptionUtility.getCode(ex.getMessage()), ex.getMessage());
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = { ValidationException.class })
	public final ResponseEntity<ErrorDetails> handleValidationException(ValidationException ex, WebRequest request) {
		logger.error("exception Occurred,", ex);
		ErrorDetails errorDetails = new ErrorDetails(ExceptionUtility.getCode(ex.getMessageCode()),
				ExceptionUtility.getCode(ex.getMessageDesc()));
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.OK);
	}

	@ExceptionHandler(value = { DataIntegrityViolationException.class })
	public final ResponseEntity<ErrorDetails> handleHibernateException(DataIntegrityViolationException sqlE,
			WebRequest request) {
		logger.error("exception Occurred,", sqlE);
		ErrorDetails errorDetails = new ErrorDetails("0", "Server Error");
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.OK);
	}

	@ExceptionHandler(value = { Exception.class })
	ResponseEntity<ErrorDetails> handleBadRequest(Exception ex, WebRequest request) {
		logger.error("exception Occurred,", ex);
		ErrorDetails errorDetails = new ErrorDetails(ExceptionUtility.getCode(ex.getMessage()), ex.getMessage());
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
