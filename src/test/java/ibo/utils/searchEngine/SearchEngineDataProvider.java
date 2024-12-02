package ibo.utils.searchEngine;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;



public class SearchEngineDataProvider {
	
	
	 public static Stream<Arguments> dateArgProvider() {
		
		 return Stream.of(
					
					Arguments.of("13.9.2005", 	LocalDate.of(2005,9,13)),
					Arguments.of("31.31.2005",	null),
					Arguments.of("31.2.2005",	null),
					Arguments.of("31.13.2005",	null),
					Arguments.of("29.2.2005",	null),
					Arguments.of("12.15.2005", 	null),
					Arguments.of("01.01.2005", 	LocalDate.of(2005,01,01)),
					Arguments.of("13.9.2005", 	LocalDate.of(2005,9,13)),
					Arguments.of("13.28.2005", 	null),
					Arguments.of("11.11.2005", 	LocalDate.of(2005,11,11)),
					Arguments.of("11/11/2005", 	LocalDate.of(2005,11,11)),
					Arguments.of("11-11-2005", 	LocalDate.of(2005,11,11)),
					Arguments.of("2005.11.11", 	LocalDate.of(2005,11,11)),
					Arguments.of("2005/11/11", 	LocalDate.of(2005,11,11)),
					Arguments.of("2005-11-11", 	LocalDate.of(2005,11,11)),					
					Arguments.of("11.11/2005", 	null),
					
					Arguments.of("11-11-77", 	LocalDate.of(1977,11,11)),
					Arguments.of("11.11.98", 	LocalDate.of(1998,11,11)),
					Arguments.of("11.11.00", 	LocalDate.of(2000,11,11)),
					Arguments.of("11.11.20", 	LocalDate.of(2000,11,11)),					
					Arguments.of("11.11.09", 	LocalDate.of(2009,11,11))
					);
	 }
	 
	 
	 public static Stream<Arguments> numberArgProvider() {
			// TODO Auto-generated method stub
			
			//Stream.Builder<Arguments> builder = Stream.builder();
			
		 	List<Arguments> listOfArguments = new ArrayList<Arguments>();
		 	listOfArguments.add(Arguments.of("34.32",	null));
		 	listOfArguments.add(Arguments.of("33",		33));
		 	listOfArguments.add(Arguments.of("31.",		null));
		 	listOfArguments.add(Arguments.of("43",		43));
		 	listOfArguments.add(Arguments.of("0",		0));
		 	listOfArguments.add(Arguments.of("k12",		null));
		 	listOfArguments.add(Arguments.of(".43",		null));
		 	listOfArguments.add(Arguments.of("27",		27));
		 	listOfArguments.add(Arguments.of("31.31.2008",	null));
		 	listOfArguments.add(Arguments.of("3",		3));
		 	
		 	return listOfArguments.stream();

	 }

}
