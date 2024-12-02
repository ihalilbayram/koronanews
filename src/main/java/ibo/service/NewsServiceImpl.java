package ibo.service;


import java.util.List;

import org.springframework.stereotype.Service;


import ibo.model.NewsKeywordModel;
import ibo.repository.NewsRepository;
import ibo.utils.parser.NewsParser;

@Service
public class NewsServiceImpl implements NewsService{

	
	NewsParser 		newsParser;
	NewsRepository 	newsRepository;
	
	public NewsServiceImpl(NewsParser newsParser, NewsRepository newsRepository) {
		
		this.newsParser		=	newsParser;
		this.newsRepository	=	newsRepository;		
	}
		
	
	
	@Override
	public NewsKeywordModel save(String news) {
		
		NewsKeywordModel newsKeywordModel = new NewsKeywordModel();
		newsKeywordModel = newsParser.parse(news);
		return newsRepository.save(newsKeywordModel);
		
	}


	@Override
	public NewsKeywordModel update(NewsKeywordModel newsKeywordModel) {
		
		return newsRepository.update(newsKeywordModel);
	}
	

	@Override
	public List<NewsKeywordModel> getAll() {
		
		return newsRepository.getAll();
	}



	@Override
	public List<NewsKeywordModel> getByCity(String city) {
		
		return newsRepository.getByCity(city);
	}
	
	

}
