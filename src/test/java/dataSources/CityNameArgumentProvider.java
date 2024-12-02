package dataSources;

import java.util.HashMap;
import java.util.Locale;
import java.util.stream.Stream;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import ibo.config.ApplicationData;

@SpringBootTest
public class CityNameArgumentProvider implements ArgumentsProvider {
	
	@Autowired
	ApplicationData appData;
	
	@Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
		
//		Locale 	locale 					=	appData.news.getLocale();
//		boolean isCaseSensitive			=	appData.news.isCityNameCaseSensitive();
//		HashMap<String,String> cityMap 	= appData.news.getCityMap(); 
		
		HashMap<String,String> cityMap = new HashMap<>();
		cityMap.put("Ankara", "Ankara");
		boolean isCaseSensitive = true;
		Locale locale = new Locale("tr-TR");
		
		
		//List<Arguments> testData = new ArrayList<Arguments>(); 
		
		Stream.Builder<Arguments> builder = Stream.builder();   
			
		
		cityMap.entrySet().stream().forEach(entry->{ builder.add(Arguments.of(entry.getKey(),entry.getValue())); });
		if(isCaseSensitive) {
			cityMap.entrySet().stream().forEach(entry->{
						builder.add(Arguments.of(entry.getKey().toLowerCase(locale), null));
						});
		}else {
			cityMap.entrySet().stream().forEach(entry->{
				builder.add(Arguments.of(entry.getKey().toLowerCase(locale), entry.getValue()));
				});
		}
		
		Arguments[] args = new Arguments[] {Arguments.of("hello",null)};
		
		return builder.build();
		//return Stream.of(testData);
        
	}
}