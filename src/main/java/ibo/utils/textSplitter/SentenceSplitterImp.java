package ibo.utils.textSplitter;

import java.text.BreakIterator;
import java.util.ArrayList;

import ibo.utils.parser.SentenceSplitter;

public class SentenceSplitterImp implements SentenceSplitter{

	
	public ArrayList<String> getWords(String sentence) {
	
		   ArrayList<String> words = new ArrayList<String>();
		    
		    BreakIterator breakIterator = BreakIterator.getWordInstance();
		    
		    breakIterator.setText(sentence);
		    int lastIndex = breakIterator.first();
		    
		    while (BreakIterator.DONE != lastIndex) {
		    	
		        int firstIndex = lastIndex;
		        lastIndex = breakIterator.next();
		        if (lastIndex != BreakIterator.DONE && Character.isLetterOrDigit(sentence.charAt(firstIndex))) {
		            words.add(sentence.substring(firstIndex, lastIndex));
		        }
		    }
		    
		    return words;
	}

	
}
