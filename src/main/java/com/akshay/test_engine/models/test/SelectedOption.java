package com.akshay.test_engine.models.test;

public class SelectedOption {
private int qid;
private int aid;
public int getQid() {
	return qid;
}
public void setQid(int qid) {
	this.qid = qid;
}
public int getAid() {
	return aid;
}
public void setAid(int aid) {
	this.aid = aid;
}
@Override
public String toString() {
	return "SelectedOption [qid=" + qid + ", aid=" + aid + "]";
}
}
