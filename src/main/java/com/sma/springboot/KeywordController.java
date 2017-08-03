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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@RestController
public class KeywordController {
		
//	public List<String> nouns = new ArrayList<String>();
	public Keyword keywords = new Keyword(Arrays.asList("1", "2"), "initial division");
	
	@RequestMapping("/keyword")
	@ResponseBody
	public Keyword keyword(@RequestBody DataObject input) {
		
		//초기화
		
//		Tagger tagger = new Tagger();
		SentenceSplitter sentSplit = new SentenceSplitter();

		
		String paragraph = input.getSpeech();
		
		List<String> sentences = sentSplit.jSentences(paragraph);
		
		List<String> nouns = return_nouns(sentences);
		List<String> verbs = return_verbs(sentences);

		keywords.setKeywords(nouns);
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
}
