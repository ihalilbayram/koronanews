package mongoTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.ConnectionString;
import com.mongodb.DBObject;
import com.mongodb.DuplicateKeyException;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import de.flapdoodle.embed.mongo.*;
import de.flapdoodle.embed.mongo.config.*;
import de.flapdoodle.embed.process.runtime.Network;
import ibo.exceptions.DatabaseConfigurationException;
import ibo.model.NewsKeywordModel;
import de.flapdoodle.embed.mongo.config.MongodConfig;
import de.flapdoodle.embed.mongo.distribution.Version;




class MongoDbTest {
	
	private MongoClient mongoClient;
	private MongoTemplate mongoTemplate;

   

    @BeforeEach
    void setup() throws Exception {
    	
//    	 String ip = "localhost";
//         int port = 27017;
//
//         ImmutableMongodConfig mongodConfig = MongodConfig
//             .builder()
//             .version(Version.Main.PRODUCTION)
//             .net(new Net(ip, port, Network.localhostIsIPv6()))
//             .build();
//
//         MongodStarter starter = MongodStarter.getDefaultInstance();
//         mongodExecutable = starter.prepare(mongodConfig);
//         mongodExecutable.start();
//         mongoTemplate = new MongoTemplate(MongoClients.create(String.format(CONNECTION_STRING, ip, port)), "test");
    	
    	String connectionStr = "mongodb+srv://ihalilbayram:Ibo.1414mdb@cluster0.ozd3v.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0";
  		
        ConnectionString connectionString = new ConnectionString(connectionStr);
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
          .applyConnectionString(connectionString)
          .build();
        
        mongoClient = MongoClients.create(mongoClientSettings);
        
        try {
    		
    		mongoTemplate = new MongoTemplate(mongoClient, "korona");
    		
    	}catch(Exception e) {
    		
    		throw new DatabaseConfigurationException("Error occured while creation of MongoTemplate");
    	}
    }


 
	
	@Disabled
    @Test
    void insertTest() throws Exception {
        // given
        
    	ArrayList<NewsKeywordModel> newsList = getNews();
    	
        newsList.forEach( news->{
        	try {
        		mongoTemplate.save(news);
        	}catch(DuplicateKeyException exc) {
        		System.out.println(exc);
        	}
        	
    	});
     
    }
    
    
    @Test
    void selectTest() throws Exception {
    	
    	Query query = new Query();
        query.addCriteria(Criteria.where("city").is("Şanlıurfa"));
        List<NewsKeywordModel> list = mongoTemplate.find(query, NewsKeywordModel.class);
    	
    }


    public ArrayList<NewsKeywordModel> getNews(){
    	
    	ArrayList<NewsKeywordModel> newsList = new ArrayList<NewsKeywordModel>();
    	
    	NewsKeywordModel newsModel;
    	LocalDate date;
        String city;
        HashMap<String, Integer> mapKeyword;
        
        
        newsModel = new NewsKeywordModel();
        date = LocalDate.now();
        city = "Ankara";
        
        mapKeyword = new HashMap<>();
        mapKeyword.put("entübe", 10);
        mapKeyword.put("ölüm", 20);
        mapKeyword.put("taburcu", 15);       
        
        newsModel.setDate(date);
        newsModel.setCity(city);
        newsModel.setDateCity(date + city);
        newsModel.setKeywords(mapKeyword);
        
        newsList.add(newsModel);
        
        
        newsModel = new NewsKeywordModel();
        date = LocalDate.now();
        city = "İstanbul";
        
        mapKeyword = new HashMap<>();
        mapKeyword.put("entübe", 20);
        mapKeyword.put("ölüm", 12);
        mapKeyword.put("taburcu", 13);       
        
        newsModel.setDate(date);
        newsModel.setCity(city);
        newsModel.setDateCity(date + city);
        newsModel.setKeywords(mapKeyword);
        
        newsList.add(newsModel);
        
        
        newsModel = new NewsKeywordModel();
        date = LocalDate.now();
        city = "Muş";
        
        mapKeyword = new HashMap<>();
        mapKeyword.put("entübe", 30);
        mapKeyword.put("ölüm", 20);
        mapKeyword.put("taburcu", 25);       
        
        newsModel.setDate(date);
        newsModel.setCity(city);
        newsModel.setDateCity(date + city);
        newsModel.setKeywords(mapKeyword);
        
        newsList.add(newsModel);
        
        
        newsModel = new NewsKeywordModel();
        date = LocalDate.of(2023, 12, 20);
        city = "Şanlıurfa";
        
        mapKeyword = new HashMap<>();
        mapKeyword.put("entübe", 10);
        mapKeyword.put("ölüm", 20);
        mapKeyword.put("taburcu", 15);       
        
        newsModel.setDate(date);
        newsModel.setCity(city);
        newsModel.setDateCity(date + city);
        newsModel.setKeywords(mapKeyword);
        
        newsList.add(newsModel);
        
        
        newsModel = new NewsKeywordModel();
        date = LocalDate.of(2024, 1, 2);
        city = "Şanlıurfa";
        
        mapKeyword = new HashMap<>();
        mapKeyword.put("entübe", 10);
        mapKeyword.put("ölüm", 20);
        mapKeyword.put("taburcu", 15);       
        
        newsModel.setDate(date);
        newsModel.setCity(city);
        newsModel.setDateCity(date + city);
        newsModel.setKeywords(mapKeyword);
        
        newsList.add(newsModel);
        
        
        newsModel = new NewsKeywordModel();
        date = LocalDate.now();
        city = "Eskişehir";
        
        mapKeyword = new HashMap<>();
        mapKeyword.put("entübe", 10);
        mapKeyword.put("ölüm", 20);
        mapKeyword.put("taburcu", 15);       
        
        newsModel.setDate(date);
        newsModel.setCity(city);
        newsModel.setDateCity(date + city);
        newsModel.setKeywords(mapKeyword);
        
        newsList.add(newsModel);
    	
        return newsList;
        
    }


	
}



