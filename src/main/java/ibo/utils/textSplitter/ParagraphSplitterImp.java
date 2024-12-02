package ibo.utils.textSplitter;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ibo.utils.parser.ParagraphSplitter;
import ibo.utils.textSplitter.config.ConfigData;

public class ParagraphSplitterImp implements ParagraphSplitter{


	private ArrayList<Pattern> 	subSentencePatterns;
	private Locale 				locale;
	private HashSet<String>		abbreviationSet; //=  {	"Dr." , "Prof." , "Mr." , "Mrs." , "Ms." , "Jr." , "Ph.D."	};
	private Pattern pattern		=	Pattern.compile("\\b(\\S*)$");   // last word of sentence
	

	public ParagraphSplitterImp(ConfigData configData) {
		
		this.locale				= configData.getLocale();
		this.subSentencePatterns= configData.getSubSentencePatterns();
		this.abbreviationSet	= configData.getAbbreviationSet();	
	
	}
	
	
	
	public ArrayList<String> getSentences(String paragraph) {
	
		ArrayList<String> 	sentences;
		
		sentences = split(paragraph);
		
		sentences = new SentencesWithPatterns().parse(sentences, subSentencePatterns);
		
		return sentences;
	}
	
	
	
	
	public ArrayList<String> split(String document){
    	
    	ArrayList<String> sentenceList = new ArrayList<String>();
    	
        BreakIterator breakIterator = BreakIterator.getSentenceInstance(locale);
        
        breakIterator.setText(document);
        int start 	= breakIterator.first();
        int end 	= breakIterator.next();
        int tempStart = start;
        while (end != BreakIterator.DONE) {
        	
            String sentence = document.substring(start, end);
            if (! isEndWithAbbreviation(sentence)) {
                sentence = document.substring(tempStart, end);
                tempStart = end;
                sentenceList.add(sentence);
            }
            start = end; 
            end = breakIterator.next();
        }
        
        return sentenceList;    	
    }
    
	
	public boolean isEndWithAbbreviation(String sentence){
		
		Matcher matcher = pattern.matcher(sentence);
		if(matcher.find()) {
			
			 return this.abbreviationSet.contains( matcher.group());
			
		}else {
			// Log to unresolved end of sentence
			return false;
		}
	}
	
	
    
}
