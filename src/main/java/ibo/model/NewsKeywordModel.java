package ibo.model;

import java.time.LocalDate;
import java.util.HashMap;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("news")
@CompoundIndexes({
    @CompoundIndex(unique=true, name = "news_date_city", def = "{'date': 1, 'city': 1}")
})
public class NewsKeywordModel {
	
	@Id
	private String		dateCity;
	
	private LocalDate 	date;
	private String 		city;
	private	HashMap<String,Integer> keywords;
	
	
	
	
	public String getDateCity() {
		return dateCity;
	}
	public void setDateCity(String dateCity) {
		this.dateCity = dateCity;
	}
	
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public HashMap<String, Integer> getKeywords() {
		return keywords;
	}
	public void setKeywords(HashMap<String, Integer> keywords) {
		this.keywords = keywords;
	}

	
}
