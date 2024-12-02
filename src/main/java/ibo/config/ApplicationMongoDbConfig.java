package ibo.config;

import java.util.Collection;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoClientFactoryBean;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import ibo.exceptions.DatabaseConfigurationException;

@Configuration
public class ApplicationMongoDbConfig {
 
	  	@Bean
	    public MongoClient getMongoClient(ApplicationData appData) {
	  		
	  		String connectionStr = appData.mongoServerData.getUrl();
	        ConnectionString connectionString = new ConnectionString(connectionStr);

	        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
	        															.applyConnectionString(connectionString)
	        															.build();
	        
	        return MongoClients.create(mongoClientSettings);
	    }
	  

	    @Bean
	    public MongoTemplate mongoTemplate(ApplicationData AppData) {
	    	
	    	try {    		
	    		return new MongoTemplate(getMongoClient(AppData), "korona");
	    		
	    	}catch(Exception e) {
	    		
	    		throw new DatabaseConfigurationException("Error occured while creating MongoTemplate");
	    	}
	    	
	    }
}