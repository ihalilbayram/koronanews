package ibo.utils.parser;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import ibo.exceptions.ParagraphRuleException;
import ibo.model.NewsKeywordModel;

public class ParagraphModel {
	
	

	private HashMap<String, Object> newsMap = new HashMap<String, Object>();

	public boolean putIntoMap(Map.Entry<String, Object> entry) {

		if (!Objects.nonNull(this.newsMap.get(entry.getKey()))) {

			this.newsMap.put(entry.getKey(), entry.getValue());
		} else {

			throw new ParagraphRuleException("The key " + entry.getKey() + " occurs multiple times in paragraph ");
		}

		return true;
	}

	
	
	public NewsKeywordModel toNewsKeywordModel() {

		NewsKeywordModel model = new NewsKeywordModel();

		String city = (String) this.newsMap.remove("city");

		if (city != null) {
			model.setCity(city);
		} else {
			throw new ParagraphRuleException("City info does not exist!!!");
		}

		LocalDate date = (LocalDate) this.newsMap.remove("date");

		if (date != null) {
			model.setDate(date);
		} else {
			throw new ParagraphRuleException("Date info does not exist!!!");
		}
		
		model.setDateCity(date + "-" + city);

		HashMap<String, Integer> keywordMap = new HashMap<String, Integer>();

		for (Map.Entry<String, Object> entry : newsMap.entrySet()) {

			keywordMap.put(entry.getKey(), (Integer) entry.getValue());
		}
		
		model.setKeywords(keywordMap);
		
		return model;
	}
	
	

}
