package com.akshay.test_engine.models.test;

import java.util.Arrays;

public class ScoreHistory {
private String name;
private Integer[] score;
private int maxScore;
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public Integer[] getScore() {
	return score;
}
public void setScore(Integer[] integers) {
	this.score = integers;
}
public int getMaxScore() {
	return maxScore;
}
public void setMaxScore(int maxScore) {
	this.maxScore = maxScore;
}
@Override
public String toString() {
	return "ScoreHistory [name=" + name + ", score=" + Arrays.toString(score) + ", maxScore=" + maxScore + "]";
}

}
