package ibo.repository;

import java.util.HashMap;
import java.util.List;

import ibo.model.NewsKeywordModel;


public interface NewsRepository {
	
	public NewsKeywordModel save(NewsKeywordModel newsKeywordModel);
	public List<NewsKeywordModel> getAll();
	public List<NewsKeywordModel> getByCity(String city);
	public NewsKeywordModel update(NewsKeywordModel newsKeywordModel);

}
