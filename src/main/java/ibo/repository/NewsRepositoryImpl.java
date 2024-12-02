package ibo.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;


import ibo.exceptions.DuplicateRecordException;
import ibo.model.NewsKeywordModel;


@Repository
public class NewsRepositoryImpl implements NewsRepository {

	private MongoTemplate mongoTemplate;
	
	@Autowired
	public NewsRepositoryImpl(MongoTemplate mongoTemplate) {
		
		this.mongoTemplate = mongoTemplate; 
	}
	
	
	@Override
	public NewsKeywordModel save(NewsKeywordModel newsKeywordModel) {
		
		try {
			
			return mongoTemplate.insert(newsKeywordModel, "news");
			
		}catch(DuplicateKeyException dpe) {
			throw new DuplicateRecordException("The record already exist!!!").attachObject(newsKeywordModel); 
		}
	}
	
	@Override
	public NewsKeywordModel update(NewsKeywordModel newsKeywordModel) {
		
		return mongoTemplate.save(newsKeywordModel, "news");
	}


	@Override
	public List<NewsKeywordModel> getAll() {
		
		Query query = new Query();
		return mongoTemplate.find(query, NewsKeywordModel.class);
	}


	@Override
	public List<NewsKeywordModel> getByCity(String city) {
		
		Query query = new Query();
		query.addCriteria(Criteria.where("city").is(city));
		return mongoTemplate.find(query, NewsKeywordModel.class);
	}

}
