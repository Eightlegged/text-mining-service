package com.sma.springboot;

import java.util.List;

public class Keyword {
	private List<String> keywords;
	private String division;
	
	public Keyword(List<String> keywords, String division) {
		this.keywords = keywords;
		this.division = division;
	}
	
	public List<String> getKeywords(){
		return keywords;
	}
	
	public void setKeywords(List<String> keywords) {
		this.keywords = keywords;
	}
	
	public String getDivision() {
		return division;
	}
	
	public void setDivision(String division) {
		this.division = division;
	}
}