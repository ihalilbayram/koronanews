package ibo.exceptions;

public class ParagraphRuleException extends MalformedInputException {

	public ParagraphRuleException(String message) {
		
		this(message, null);		
	}

	public ParagraphRuleException(String message, Throwable cause) {
		
		super(message, cause);		
	}
	
	
}
