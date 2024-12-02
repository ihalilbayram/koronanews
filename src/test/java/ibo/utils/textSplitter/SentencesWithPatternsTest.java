package ibo.utils.textSplitter;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.regex.Pattern;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class SentencesWithPatternsTest {

	@ParameterizedTest
	@MethodSource("dataSources.ArgumentProviderForSubSentences#sentenceWithPatternsArgProvider")
	@DisplayName("Testing dividing sentences into subsentences")
	void testSplit(ArrayList<String> inputSentences, ArrayList<String> expectedSubsentences, ArrayList<Pattern> patterns) throws Exception{

		SentencesWithPatterns swp = new SentencesWithPatterns() ;
		
		ArrayList<String> output = swp.parse(inputSentences, patterns);
		
		assertTrue(output.equals(expectedSubsentences));
		
		
		
//		text karşılaştırmaları soz olduğu için hatalı yeri aşağıdaki şekilde kontrol ediyorum, aslında
//		sys:out ile ekrana yazdırmak yerine log dosyasına atılması daha iyi şu an için log çok kullanmadığım için yapamadım
		
//		for(int i=0; i<output.size() || i<expectedSubsentences.size();i++) {
//			if(!output.get(i).equals(expectedSubsentences.get(i))) {
//				System.out.println( output.get(i) + "<>" + expectedSubsentences.get(i));
//				
//				char[] outputChars = output.get(i).toCharArray();
//				char[] expectedSubsentencesChars = expectedSubsentences.get(i).toCharArray();
//				
//				for(int k=0; k < outputChars.length || k < expectedSubsentencesChars.length ; k++) {
//				
//					System.out.println( outputChars[k] + " : " + expectedSubsentencesChars[k] + "--" + (int) outputChars[k] + " : " + (int) expectedSubsentencesChars[k]);
//				}
//			}
//		}

	
	}
}


