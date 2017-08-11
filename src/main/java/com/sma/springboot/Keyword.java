package com.sma.springboot;

import java.util.HashMap;

public class Keyword {
	private HashMap<String, Integer> keywords;
	private String division;
	
	public Keyword(HashMap<String,Integer> keywords, String division) {
		this.keywords = keywords;
		this.division = division;
	}
	
	public HashMap<String, Integer> getKeywords(){
		return keywords;
	}
	
	public void setKeywords(HashMap<String, Integer> keywords) {
		this.keywords = keywords;
	}
	
	public String getDivision() {
		return division;
	}
	
	public void setDivision(String division) {
		this.division = division;
	}
}