package ibo.utils.searchEngine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;

import ibo.config.ApplicationData;

public class KeywordSearchEngine {
	
	ApplicationData appData;
	private Locale 	locale;
	private boolean isCaseSensitive;
	
	private HashMap<String,String> keywordMap;
	
	@Autowired
	public KeywordSearchEngine(ApplicationData appData){
		
		this.locale 		= appData.newsData.getLocale();
		this.isCaseSensitive= appData.newsData.isKeywordListCaseSensitive();
		
		ArrayList<String> keywordList = appData.newsData.getKeywordList();
		setKeywordMap(keywordList);
		
	}
	
	
	public String search(String searchText) {
		
		if(!isCaseSensitive) {
			searchText = searchText.toLowerCase(locale); 
		}
		
		return keywordMap.get(searchText);
	}


	
	private void setKeywordMap(ArrayList<String> keywordList) {

		this.keywordMap = new HashMap<String,String>();
		
		keywordList.stream()
				.filter(word-> !( word== null || word.isBlank()))
				.forEach(word -> {
			
					String[] synonims = word.split("\\|");
					String key = synonims[0];
						
					Arrays.asList(synonims).stream()
										.filter(value-> !( value== null || value.isBlank()))
										.forEach(value->{	
			
											if(this.isCaseSensitive) {
												this.keywordMap.put(value, key);
											}else {				
												this.keywordMap.put(value.toLowerCase(locale), key);   // map key converted to lowercase because while searching searchword also will become lowercase
											}
										});
					});
	}

	
	
	
	

	public Locale getLocale() {
		return locale;
	}


	public boolean isCaseSensitive() {
		return isCaseSensitive;
	}


	public HashMap<String, String> getKeywordMap() {
		return keywordMap;
	}
	
	

}
