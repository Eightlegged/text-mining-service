package com.sma.springboot;

import java.util.List;

public class DataObject {
	private List<String> speech;
	private String division;
	
	
	
	public List<String> getSpeech(){
		return speech;
	}
	
	public void setSpeech(List<String> speech) {
		this.speech = speech;
	}
	
	public String getDivision() {
		return division;
	}
	
	public void setDivision(String division) {
		this.division = division;
	}
}