package ibo.utils.textSplitter;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.*;
import java.util.stream.Stream;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import ibo.utils.textSplitter.config.ConfigData;

import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AbbreviationConcaterTest {

	@Mock
	ConfigData configData;
	
	@InjectMocks
    AbbreviationConcater ac;
	
	@BeforeEach
	void init() {
		
		HashSet<String> abbreviationSet = new HashSet<>(); 
		abbreviationSet.add("Dr.");
		abbreviationSet.add("Prof.");
		abbreviationSet.add("Sn.");
		
		when(configData.getAbbreviationSet()).thenReturn(abbreviationSet);
		//when(news.getAbbreviationSet()).thenReturn(abbreviationSet);
		
		ac = new AbbreviationConcater(configData);
		
	}
	
	/**
	 * this case test reconcatanation of incorrectly sentences due to the abbreviations 
	 */
	@ParameterizedTest	
	@MethodSource("concatArgProvider")
	@DisplayName("Testing Reconstruction of Sentence")
	void testConcat(ArrayList<String> input, ArrayList<String> expectedValue) {

		ArrayList<String> output = ac.concat(input);
		assert(output.equals(expectedValue));
	}

	
	
	
	
	@ParameterizedTest	
	@MethodSource("abbreviatedEndArgProvider")
	@DisplayName("Testing Abbreviation Ended Sentences")
	void testIsEndWithAbbreviation(String input, Boolean expectedValue) {
		
		boolean output = ac.isEndWithAbbreviation(input);
		assertTrue( ( !output && !expectedValue ) || ( output && expectedValue ));
	}
	
	
	
	
	private static Stream<Arguments> concatArgProvider(){
		
		ArrayList<String>	i1 = new ArrayList<String>( Arrays.asList("Dr.", "Kemal bey Prof.", "Ahmet beylerin ile Sn.", "Cengiz bey"));
		ArrayList<String>	e1 = new ArrayList<String>( Arrays.asList("Dr. Kemal bey Prof. Ahmet beylerin ile Sn. Cengiz bey"));
		
		ArrayList<String>	i2 = new ArrayList<String>( Arrays.asList("Dr.", "Kemal. bey Prof.", "Ahmet beylerin", "ile Sn.", "Cengiz bey"));
		ArrayList<String>	e2 = new ArrayList<String>( Arrays.asList("Dr. Kemal. bey Prof. Ahmet beylerin", "ile Sn. Cengiz bey"));
		
		ArrayList<String>	i3 = new ArrayList<String>( Arrays.asList("Dr.", "Kemal bey Prof..", "Ahmet beylerin ile Sn.", "Cengiz bey"));
		ArrayList<String>	e3 = new ArrayList<String>( Arrays.asList("Dr. Kemal bey Prof..", "Ahmet beylerin ile Sn. Cengiz bey"));
		
		ArrayList<String>	i4 = new ArrayList<String>( Arrays.asList("bDr.", "Kemal bey Prof..", "Ahmet beylerin ile bSn.", "Cengiz bey"));
		ArrayList<String>	e4 = new ArrayList<String>( Arrays.asList("bDr.", "Kemal bey Prof..", "Ahmet beylerin ile bSn.", "Cengiz bey"));
		
		
		return Stream.of(
						Arguments.of(i1,e1),
						Arguments.of(i2,e2),
						Arguments.of(i3,e3),
						Arguments.of(i4,e4)
				);
		}
	
	
	private static Stream<Arguments> abbreviatedEndArgProvider(){
		
		return Stream.of(

				Arguments.of("merhaba dr.",	false),
				Arguments.of("merhaba dr. ",	false),
				Arguments.of("merhaba DR.",	false),
				Arguments.of("merhaba Dr.",	true),
				Arguments.of("merhaba MDr.",	false),
				Arguments.of("merhaba dr. ",	false),
				Arguments.of("merhaba DR.",	false),
				Arguments.of("merhaba prof.",	false),
				Arguments.of("merhaba Prof.",	true),
				Arguments.of("merhaba Prof. ",	false),
				Arguments.of("merhaba aProf.",	false),
				Arguments.of("merhaba Sn.",	true),
				Arguments.of("merhaba SN.",	false)
				
				
				
				);		
	}

}
