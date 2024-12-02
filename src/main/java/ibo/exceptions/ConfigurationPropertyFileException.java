package ibo.exceptions;

import org.springframework.http.HttpStatus;

public class ConfigurationPropertyFileException extends ConfigurationException {
	
	public ConfigurationPropertyFileException(String message) {
		
		this(message, null);		
	}

	public ConfigurationPropertyFileException(String message, Throwable cause) {
		
		super(message, cause);		
	}
	
	

}
