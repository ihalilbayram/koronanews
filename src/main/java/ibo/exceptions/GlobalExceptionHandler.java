package ibo.exceptions;

import java.net.http.HttpHeaders;
import java.util.ArrayList;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	
	public GlobalExceptionHandler() {	}
	
	
	@ExceptionHandler(value= {MalformedInputException.class, ConfigurationException.class})
	public ResponseEntity<Object> handleCustomException( BaseException baseException){
		
		ExceptionResponseObject responseObj = new ExceptionResponseObject(baseException);
		
	    return new ResponseEntity<Object>(responseObj, responseObj.getHttpStatus());	    		
	}
	
	
	
	@ExceptionHandler(value= {DatabaseException.class})
	public ResponseEntity<Object> handleCustomException( DatabaseException databaseException){		
		
		ExceptionResponseObject responseObj = new ExceptionResponseObject(databaseException);		
		
	    return new ResponseEntity<Object>(responseObj, responseObj.getHttpStatus());		
	}
	
	
	
	@ExceptionHandler(value= {HttpRequestMethodNotSupportedException.class})
	public ResponseEntity<Object> handleException( HttpRequestMethodNotSupportedException httpRequestMethodNotSupportedException){		 
		
		ExceptionResponseObject responseObj = new ExceptionResponseObject(httpRequestMethodNotSupportedException);
		
		return new ResponseEntity<Object>(responseObj, responseObj.getHttpStatus());	    
	}
	
	
	@ExceptionHandler(value= {Exception.class})
	public ResponseEntity<Object> handleException( Exception exception){
		 
		ExceptionResponseObject responseObj = new ExceptionResponseObject(exception);
		
		
	    return new ResponseEntity<Object>(responseObj, responseObj.getHttpStatus());
	}
	
}
