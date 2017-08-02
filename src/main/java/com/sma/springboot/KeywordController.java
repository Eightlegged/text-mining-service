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

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
public class KeywordController {
	
	@RequestMapping(value = "/keyword", method = RequestMethod.POST)
	@ResponseBody
	public DataObject keyword(@RequestBody DataObject input) {
		System.out.println(input.getSpeech());
		return input;
	}
}
