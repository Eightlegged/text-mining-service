package com.sma.springboot;

import java.util.HashMap;

public class Similarity {
	private Integer id;
	private HashMap<Integer, Double> similarity;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public HashMap<Integer, Double> getsimilarity() {
		return similarity;
	}
	
	public void setsimilarity(HashMap<Integer, Double> similarity) {
		this.similarity = similarity;
	}
	
}

