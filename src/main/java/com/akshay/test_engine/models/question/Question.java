package com.akshay.test_engine.models.question;

import java.util.Arrays;

public class Question {
private int qid;
private String name;
private String descr;
private int score;
private int testid;
private Answer answers[];


public int getQid() {
	return qid;
}
public void setQid(int qid) {
	this.qid = qid;
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
public int getScore() {
	return score;
}
public void setScore(int score) {
	this.score = score;
}
public Answer[] getAnswers() {
	return answers;
}
public void setAnswers(Answer answers[]) {
	this.answers = answers;
}
@Override
public String toString() {
	return "Question [qid=" + qid + ", name=" + name + ", descr=" + descr + ", score=" + score + ", answers="
			+ Arrays.toString(answers) + "]";
}
public int getTestid() {
	return testid;
}
public void setTestid(int testid) {
	this.testid = testid;
}
}
