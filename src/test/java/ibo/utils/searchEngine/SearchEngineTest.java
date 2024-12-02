package ibo.utils.searchEngine;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.stream.Stream;

import org.junit.jupiter.api.*;

import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SearchEngineTest {

	@Autowired
	KeywordSearchEngine keywordSearchEngine;
	@Autowired
	DateSearchEngine 	dateSearchEngine;
	@Autowired
	NumberSearchEngine 	numberSearchEngine;
	@Autowired
	CitySearchEngine 	citySearchEngine;


	@ParameterizedTest(name = "{0} => {1}")
	@MethodSource("keywordArgProvider")
	@DisplayName("Testing Keyword Engine ${keywordSearchEngine}")
	void searchKeywordEngineTest(String input, String expectedValue) {
		
		String output = keywordSearchEngine.search(input);
		String errorMessage= testOutputAndExpectedValue(input, output, expectedValue);
        assertEquals(0, errorMessage.length(), errorMessage);
	}
	
	
	
	@ParameterizedTest(name = "{0} => {1}")	
	@MethodSource("ibo.utils.searchEngine.SearchEngineDataProvider#dateArgProvider")
	@DisplayName("Testing Date Engine")
	void searchDateEngineTest(String input, LocalDate expectedValue) {		
		
		LocalDate output = dateSearchEngine.search(input);		
		String errorMessage= testOutputAndExpectedValue(input, output, expectedValue);		
		assertTrue( errorMessage.length()==0, errorMessage);
	}
	
	
	
	@ParameterizedTest(name = "{0} => {1}")
	@MethodSource("ibo.utils.searchEngine.SearchEngineDataProvider#numberArgProvider")
	@DisplayName("Testing Number Engine")
	void searchNumberEngineTest(String input, Integer expectedValue) {
		
		Integer output = numberSearchEngine.search(input);
		String errorMessage= testOutputAndExpectedValue(input, output, expectedValue);
		assertTrue( errorMessage.length()==0, errorMessage);
		
	}
	
	
	@ParameterizedTest(name = "{0} => {1}")
	@MethodSource("cityArgProvider")
	@DisplayName("Testing City Engine")
	void citySearchEngineTest(String input, String expectedValue) {

		String output = citySearchEngine.search(input);	
		String errorMessage= testOutputAndExpectedValue(input, output, expectedValue);
		assertTrue( errorMessage.length()==0, errorMessage);
	}


	private String testOutputAndExpectedValue(Object input, Object output, Object expectedValue) {
		
	
		String errorMessage = "";

		if (output == null) {
			if (expectedValue != null) {
				errorMessage = "why " + input + " is not NOT detected !";
			}
		} else {
			if (expectedValue == null) {
				errorMessage = "why " + input + " is detected!";
			}
			if (!output.equals(expectedValue)) {
				errorMessage = "output and expected values are not equal (" + input + " converted as " + output +")";
			}
		}
		return errorMessage;
	}
	
	
	
	
	
	
	
	//need to Autowiring with applicationconfigdata, so should be defined here as non-static, 
	//if defined inside of a different outside class must be static that can not autowired
	private Stream<Arguments> cityArgProvider() {
		
		Locale 	locale 			= citySearchEngine.getLocale();
		Boolean	isCaseSensitive	= citySearchEngine.isCaseSensitive();		
		HashMap<String,String> cityMap = citySearchEngine.getCityMap();
		
		ArrayList<Arguments> cityArguments = new ArrayList<Arguments>(); 
		
		//adding all key values in cityMap AS IS, must be found whether uppercase or lowercase  
		cityMap.entrySet().stream().forEach(entry->{ cityArguments.add(Arguments.of(entry.getKey(),entry.getValue())); });
		
		
		if(isCaseSensitive) {
			//key of cityMap capitalized, so I am uppercasing/lowercasing my test keys data and expecting not found in SensitiveCase
			cityMap.entrySet().stream().forEach(entry->{
				
								cityArguments.add(Arguments.of(entry.getKey().toLowerCase(locale), null));
								cityArguments.add(Arguments.of(entry.getKey().toUpperCase(locale), null));
						});
		}else {
			//key of cityMap lowercased, so I am uppercasing my test keys data and expecting found in NONSensitivecase
			cityMap.entrySet().stream().forEach(entry->{
								cityArguments.add(Arguments.of(entry.getKey().toUpperCase(locale), entry.getValue()));
				});
		}	
		
		cityMap.entrySet().stream().forEach(entry->{
			
			String key = entry.getKey();
			String value = entry.getValue();

			String flippedStr = flipStr(key, locale);

			// the characters of key completely converted to from uppercase to lowercase or vice versa,
			// INcasesensitive shoudn't be found, NONcaseSensitive should be found

			if (isCaseSensitive) {

				cityArguments.add(Arguments.of(flippedStr, null));
			} else {
				cityArguments.add(Arguments.of(flippedStr, value));
			}			
		});
		
		// some dummy value sure to be not found
		cityArguments.add(Arguments.of("xyslkd", null));
		cityArguments.add(Arguments.of("kjlkjl", null));
		cityArguments.add(Arguments.of("Ndsfff", null));
		
		return cityArguments.stream();
	}
	
	
	
	
	private Stream<Arguments> keywordArgProvider() {		
		 
		Locale 	locale 			= keywordSearchEngine.getLocale();
		Boolean	isCaseSensitive	= keywordSearchEngine.isCaseSensitive();		
		HashMap<String,String> keywordMap = keywordSearchEngine.getKeywordMap();
		
		ArrayList<Arguments> keywordArguments = new ArrayList<Arguments>(); 
		
		//adding all key values in keyword AS IS, must be found whether caseSensitive or not  
		keywordMap.entrySet().stream().forEach(entry->{ keywordArguments.add(Arguments.of(entry.getKey(), entry.getValue())); });

		keywordMap.entrySet().stream().forEach( entry->{
			
			String key 	= 	entry.getKey();
			String value=	entry.getValue();
			
			String flippedStr = flipStr(key, locale);
			
			// characters of key flipped from uppercase to lowercase or vice versa, INcasesensitive shoudn't be found, NONcaseSensitive should be found
			if(isCaseSensitive) {				
				keywordArguments.add(Arguments.of(flippedStr, null));
			}else{
				keywordArguments.add(Arguments.of(flippedStr, value));
			}		
		});
		
		// some dummy values, expected to be not found
		keywordArguments.add(Arguments.of("xyslkd", null));
		keywordArguments.add(Arguments.of("kjlkjl", null));
		keywordArguments.add(Arguments.of("Ndsfff", null));	
		
		return keywordArguments.stream();
	}
	
	
	private String flipStr(String str, Locale locale){
		
		StringBuffer  flippedStr = new StringBuffer();
	    String        flippedStrChar;
	    
	    for(char c: str.toCharArray()) {
					
	    	if (Character.isLowerCase(c)) {
						
	    		flippedStrChar = (c+"").toUpperCase(locale);    // Character upperCase/lowerCase does not work with locale
					flippedStr.append(flippedStrChar);       	
	    	}else {
	    		flippedStrChar = (c+"").toLowerCase(locale);
	    		flippedStr.append(flippedStrChar);
	    	}
		}
	    
	    return flippedStr.toString();		
	}

}
