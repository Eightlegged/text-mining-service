package com.sma.springboot;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;

import kr.bydelta.koala.twt.SentenceSplitter;
import kr.bydelta.koala.eunjeon.Tagger;
import kr.bydelta.koala.kkma.Parser;
import kr.bydelta.koala.data.Sentence;
import kr.bydelta.koala.data.Word;

import java.io.IOException;
import java.util.*;

@RestController
public class KeywordController {
		
//	public List<String> nouns = new ArrayList<String>();
//	public Keyword keywords = new Keyword(Arrays.asList("1", "2"), "initial division");
	
	@RequestMapping("/keyword")
	@ResponseBody
	public Keyword keyword(@RequestBody DataObject input) {
		
		SentenceSplitter sentSplit = new SentenceSplitter();
		Keyword keywords = new Keyword(Arrays.asList("1", "2"), "initial division");
		
		String paragraph = input.getSpeech();
		
		List<String> sentences = sentSplit.jSentences(paragraph);
		
		List<String> nouns = return_nouns(sentences);
		List<String> verbs = return_verbs(sentences);
		List<String> nouns_verbs = new ArrayList<String>(nouns); nouns_verbs.addAll(verbs);

		keywords.setKeywords(sortByValue(count(nouns_verbs)).subList(0, 9));
		keywords.setDivision(input.getDivision());
		
		return keywords;
	}
	
	public List<String> return_nouns(List<String> sentences) {
		List<String> nouns = new ArrayList<String>();
		Tagger tagger = new Tagger();
		
		for (int i=0; i < sentences.size(); i++  ) {
			Sentence analyzed = tagger.tagSentence(sentences.get(i));
					
			for (int j=0; j < analyzed.jNouns().size(); j++ ) {
				
				nouns.add(analyzed.jNouns().get(j).toString().split("/")[0].split(" ")[1]);
				
			}
		}
		
		return nouns;
	}
	
	public List<String> return_verbs(List<String> sentences) {
		List<String> verbs = new ArrayList<String>();
		Tagger tagger = new Tagger();
		
		for (int i=0; i < sentences.size(); i++  ) {
			Sentence analyzed = tagger.tagSentence(sentences.get(i));
					
			for (int j=0; j < analyzed.jVerbs().size(); j++ ) {
				
				verbs.add(analyzed.jVerbs().get(j).toString().split("/")[0].split(" ")[1]);
				
			}
		}
		
		return verbs;
				
	}
	
	public HashMap<String, Integer> count(List<String> words) {
		HashMap<String , Integer> result = new HashMap<String , Integer>();
		for (int i=0; i < words.size(); i++) {
			
			if (!result.containsKey(words.get(i))) {
				result.put(words.get(i), 1);
			}
			else {
				result.put(words.get(i), result.get(words.get(i)) + 1);
			}
		}
		
		return result;
	}
	
	public static List<String> sortByValue(final Map map) {
        List<String> list = new ArrayList();
        list.addAll(map.keySet());
         
        Collections.sort(list,new Comparator() {
             
            public int compare(Object o1,Object o2) {
                Object v1 = map.get(o1);
                Object v2 = map.get(o2);
                 
                return ((Comparable) v2).compareTo(v1);
            }
             
        });
//        Collections.reverse(list); // 주석시 오름차순
        return list;
    }

	
}
