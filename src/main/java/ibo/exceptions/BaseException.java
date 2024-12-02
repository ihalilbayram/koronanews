package ibo.exceptions;

import org.springframework.http.HttpStatus;

public class BaseException extends RuntimeException {
	
	protected int				errorCode;
	protected String 			message;
	protected int 				severity;
	protected HttpStatus		httpStatus;
	protected boolean			isLogged;
	protected Object			attachedObject;	
	
	
	
	public BaseException(String message) {
		
		this(message, null);
	}
	
	public BaseException(String message, Throwable cause) {
		
		super(message, cause);
		this.message 	= message;		
	}




	/**
	 * log method is the top DEFAULT log, subclasses can implements its own log behaviour overriding this method
	 * @return
	 */
	public Boolean log() {
		
		// simply LOG information
		return true;
	}
	
	
	
	public String getMessage() {
		return this.message;
	}
	
	public int getErrorCode() {
		return errorCode;
	}

	public int getSeverity() {
		return severity;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}


	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public void setSeverity(int severity) {
		this.severity = severity;
	}


	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}


	public boolean isLogged() {
		return isLogged;
	}


	public void setLogged(boolean isLogged) {
		this.isLogged = isLogged;
	}

	
	public Object getAttachedObject() {
		return attachedObject;
	}

	public void setAttachedObject(Object attachedObject) {
		this.attachedObject = attachedObject;
	}



}
