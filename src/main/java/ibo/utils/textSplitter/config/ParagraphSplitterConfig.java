package ibo.utils.textSplitter.config;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import ibo.utils.textSplitter.AbbreviationConcater;
import ibo.utils.textSplitter.ParagraphSplitterImp;
import ibo.utils.textSplitter.SentenceSplitterImp;

@Configuration
@PropertySource(value="/textSplitter.properties", ignoreResourceNotFound=true)
public class ParagraphSplitterConfig {

	
	@Bean(initMethod="init")
	protected ConfigData getConfigData() {
		
		return new ConfigData();
	}
	
	@Bean
	public AbbreviationConcater getAbbreviationConcater(ConfigData configData) {
		
		return new AbbreviationConcater(configData);
	}
	
	@Bean
	public ParagraphSplitterImp getParagraphSplitterImp(ConfigData configData) {
		
		return new ParagraphSplitterImp(configData);
	}

	@Bean
	public SentenceSplitterImp getSentenceSplitterImp() {
		
		return new SentenceSplitterImp();
	}
}
