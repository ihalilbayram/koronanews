package ibo.utils.textSplitter.config;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Locale;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Value;

import ibo.exceptions.ConfigurationPropertyFileException;
import jakarta.annotation.PostConstruct;

public class ConfigData {

	
	@Value("${paragraphSplitter.locale:tr-TR}")
	private String localeStr;
	private Locale locale;
			  
	@Value("${paragraphSplitter.abbreviations:}")
	private ArrayList<String> abbreviations;
	
	@Value("${paragraphSplitter.subSentencePatternsStr:}")
	private ArrayList<String> subSentencePatternsStr;
	
	private HashSet<String> 	abbreviationSet;
	private ArrayList<Pattern> 	subSentencePatterns;
	
	
	public ConfigData() {
		
	}

	
	public void init() {
		
		locale = Locale.forLanguageTag("tr-TR");
		setSubSentencePatterns();
		setAbbreviationSet();
	}
	

	
	private void setAbbreviationSet(){
		
		this.abbreviationSet = new HashSet<String>();
		
		this.abbreviations.forEach( abbr-> this.abbreviationSet.add(abbr));
		
	}
	
	private void setSubSentencePatterns(){
		
		// checks whether patternString valid or not if not valid Exception thrown
		try {
			this.subSentencePatterns = new ArrayList<Pattern>();
			
			this.subSentencePatternsStr.forEach(patternStr-> {
			
							Pattern pattern = Pattern.compile(patternStr, Pattern.UNICODE_CHARACTER_CLASS);
							// check other business rule for validation
							this.subSentencePatterns.add(pattern);
						}
					);
		}catch(Exception e) {
			
			throw new ConfigurationPropertyFileException("User data for subSentencePatternStr is not valid");
		}
	}
	

	
	
	public String getLocaleStr() {
		return localeStr;
	}

	public void setLocaleStr(String localeStr) {
		this.localeStr = localeStr;
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}


	public void setSubSentencePatternsStr(ArrayList<String> subSentencePatternsStr) {
		this.subSentencePatternsStr = subSentencePatternsStr;
	}

	public ArrayList<Pattern> getSubSentencePatterns() {
		return subSentencePatterns;
	}

	public void setSubSentencePatterns(ArrayList<Pattern> subSentencePatterns) {
		this.subSentencePatterns = subSentencePatterns;
	}

	public void setAbbreviations(ArrayList<String> abbreviations) {
		this.abbreviations = abbreviations;
	}

	public void setAbbreviationSet(HashSet<String> abbreviationSet) {
		this.abbreviationSet = abbreviationSet;
	}

	public ArrayList<String> getAbbreviations() {
		return abbreviations;
	}

	
	public HashSet<String> getAbbreviationSet() {
		return abbreviationSet;
	}
	

}
