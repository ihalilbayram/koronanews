package ibo.exceptions;

import java.util.Random;

import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;

import com.fasterxml.jackson.annotation.JsonInclude;


@JsonInclude(JsonInclude.Include.NON_NULL) 
public class ExceptionResponseObject {

	// do not put stack trace and cause here it will send to client all your code details ( jackson/jaxb automatically convert and send )
	//private Throwable	cause;

	private String 		message;
	private Integer		errorCode;
	private HttpStatus	httpStatus;
	private Object		attachedObject;
	
	
	
	
	public ExceptionResponseObject(BaseException baseException) {
		
		this.message		=	baseException.getMessage();
		this.errorCode		=	baseException.getErrorCode();
		this.httpStatus		=	baseException.getHttpStatus();
		
		if(baseException.isLogged) {
			
			baseException.log();
		}
	}


	public ExceptionResponseObject(DatabaseException databaseException) {

		this((BaseException) databaseException);
		this.attachedObject = databaseException.getAttachedObject();
	}

	

	
	
	
	
	public ExceptionResponseObject(HttpRequestMethodNotSupportedException rmnse) {
		
		this.message		=	rmnse.getMessage();
//		this.httpStatus		=	rmnse.
//		this.errorCode		=	baseException.getErrorCode();
//		this.httpStatus		=	baseException.getHttpStatus();
//		this.attachedObject = 	baseException.getAttachedObject();
	}
	
	
	
	
	public ExceptionResponseObject(Exception exception) {
		
		this.message	=	"An unknown error occured "  + "(" + this.randomString() + ")";		
		this.httpStatus = 	HttpStatus.INTERNAL_SERVER_ERROR;
		
		// you can define further LOG etc... processes using details in the exception obj, 
		// and random string can be used by client feedback to you  
	}
	
	
	
	

	
	
	
	public String toString() {
		
		return this.message + "( errorCode : " + this.errorCode + " ) ";
		
	}

	public String getMessage() {
		return message;
	}



	public Integer getErrorCode() {
		return errorCode;
	}



	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	
	
	
	public Object getAttachedObject() {
		return attachedObject;
	}



	private String randomString() {
		
	    int leftLimit = 48; 	// numeral '0'
	    int rightLimit = 122; 	// letter 'z'
	    int targetStringLength = 10;
	    Random random = new Random();

	    String generatedString = random.ints(leftLimit, rightLimit + 1)
	      .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
	      .limit(targetStringLength)
	      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
	      .toString();

	      return generatedString;
	}



}
