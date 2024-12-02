package ibo.exceptions;

import org.springframework.http.HttpStatus;

@SuppressWarnings("serial")
public class DatabaseException extends BaseException {

	protected Integer	dbErrorCode;
	protected String	dbErrorMssage;
	
	public DatabaseException(String message) {
		
		this(message, null, null, null);
	}

	public DatabaseException(String message, Throwable cause) {
		
		this(message, cause, null, null);
	
	}
	
	public DatabaseException(String message, Integer dbErrorCode, String dbErrorMessage) {
		
		this(message, null, dbErrorCode, dbErrorMessage);
	}
	
	public DatabaseException(String message, Throwable cause, Integer dbErrorCode, String dbErrorMssage) {
		
		super(message, cause);
		
		this.dbErrorCode 	= 	dbErrorCode;
		this.dbErrorMssage 	=	dbErrorMssage;
		
		this.setErrorCode(601);
		this.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		this.setSeverity(1);
		this.setLogged(false);
	}

	
	


	public int getDbErrorCode() {
		return dbErrorCode;
	}

	public void setDbErrorCode(int dbErrorCode) {
		this.dbErrorCode = dbErrorCode;
	}

	public String getDbErrorMssage() {
		return dbErrorMssage;
	}

	public void setDbErrorMssage(String dbErrorMssage) {
		this.dbErrorMssage = dbErrorMssage;
	}
	
	
	
}
