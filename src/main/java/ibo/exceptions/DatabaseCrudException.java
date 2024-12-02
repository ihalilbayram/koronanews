package ibo.exceptions;

public class DatabaseCrudException extends DatabaseSqlException {

	public DatabaseCrudException(String message) {
		
		this(message, null, null, null);
	}

	public DatabaseCrudException(String message, Throwable cause) {
	
		this(message, cause, null, null);
	}

	public DatabaseCrudException(String message, Integer dbErrorCode, String dbErrorMessage) {
		
		this(message, null, dbErrorCode, dbErrorMessage);
		
	}

	public DatabaseCrudException(String message, Throwable cause, Integer dbErrorCode, String dbErrorMssage) {
		
		super(message, cause, dbErrorCode, dbErrorMssage);		
	}

	
	
}
