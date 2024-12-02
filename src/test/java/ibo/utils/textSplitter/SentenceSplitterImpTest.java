package ibo.utils.textSplitter;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import ibo.utils.textSplitter.SentenceSplitterImp;
import ibo.utils.parser.*;

	class SentenceSplitterImpTest {
	
		@ParameterizedTest	
		@MethodSource("sentenceAndItsWordArgProvider")
		@DisplayName("Split sentence into words")
		void getWords(String input, ArrayList<String> expectedValue) {
			
			SentenceSplitter sentenceSplitter = new SentenceSplitterImp();
			
			ArrayList<String> output = sentenceSplitter.getWords(input);
			
			assertTrue(output.equals(expectedValue));
			
		}
	

	private static Stream<Arguments> sentenceAndItsWordArgProvider(){
		
		String				sentence1="20.04.2020 tarihinde Ankara'da Korona, virüs salgınında yapılan testlerde 15 yeni vaka bulundu.";	
		ArrayList<String>	words1 	= new ArrayList<String>( Arrays.asList("20.04.2020", "tarihinde", "Ankara'da", "Korona", "virüs", "salgınında", "yapılan", "testlerde", "15", "yeni", "vaka", "bulundu"));
	
		String				sentence2=" 1 kişi korona'dan vefat etti.";	
		ArrayList<String>	words2 	= new ArrayList<String>( Arrays.asList(  "1", "kişi", "korona'dan", "vefat", "etti"   ));
	
		
		String				sentence3="5 kişide taburcu oldu";	
		ArrayList<String>	words3 	= new ArrayList<String>( Arrays.asList( "5", "kişide", "taburcu", "oldu"    ));
	
		String				sentence4="Korona virüs salgınında yapılan testlerde 19.04.2020 tarihinde  İstanbul'da 30 yeni vaka tespit edildi.";	
		ArrayList<String>	words4 	= new ArrayList<String>( Arrays.asList( "Korona", "virüs", "salgınında", "yapılan", "testlerde", "19.04.2020", "tarihinde", "İstanbul'da", "30", "yeni", "vaka", "tespit", "edildi"    ));
	
		String				sentence5=" İstanbul'da taburcu sayısı 7 kişi .";	
		ArrayList<String>	words5 	= new ArrayList<String>( Arrays.asList( "İstanbul'da", "taburcu", "sayısı", "7", "kişi"    ));
		
		String				sentence6=	"19.04.2020 tarihinde İstanbul  için korona virüs ile ilgili yeni bir açıklama yapıldı.";
		ArrayList<String>	words6 	= new ArrayList<String>( Arrays.asList( "19.04.2020", "tarihinde", "İstanbul", "için", "korona", "virüs", "ile", "ilgili", "yeni", "bir", "açıklama", "yapıldı"    ));
		
		return Stream.of(
				Arguments.of(sentence1,words1),
				Arguments.of(sentence2,words2),
				Arguments.of(sentence3,words3),
				Arguments.of(sentence4,words4),
				Arguments.of(sentence5,words5),
				Arguments.of(sentence6,words6)
		);
	}
	

	}



