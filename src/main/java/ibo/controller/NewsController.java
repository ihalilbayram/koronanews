package ibo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import ibo.exceptions.SentenceRuleException;
import ibo.model.NewsInputModel;
import ibo.model.NewsKeywordModel;
import ibo.service.NewsService;


@RestController()
@RequestMapping("/news")
public class NewsController {
	
	private NewsService newsService;
	
	@Autowired
	public NewsController(NewsService newsService ){
		
		this.newsService = newsService;
	}
	
	
	@PostMapping( 
				produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, 
				consumes = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus( HttpStatus.CREATED )
	public NewsKeywordModel save(@RequestBody NewsInputModel newsInputModel ) {
		
		return newsService.save(newsInputModel.getText());			
	}
	
	
	@PostMapping(
				produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
				consumes = { MediaType.TEXT_PLAIN_VALUE })
	@ResponseStatus( HttpStatus.CREATED )
	public NewsKeywordModel save(@RequestBody String newsText ) {
		
		return newsService.save(newsText);	
	}

	
	
	
	@PutMapping( 
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, 
			consumes = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus( HttpStatus.ACCEPTED )
	public NewsKeywordModel update(@RequestBody NewsKeywordModel newsKeywordModel ) {
	
		return newsService.update(newsKeywordModel);			
	}
	
	
	
	@GetMapping(path="/{city}", 
				produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	
	public List<NewsKeywordModel> getByCity(@PathVariable String city) {
	
		return newsService.getByCity(city);	
	}
	
	
	@GetMapping(
			produces = {  MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE})

	public List<NewsKeywordModel> getByAll() {

		return newsService.getAll();	
	}

	
	
	
	
	
	
}

