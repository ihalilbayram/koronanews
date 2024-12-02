package ibo.exceptions;

public class DuplicateRecordException extends DatabaseSqlException {

	public DuplicateRecordException(String message){
		
		this(message, null, null, null);
	}
	
	
	public DuplicateRecordException(String message, Throwable cause, Integer dbErrorCode, String dbErrorMessage) {
		
		super(message, cause, dbErrorCode, dbErrorMessage);
	}
	
	public DuplicateRecordException attachObject(Object attachedObject) {
		
		this.attachedObject = attachedObject;
		return this;
	}

}
