package ibo.service;

import java.util.List;

import ibo.model.NewsInputModel;
import ibo.model.NewsKeywordModel;


public interface NewsService {

	public NewsKeywordModel 		save(String newsText);
	public List<NewsKeywordModel> 	getAll();
	public List<NewsKeywordModel> 	getByCity(String city);
	public NewsKeywordModel 		update(NewsKeywordModel newsKeywordModel);
	
}
