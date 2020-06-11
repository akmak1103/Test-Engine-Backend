package com.akshay.test_engine.models.question;

public class Answer {
	private int aid;
	private String name;
	private String descr;
	private int isCorrect;
	public int getIsCorrect() {
		return isCorrect;
	}
	public void setIsCorrect(int isCorrect) {
		this.isCorrect = isCorrect;
	}
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public String getName() {
		if (name == null) return "";
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescr() {
		if (descr == null) return "";
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}

	@Override
	public String toString() {
		return "Answer [aid=" + aid + ", name=" + name + ", descr=" + descr + ", isCorrect=" + isCorrect + "]";
	}
	
}
