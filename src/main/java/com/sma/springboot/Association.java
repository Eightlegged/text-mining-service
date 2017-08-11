package com.sma.springboot;

import java.util.HashMap;

public class Association {
	private HashMap<String, Double> asso_words;
	private String topic;
	
	public Association(HashMap<String,Double> asso_words, String topic) {
		this.asso_words = asso_words;
		this.topic = topic;
	}
	
	public HashMap<String, Double> getAsso_words(){
		return asso_words;
	}
	
	public void setAsso_words(HashMap<String, Double> asso_words) {
		this.asso_words = asso_words;
	}
	
	public String getTopic() {
		return topic;
	}
	
	public void setTopic(String topic) {
		this.topic = topic;
	}
}
