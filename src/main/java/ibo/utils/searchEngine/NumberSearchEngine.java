package ibo.utils.searchEngine;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberSearchEngine {
	
	private Pattern intPattern = Pattern.compile("[0-9]{1,5}");
	
	public Integer search(String word) {
		
		Matcher matcher = intPattern.matcher(word);
		if(matcher.matches()) {
			return Integer.parseInt(word);
		}
			
		return null;		
	}

}
