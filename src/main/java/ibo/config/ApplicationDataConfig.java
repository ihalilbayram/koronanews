package ibo.config;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;

@Configuration
public class ApplicationDataConfig {
	
	@Bean(initMethod="init")
	public ApplicationData getApplicationData( 	ApplicationData.CommonData 		commonData, 
												ApplicationData.MongoServerData	mongoServerData,
												ApplicationData.NewsData		newsData) {
		
		return new ApplicationData(commonData, mongoServerData, newsData);
	}

	
	@Bean(initMethod="init")
	public ApplicationData.CommonData getApplicationCommonData( ) {
		
		return new ApplicationData.CommonData();		
	}
	

	@Bean(initMethod="init")
	public ApplicationData.MongoServerData getApplicationMongoServerData(){
		
		return new ApplicationData.MongoServerData();		
	}
	
	@Bean(initMethod="init")
	public ApplicationData.NewsData getApplicationNewsData(){
		
		return new ApplicationData.NewsData();
	}

}
