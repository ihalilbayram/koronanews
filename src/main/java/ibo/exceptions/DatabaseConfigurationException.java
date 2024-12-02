package ibo.exceptions;

public class DatabaseConfigurationException extends DatabaseException {

	
	public DatabaseConfigurationException(String message) {
		
		this(message, null, null, null);
	}

	public DatabaseConfigurationException(String message, Throwable cause) {
	
		this(message, cause, null, null);
	}

	public DatabaseConfigurationException(String message, Integer dbErrorCode, String dbErrorMessage) {
		
		this(message, null, dbErrorCode, dbErrorMessage);
		
	}

	public DatabaseConfigurationException(String message, Throwable cause, Integer dbErrorCode, String dbErrorMssage) {
		
		super(message, cause, dbErrorCode, dbErrorMssage);		
	}
}
