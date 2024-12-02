package ibo.utils.parser;

import java.time.LocalDate;
import java.util.HashMap;


import ibo.exceptions.SentenceRuleException;


public class SentenceModel {
		
		private LocalDate 	date;
		private String		city;
		private String		keyword;	
		private Integer		count;

		private HashMap<String,Object> keywordMap = new HashMap<String,Object>();
		
		public HashMap<String, Object> getKeyMap(){
			
			if(!validate()) {
				
				return null;
				
			}else {
				
				putIntoMap("date", this.date);
				putIntoMap("city", this.city);
				putIntoMap(this.keyword, this.count);
			}
			
			return this.keywordMap;
		}
		
		public void putIntoMap(String key, Object value) {
			
			if(key != null && value != null) {
				
				keywordMap.put(key, value);
			}	
		}
		
		
		
		private boolean validate() {
			
			return (this.date != null || this.city != null || ( keyword != null && count != null   ));
		}
		
		
		
		
		
		public boolean setDate(LocalDate date) {
			
			if(checkExistence(this.date, date, "Multi date founded in the sentence")) {
				this.date = date;
				return true;
			}
			return false;
		}
		
		public boolean setCity(String city) {
			
			if (this.checkExistence(this.city, city, "Multi city founded in the sentence")) {
				this.city=city;
				return true;
			}
			return false;
		}

		public boolean setKeyword(String keyword) {
			
			if ( this.checkExistence(this.keyword, keyword, "Multi keyword founded in the sentence")) {
				this.keyword = keyword;
				return true;
			}
			return false;
		}

		public boolean setCount(Integer count) {
			
			if( this.checkExistence(this.count, count, "Multi count founded in the sentence")) {
				this.count = count;
				return true;		
			};
			return false;		
		}
		
		
		private <T> boolean checkExistence(T variable, T value, String errorMessage) {
			
			if(value != null) {
				
				if(variable == null) {
					
					return true;
					
				}else if( !variable.equals(value)) {
					
					throw new SentenceRuleException(errorMessage);
				}
			}
			
			return false;
		}

		

		public LocalDate getDate() {
			return date;
		}
		
		public String getCity() {
			return city;
		}
		
		public String getKeyword() {
			return keyword;
		}
		public Integer getCount() {
			return count;
		}


}
