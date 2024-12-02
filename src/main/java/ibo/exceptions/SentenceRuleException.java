package ibo.exceptions;

public class SentenceRuleException extends MalformedInputException {

	public SentenceRuleException(String message) {
		
		this(message, null);		
	}

	public SentenceRuleException(String message, Throwable cause) {
		
		super(message, cause);		
	}
	
	
}
