package ibo.config;

import java.util.HashMap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import ibo.utils.parser.NewsParser;
import ibo.utils.parser.ParagraphSplitter;
import ibo.utils.parser.SentenceParser;
import ibo.utils.parser.SentenceSplitter;
import ibo.utils.searchEngine.CitySearchEngine;
import ibo.utils.searchEngine.DateSearchEngine;
import ibo.utils.searchEngine.KeywordSearchEngine;
import ibo.utils.searchEngine.NumberSearchEngine;


@Configuration
public class ApplicationNewsModuleConfig {
	
	@Bean
	public CitySearchEngine getCitySearchEngine(ApplicationData appData) {

		return new CitySearchEngine(appData);
	}

	@Bean
	public KeywordSearchEngine getKeywordSearchEngine(ApplicationData appData) {

		return new KeywordSearchEngine(appData);
	}
	
	@Bean
	public DateSearchEngine getDateSearchEngine(ApplicationData appData) {

		return new DateSearchEngine(appData);
	}
	
	@Bean
	public NumberSearchEngine getNumberSearchEngine() {

		return new NumberSearchEngine();
	}
	
	@Bean
	public NewsParser getNewsParser( ApplicationData appData, ParagraphSplitter ps, SentenceSplitter ss,  SentenceParser sp) {
		
		return new NewsParser( appData,  ps, ss, sp);
	}
	
	@Bean
	public SentenceParser getSentenceParser(CitySearchEngine cse, DateSearchEngine dse, KeywordSearchEngine kse, NumberSearchEngine nse) {
		
		return new SentenceParser(cse, dse, kse, nse);
	}
			
	
	
}
