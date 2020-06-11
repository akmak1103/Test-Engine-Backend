package com.akshay.test_engine.models.test;

import java.util.Arrays;

public class EvaluateTest {
private String userid;
private int testid;
private SelectedOption answers[];
public String getUserid() {
	if (userid == null) return "";
	return userid;
}
public void setUserid(String userid) {
	this.userid = userid;
}
public int getTestid() {
	return testid;
}
public void setTestid(int testid) {
	this.testid = testid;
}
public SelectedOption[] getAnswers() {
	return answers;
}
public void setAnswers(SelectedOption[] answers) {
	this.answers = answers;
}
@Override
public String toString() {
	return "EvaluateTest [userid=" + userid + ", testid=" + testid + ", answers=" + Arrays.toString(answers) + "]";
}
}
