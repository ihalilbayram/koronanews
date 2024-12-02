package dataSources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;

public class ArgumentProviderForSubSentences {
	
	/**
	 *  sentences subdivided into subsentences  
	 * 
	 */
	
	public static Stream<Arguments> sentenceWithPatternsArgProvider() {
		
		HashMap<ArrayList<String>,ArrayList<String>> map = new HashMap<>();
		ArrayList<Arguments> argumentList = new ArrayList<>();
		
		ArrayList<Pattern> patterns = new ArrayList<>();
		
		Pattern pattern;
		
		pattern = Pattern.compile("(?<firstSentence>\\.)(?<gap>\\s+)(?<secondSentence>\\p{Lu})", Pattern.UNICODE_CHARACTER_CLASS);
		patterns.add(pattern);
		pattern = Pattern.compile("(?<firstSentence>\\!)(?<gap>\\s+)(?<secondSentence>\\p{Lu})", Pattern.UNICODE_CHARACTER_CLASS);
		patterns.add(pattern);
		
		map = getData();
		
		map.entrySet().forEach(entry-> 
									{
										argumentList.add( Arguments.of(entry.getKey(), entry.getValue(), patterns)    );						
		
									});
		
		return argumentList.stream();	
	}
	
	
	

	
	
	
	
	
	
