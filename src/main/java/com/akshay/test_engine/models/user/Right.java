package com.akshay.test_engine.models.user;

public class Right {
	private String name;
	private String url;
	public String getName() {
		if (name == null) return "";
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		if (url == null) return "";
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
