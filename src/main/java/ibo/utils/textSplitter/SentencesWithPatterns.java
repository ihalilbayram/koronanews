package ibo.utils.textSplitter;

import java.util.ArrayList;
import java.util.function.BiFunction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;

public class SentencesWithPatterns {
	
	ArrayList<String> children = new ArrayList<>();
	
	public SentencesWithPatterns() { }
	
	
	public ArrayList<String> parse(ArrayList<String> sentences, ArrayList<Pattern> patterns) {
		
		parseSubsentences(sentences, patterns);
		return this.children;
	}
	
	private void parseSubsentences(ArrayList<String> sentences, ArrayList<Pattern> patterns) {
		
		Stream<SentenceWithPatterns> stream = toSentenceWithPatterns.apply(sentences, patterns);
		
//		if(this.parallelSplitMode) {
//			stream.parallel();
//		}
		
		stream.forEach(swp-> applyPatterns(swp));
		
	}

	
	private void applyPatterns( SentenceWithPatterns swp){
		
		int patternSize = swp.patterns.size();
		
		if(patternSize > 0) {
			
			ArrayList<String> sentences = applyPattern(swp.sentence, swp.patterns.get(patternSize-1));
			
			swp.patterns.remove(patternSize-1);
			if(patternSize - 1 == 0) {
				this.children.addAll(sentences);			// no any patterns all processed
			}else {
				parseSubsentences(sentences,swp.patterns);  // apply remaining patterns to all subsentences
			}
		}		
	}
	
	/**
	 *     each sentence gets the same patterns to be applied recursively, look caution giving clone of patterns 
	 *     otherwise in parallel mode will be concurrency issue, no need to deep copy, clone is enough  
	 */
	@SuppressWarnings("unchecked")
	public BiFunction<ArrayList<String>, ArrayList<Pattern>, Stream<SentenceWithPatterns>> toSentenceWithPatterns =

			(sentences, patterns) -> {

				if(sentences != null || patterns != null) {
					Stream.empty();
				}
				
				return sentences.stream()
						.map(sentence -> new SentenceWithPatterns(sentence, (ArrayList<Pattern>) patterns.clone()));
			};

			
	
			

			/**
			 * @param sentence
			 * @param patternStr 
			 * 					pattern can be combination of first sentence and next sentence (not should be end of single sentence)
			 * 					in application properties.file use named pattern with names must include ?<firstSentence> and ?<secondSentence>
			 *					that makes more robust and flexible that you can get full control starting and ending of sentences 					
			 * @return
			 */
			private static ArrayList<String> applyPattern(String sentence, Pattern pattern){
				
				ArrayList<String> sentenceList = new ArrayList<>(); 
				
				sentence = sentence.strip();
				
				Matcher matcher = pattern.matcher(sentence);
				int[] nextStart = new int[1];
				nextStart[0] = 0;
				
				while(matcher.find()) {
					
						String subSentence = sentence.substring(nextStart[0] , matcher.end("firstSentence"));
						sentenceList.add(subSentence);			
						nextStart[0] = matcher.start("secondSentence");
				  } 
				  if(sentence.length()>nextStart[0]) { 
					  sentenceList.add(sentence.substring(nextStart[0])); 
				  }
				 
		        return sentenceList;
			}
					
			
			
 	class SentenceWithPatterns{
		
		public String sentence;
		public ArrayList<Pattern> patterns;
		
		public SentenceWithPatterns(String sentence, ArrayList<Pattern> patterns) {
		
			this.sentence = sentence;
			this.patterns = patterns;		
		}
		
	}
	
}
