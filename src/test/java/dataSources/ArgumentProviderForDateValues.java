package dataSources;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.springframework.boot.test.context.SpringBootTest;




public class ArgumentProviderForDateValues  {
	
	
	public Stream<Arguments> provideArguments(ExtensionContext context) {
		// TODO Auto-generated method stub
		
		//Stream.Builder<Arguments> builder = Stream.builder();
		
//		List<Arguments> listOfArguments = new ArrayList<Arguments>();
//		listOfArguments.add(Arguments.of("31.31.2005",	null));
//		return listOfArguments.stream();
		
		return Stream.of(
				Arguments.of("hello", 		LocalDate.of(2005,9,13)),
				Arguments.of("13.9.2005", 	LocalDate.of(2005,9,13)),
				Arguments.of("31.31.2005",	null),
				Arguments.of("31.2.2005",	null),
				Arguments.of("31.13.2005",	null),
				Arguments.of("29.2.2005",	null),
				Arguments.of("12.15.2005", 	LocalDate.of(2005,12,15)),
				Arguments.of("01.01.2005", 	LocalDate.of(2005,01,01)),
				Arguments.of("13.9.2005", 	LocalDate.of(2005,9,13))
				);
	}
}