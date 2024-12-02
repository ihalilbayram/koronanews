package ibo.exceptions;

import org.springframework.http.HttpStatus;

public class ConfigurationException extends BaseException{
	

	public ConfigurationException(String message) {
		
		this(message, null);
	}

	public ConfigurationException(String message, Throwable cause) {
		
		super(message, cause);
		
		this.setErrorCode(400);
		this.setHttpStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE);
		this.setSeverity(1);
		this.setLogged(false);
	}

}
