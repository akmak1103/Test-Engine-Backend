package com.akshay.test_engine.models.test;

import java.util.Arrays;

public class Scorecard {
private int totalScore;
private int maxScore;
private String[] correctOption;

Scorecard(int count_Answers)
{
	correctOption = new String[count_Answers];
}
public int getTotalScore() {
	return totalScore;
}
public void setTotalScore(int totalScore) {
	this.totalScore = totalScore;
}
public String[] getCorrectOption() {
	return correctOption;
}
public void setCorrectOption(String[] correctOption) {
	this.correctOption = correctOption;
}
public int getMaxScore() {
	return maxScore;
}
public void setMaxScore(int maxScore) {
	this.maxScore = maxScore;
}
@Override
public String toString() {
	return "Scorecard [totalScore=" + totalScore + ", maxScore=" + maxScore + ", correctOption="
			+ Arrays.toString(correctOption) + "]";
}

}
