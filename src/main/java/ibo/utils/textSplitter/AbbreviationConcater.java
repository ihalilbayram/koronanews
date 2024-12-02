package ibo.utils.textSplitter;

import java.util.ArrayList;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ibo.config.ApplicationData;
import ibo.utils.textSplitter.config.ConfigData;

public class AbbreviationConcater {
	
	private Set<String>		abbreviationSet;
	private Pattern 		pattern;
	
	
	public AbbreviationConcater(ConfigData configData){
		
		this.abbreviationSet	= 	configData.getAbbreviationSet();
		pattern 				=	Pattern.compile("\\b(\\S*)$");   // last word of sentence
	}

	public ArrayList<String> concat(ArrayList<String> splittedSentences){
		
		ArrayList<String> sentences = new ArrayList<String>();
		
		String last = splittedSentences.stream().reduce((a,b)->{
																if(isEndWithAbbreviation(a)) {
																	return a.concat(" ").concat(b);
																}else {
																	if(!a.isBlank()) {
																		sentences.add(a);
																}
																return b;
														}
												}).orElse("");	
		if(!last.isBlank()) {
			sentences.add(last);
		}
		return sentences;
	}
	
	
	
	
	
	public boolean isEndWithAbbreviation(String sentence){
		
		Matcher matcher = pattern.matcher(sentence);
		if(matcher.find()) {
			
			 return this.abbreviationSet.contains( matcher.group());
			
		}else {
			// Log to unresolved end of sentence
			return false;
		}
	}
}
