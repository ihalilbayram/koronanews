package ibo.utils.parser;

import java.util.ArrayList;
import java.util.Objects;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;

import ibo.config.ApplicationData;
import ibo.exceptions.ParagraphRuleException;
import ibo.model.NewsKeywordModel;
import ibo.utils.textSplitter.SentenceSplitterImp;


public class NewsParser {
	
	private ApplicationData		appData;
	private ParagraphSplitter	paragraphSplitter;
	private SentenceSplitter	sentenceSplitter;
	private SentenceParser 		sentenceParser;
	private	ArrayList<Pattern>	sentencePatterns;
	
	private int maxNewsLength;
	

	@Autowired
	public NewsParser(	ApplicationData appData, 
						ParagraphSplitter paragraphSplitter,	
						SentenceSplitter sentenceSplitter, 
						SentenceParser sentenceParser) {
		
		this.appData 			= appData;
		this.paragraphSplitter	= paragraphSplitter;
		this.sentenceSplitter	= sentenceSplitter;
		this.sentenceParser 	= sentenceParser;
		this.maxNewsLength 		= appData.newsData.getMaxLength();
	}
	
	public NewsKeywordModel parse(String paragraph) {
		
		ParagraphModel 		paragraphModel 	= 	new ParagraphModel();
		ArrayList<String> 	sentences;
		
		paragraph = checkAndPrepare(paragraph);
		sentences = paragraphSplitter.getSentences(paragraph);
		
		sentences.stream()
					.map(sentenceSplitter::getWords)
					.map(sentenceParser::searchWordsAndGetKeyMap)
					.filter(Objects::nonNull)      					// not valid sentences throw exception however no info sentences returns null  
					.flatMap(map->map.entrySet().stream())
					.forEach(paragraphModel::putIntoMap);
		
		return paragraphModel.toNewsKeywordModel();		
	}
	
	
	
	
	
	
	private String checkAndPrepare( String paragraph ) {
		
		String newsStr = paragraph;
		
		if(newsStr.isBlank()) {
			
			throw new ParagraphRuleException("Empyt news!!!");	
			
		}else {
			newsStr = newsStr.replaceAll("\\s+", " ");
			newsStr = newsStr.trim();
		}
		
		if(newsStr.length() > this.maxNewsLength ) {
			
			throw new ParagraphRuleException("News length exceeds the maximum length!!!");
		}
		
		return newsStr;	
	}



}