	public static HashMap<ArrayList<String>,ArrayList<String>> getData(){
	
		HashMap<ArrayList<String>,ArrayList<String>> map = new HashMap<>();
		ArrayList<String> sentenceList = new ArrayList<String>();
		ArrayList<String> sentenceSubList = new ArrayList<String>();
		
		
		String s1	= 	"Yetişme çağındaki Mine küçük! Başta anne ve babasını! kaybetmiş, ağabeyi Kenan ve yengesi Sacide’yle! Şaşamaya başlamıştır. "
				+   "  Sacide ile Mine. Teyze çocuklarıdır. Sacide’nin.İki erkek kardeşinden şair! yaradılışlı. Nühzet, Mine’yi sever; Doktor Fikret’se genç kızla "
				+ "sürekli çekişir, ona takılır. Duygularını düşüncelerini daima gizleyen Mine bir ruh durumundan sürekli karşıtına geçer. "
				+ "Neşesi gibi içe kapanışı da uç noktadır. Hastanede tanıştığı Sekreter Zehra’yla evlenen Fikret, Anadolu’ya Kurtuluş Savaşı’na gider. "
				+ "Sacide’nin annesi ölürken, yeğeni Mine ile Nühzet’in evlenmesini vasiyet eder. Mine sevmediği Nühzet’le evlenir. "
				+ "Uzaktan akrabaları Ressam Ömer Naim, Anadolu’ya geçmiş, bir süre Fikret ile Zehra’nın "
				+ "yanında kalmıştır. Genç adam Zehra’ya aşık olur. Aşkı karşılıksız kalınca! Ğepheden cepheye gider.";
		
		String s1Sub1	=   "Yetişme çağındaki Mine küçük!";
		String s1Sub2 	= 	"Başta anne ve babasını! kaybetmiş, ağabeyi Kenan ve yengesi Sacide’yle!";
		String s1Sub3	= 	"Şaşamaya başlamıştır.";
		String s1Sub4	=	"Sacide ile Mine.";
		String s1Sub5	=	"Teyze çocuklarıdır.";
		String s1Sub6	=	"Sacide’nin.İki erkek kardeşinden şair! yaradılışlı.";
		String s1Sub7	=	"Nühzet, Mine’yi sever; Doktor Fikret’se genç kızla sürekli çekişir, ona takılır.";
		String s1Sub8	=	"Duygularını düşüncelerini daima gizleyen Mine bir ruh durumundan sürekli karşıtına geçer.";
		String s1Sub9	=	"Neşesi gibi içe kapanışı da uç noktadır.";
		String s1Sub10	=	"Hastanede tanıştığı Sekreter Zehra’yla evlenen Fikret, Anadolu’ya Kurtuluş Savaşı’na gider.";
		String s1Sub11	=	"Sacide’nin annesi ölürken, yeğeni Mine ile Nühzet’in evlenmesini vasiyet eder.";
		String s1Sub12	=	"Mine sevmediği Nühzet’le evlenir."; 
		String s1Sub13 	=	"Uzaktan akrabaları Ressam Ömer Naim, Anadolu’ya geçmiş, bir süre Fikret ile Zehra’nın yanında kalmıştır."; 
		String s1Sub14	=	"Genç adam Zehra’ya aşık olur.";
		String s1Sub15	=	"Aşkı karşılıksız kalınca!";
		String s1Sub16	=	 "Ğepheden cepheye gider.";
		
		
		
		
		String s2	= 	"Yetişme çağındaki Mine küçük! Başta anne ve babasını! kaybetmiş. ağabeyi Kenan ve yengesi Sacide’yle Şaşamaya başlamıştır. "
				+   "  Sacide ile Mine. teyze çocuklarıdır. Sacide’nin.İki erkek kardeşinden şair! yaradılışlı. Nühzet, Mine’yi sever; Doktor Fikret’se genç kızla "
				+ "sürekli çekişir, ona takılır. Duygularını düşüncelerini!Daima gizleyen Mine bir ruh durumundan sürekli karşıtına geçer. "
				+ "Neşesi gibi içe kapanışı da uç noktadır.! Hastanede tanıştığı Sekreter Zehra’yla evlenen Fikret, Anadolu’ya Kurtuluş Savaşı’na gider!. "
				+ "Sacide’nin annesi ölürken, yeğeni Mine ile Nühzet’in evlenmesini vasiyet eder? Mine sevmediği Nühzet’le evlenir. "
				+ "Uzaktan akrabaları Ressam Ömer Naim, Anadolu’ya geçmiş! bir süre Fikret ile Zehra’nın "
				+ "yanında kalmıştır. Genç adam Zehra’ya aşık olur. Aşkı karşılıksız kalınca! Ğepheden cepheye gider.";
		
		String s2Sub1	=   "Yetişme çağındaki Mine küçük!";
		String s2Sub2 	= 	"Başta anne ve babasını! kaybetmiş. ağabeyi Kenan ve yengesi Sacide’yle Şaşamaya başlamıştır.";
		String s2Sub3	=	"Sacide ile Mine. teyze çocuklarıdır.";
		String s2Sub4	=	"Sacide’nin.İki erkek kardeşinden şair! yaradılışlı.";
		String s2Sub5	=	"Nühzet, Mine’yi sever; Doktor Fikret’se genç kızla sürekli çekişir, ona takılır.";
		String s2Sub6	=	"Duygularını düşüncelerini!Daima gizleyen Mine bir ruh durumundan sürekli karşıtına geçer.";
		String s2Sub7	=	"Neşesi gibi içe kapanışı da uç noktadır.!";
		String s2Sub8	=	"Hastanede tanıştığı Sekreter Zehra’yla evlenen Fikret, Anadolu’ya Kurtuluş Savaşı’na gider!.";
		String s2Sub9	=	"Sacide’nin annesi ölürken, yeğeni Mine ile Nühzet’in evlenmesini vasiyet eder? Mine sevmediği Nühzet’le evlenir."; 
		String s2Sub10 	=	"Uzaktan akrabaları Ressam Ömer Naim, Anadolu’ya geçmiş! bir süre Fikret ile Zehra’nın yanında kalmıştır."; 
		String s2Sub11	=	"Genç adam Zehra’ya aşık olur.";
		String s2Sub12	=	"Aşkı karşılıksız kalınca!";
		String s2Sub13	=	 "Ğepheden cepheye gider.";
		
		
		
		
		
		sentenceList.add(s1);
		sentenceList.add(s2);
		
		sentenceSubList.add(s1Sub1);
		sentenceSubList.add(s1Sub2);
		sentenceSubList.add(s1Sub3);
		sentenceSubList.add(s1Sub4);
		sentenceSubList.add(s1Sub5);
		sentenceSubList.add(s1Sub6);
		sentenceSubList.add(s1Sub7);
		sentenceSubList.add(s1Sub8);
		sentenceSubList.add(s1Sub9);
		sentenceSubList.add(s1Sub10);
		sentenceSubList.add(s1Sub11);
		sentenceSubList.add(s1Sub12);
		sentenceSubList.add(s1Sub13);
		sentenceSubList.add(s1Sub14);
		sentenceSubList.add(s1Sub15);
		sentenceSubList.add(s1Sub16);
		
		sentenceSubList.add(s2Sub1);
		sentenceSubList.add(s2Sub2);
		sentenceSubList.add(s2Sub3);
		sentenceSubList.add(s2Sub4);
		sentenceSubList.add(s2Sub5);
		sentenceSubList.add(s2Sub6);
		sentenceSubList.add(s2Sub7);
		sentenceSubList.add(s2Sub8);
		sentenceSubList.add(s2Sub9);
		sentenceSubList.add(s2Sub10);
		sentenceSubList.add(s2Sub11);
		sentenceSubList.add(s2Sub12);
		sentenceSubList.add(s2Sub13);

		map.put(sentenceList, sentenceSubList);	
		
		return map;		
		
	}
	

}
