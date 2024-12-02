package ibo.config;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.convert.Delimiter;
import org.springframework.context.annotation.Lazy;

import jakarta.annotation.PostConstruct;
import ibo.exceptions.ConfigurationPropertyFileException;

public class ApplicationData {

	
	@Value("${application.mode}")
	private String mode;
	
	public ApplicationData.CommonData		commonData;
	public ApplicationData.MongoServerData	mongoServerData;
	public ApplicationData.NewsData			newsData;

	public ApplicationData(	ApplicationData.CommonData 		commonData, 
		 					ApplicationData.MongoServerData	mongoServerData,
		 					ApplicationData.NewsData		newsData) {
		
		this.commonData 		= commonData;
		this.mongoServerData 	= mongoServerData;
		this.newsData			= newsData;	
		
	}
	
	
	
	public void init( ){
		
		if(mode.equals("dev")) {
			this.logProperties();
		}
	}
	
	
	public static class CommonData{
		
		@Value("${application.locale:tr-TR}")
		private String localeStr;
		private Locale locale;
		
		
	
		public void init() {
			
			this.locale = Locale.forLanguageTag(this.localeStr);
		}
		
		public Locale getLocale() {
			return locale;
		}
		
	}
	
	
	
	
	public static class MongoServerData{		
		
		@Value("${spring.data.mongo.url}")
		private String url;
		
		@Value("${spring.data.mongo.database}")
		private String database;
		
//		private String	username	=	prop.databaseServer_username;
//		private String	password	=	prop.databaseServer_password;
//		private String	port		=	prop.databaseServer_port;
		
		public void init() {
			
		}
		
		public String getUrl() {
			return url;
		}
		public String getDatabase() {
			return database;
		}
	}
	
	public static class NewsData{
		
		@Value("${application.news.maxLength}")
		private int maxLength;

		@Value("${application.news.endOfSentencePattern}")
		private String endOfSentencePattern;
				  
		@Value("${application.news.keywordList.isCaseSensitive}")
		private boolean isKeywordListCaseSensitive;

		@Value("${application.news.sentence.isSentenceCountWithoutKeyword:true}")
		private boolean isSentenceCountWithoutKeyword;
                 
		@Value("${application.news.sentence.isSentenceKeywordWithoutCount}")
		private boolean isSentenceKeywordWithoutCount;

		@Value("${application.news.cityName.isCaseSensitive}")
		private boolean isCityNameCaseSensitive;
		
		@Value("${application.news.locale:tr-TR}")
		private String localeStr;
		private Locale locale;
		
		@Value("#'${application.news.datePatternsStr:}'.split(';')}")		
		private ArrayList<String> datePatternsStr;
		
		@Value("${application.news.keywordList:}")
		private ArrayList<String> keywordList;

		public void init() {
				
			this.locale = Locale.forLanguageTag(this.localeStr);			
		}
		
		
		public ArrayList<String> getDatePatternsStr() {
			return datePatternsStr;
		}

		public int getMaxLength() {
			return maxLength;
		}

		public String getEndOfSentencePattern() {
			return endOfSentencePattern;
		}

		public boolean isKeywordListCaseSensitive() {
			return isKeywordListCaseSensitive;
		}

		public boolean isSentenceCountWithoutKeyword() {
			return isSentenceCountWithoutKeyword;
		}

		public boolean isSentenceKeywordWithoutCount() {
			return isSentenceKeywordWithoutCount;
		}

		public boolean isCityNameCaseSensitive() {
			return isCityNameCaseSensitive;
		}

		public Locale getLocale() {
			return locale;
		}


		public ArrayList<String> getKeywordList() {
			return keywordList;
		}


		
	}
		
		
		
		/**
		 * 
		 * this method tests whether the application.properties fetched/read as expected and invoked only if mode=dev (parameter)
		// it can be further improved to check parameter values converted as expected types via Relection
		 */
		private void logProperties(){
			
			String propertyName  ="";
			Object propertyValue ="";
			
			System.out.println("printing application properties");
			
			var fields = new ArrayList<Field>();
			
			System.out.println("Printing Application Data properties");
			for (var field : ApplicationData.class.getDeclaredFields()) {
				
				propertyName = 	field.getName();
				try {
					propertyValue = field.get(this);
				}catch(Exception e) {
					System.out.println("Error while reading value of property : " + propertyName);
				}
				
			    System.out.println(propertyName + ": " + propertyValue );
			}
			
			
			System.out.println("Printing Application.Common Data properties");
			for (var field : ApplicationData.CommonData.class.getDeclaredFields()) {
				
				propertyName = 	field.getName();
				try {
					propertyValue = field.get(this.commonData);
				}catch(Exception e) {
					System.out.println("Error while reading value of property : " + propertyName);
				}
				
			    System.out.println(propertyName + ": " + propertyValue );
			}
			
			System.out.println("Printing Application.News Data properties");
			for (var field : ApplicationData.NewsData.class.getDeclaredFields()) {
				
				propertyName = 	field.getName();
				try {
					propertyValue = field.get(this.newsData);
				}catch(Exception e) {
					System.out.println("Error while reading value of property : " + propertyName);
				}
				
			    System.out.println(propertyName + ": " + propertyValue );
			}
			
			System.out.println("Printing Application.Mongo Server Data properties");
			for (var field : ApplicationData.MongoServerData.class.getDeclaredFields()) {
				
				propertyName = 	field.getName();
				try {
					propertyValue = field.get(this.mongoServerData);
				}catch(Exception e) {
					System.out.println("Error while reading value of property : " + propertyName);
				}
				
			    System.out.println(propertyName + ": " + propertyValue );
			}
			
		}
	

}
