package com.akshay.test_engine.models.test;

public class CorrectAns {
private int qid,score,aid;
private String name;

public int getQid() {
	return qid;
}

public void setQid(int qid) {
	this.qid = qid;
}

public int getScore() {
	return score;
}

public void setScore(int score) {
	this.score = score;
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

@Override
public String toString() {
	return "CorrectAns [qid=" + qid + ", score=" + score + ", aid=" + aid + ", name=" + name + "]";
}

}
