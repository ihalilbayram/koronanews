package ibo.exceptions;

public class DatabaseSqlException extends DatabaseException {

	public DatabaseSqlException(String message) {
		
		this(message, null, null, null);
	}

	public DatabaseSqlException(String message, Throwable cause) {
	
		this(message, cause, null, null);
	}

	public DatabaseSqlException(String message, Integer dbErrorCode, String dbErrorMessage) {
		
		this(message, null, dbErrorCode, dbErrorMessage);
		
	}

	public DatabaseSqlException(String message, Throwable cause, Integer dbErrorCode, String dbErrorMessage) {
		
		super(message, cause, dbErrorCode, dbErrorMessage);		
	}

	
}
