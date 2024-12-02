package ibo.utils.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;


import ibo.utils.searchEngine.CitySearchEngine;
import ibo.utils.searchEngine.DateSearchEngine;
import ibo.utils.searchEngine.KeywordSearchEngine;
import ibo.utils.searchEngine.NumberSearchEngine;

public class SentenceParser {
	
	
	private CitySearchEngine 	citySearchEngine;
	private DateSearchEngine 	dateSearchEngine;
	private KeywordSearchEngine keywordSearchEngine;
	private NumberSearchEngine 	numberSearchEngine;
	private SentenceSplitter 	sentenceSplitter;

	@Autowired
	public SentenceParser( 
					CitySearchEngine 	citySearchEngine, 
					DateSearchEngine 	dateSearchEngine, 
					KeywordSearchEngine keywordSearchEngine,
					NumberSearchEngine 	numberSearchEngine
					) {	
		
		this.citySearchEngine	=	citySearchEngine;
		this.dateSearchEngine 	=	dateSearchEngine;
		this.keywordSearchEngine=	keywordSearchEngine;
		this.numberSearchEngine =	numberSearchEngine;
		
	}
	
	public HashMap<String, Object> searchWordsAndGetKeyMap(ArrayList<String> words) {
		
		SentenceModel model = new SentenceModel();
		
		words.stream()	.filter(word-> !( word== null || word.isBlank()))
						.filter(word-> !model.setKeyword( keywordSearchEngine.search(word)))  	// true means model accepted keyword so skip to further search
						.filter(word-> !model.setCount( numberSearchEngine.search(word)))
						.filter(word-> !model.setCity( 	citySearchEngine.search(word)))
						.filter(word-> !model.setDate( 	dateSearchEngine.search(word)))
						.forEach(word->{});
			
		return model.getKeyMap();
		
	}

}
