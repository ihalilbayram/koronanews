package ibo.utils.searchEngine;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ibo.config.ApplicationData;

public class DateSearchEngine {
	
	ApplicationData appData;
	ArrayList<Pattern> patterns = new ArrayList<>();
	
	public DateSearchEngine(ApplicationData appData) {
		
		this.appData = appData;
		ArrayList<String> patternList = appData.newsData.getDatePatternsStr();
		setDatePatterns(patternList);
	}



	public LocalDate search(String word) {

		LocalDate localDate = null;				
		Matcher matcher;
		
		for(Pattern pattern: this.patterns) {
			
			matcher = pattern.matcher(word);
			if(matcher.find()) {

				int day 	= Integer.parseInt(matcher.group("day"));
		        int month 	= Integer.parseInt(matcher.group("month"));
		        int year 	= Integer.parseInt(matcher.group("year"));
		        
		        if(year < 100) {
		        	if(year>70) {
		        		year += 1900;
		        	}else{
		        		year += 2000;
		        	}
		        }
		        
		        
		        String sprtr1=matcher.group("sprtr1");
		        String sprtr2=matcher.group("sprtr2");
				
		        if( sprtr1 != null && sprtr2 != null && sprtr1.equals(sprtr2) ) {
		        
		        	// still there may not be date last check try to create LocalDate with the matched groups
		        	try {
						
						localDate = LocalDate.of(year, month, day);
						return localDate;
						
					} catch (Exception e) {

						return null;
					}

		        }		
			}
		}
		return localDate;
	}
	
	
	private void setDatePatterns(ArrayList<String> patternList) {
		
//		patternList.forEach( patternStr-> {
//								this.patterns.add(Pattern.compile(patternStr));
//							});
		
		//temorarily use default pattern
		this.patterns.add(Pattern.compile("^(?<year>(19|2[0-9])[0-9]{2})(?<sprtr1>[\\.\\-\\/])(?<month>0?[1-9]|1[012])(?<sprtr2>[\\.\\-\\/])(?<day>0?[1-9]|[12][0-9]|3[01])$"));		
		this.patterns.add(Pattern.compile("^(?<day>0?[1-9]|[12][0-9]|3[01])(?<sprtr1>[\\.\\-\\/])(?<month>0?[1-9]|1[012])(?<sprtr2>[\\.\\-\\/])(?<year>(19|2[0-9])[0-9]{2})$"));
		this.patterns.add(Pattern.compile("^(?<day>0?[1-9]|[12][0-9]|3[01])(?<sprtr1>[\\.\\-\\/])(?<month>0?[1-9]|1[012])(?<sprtr2>[\\.\\-\\/])(?<year>([70-99]|[00-27]))$"));
			

	}
	
	
		
	

	}	
