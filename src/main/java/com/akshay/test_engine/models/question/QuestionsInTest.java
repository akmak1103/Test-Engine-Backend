package com.akshay.test_engine.models.question;

import java.util.Arrays;

import com.akshay.test_engine.utils.ReturnType;

public class QuestionsInTest implements ReturnType {
	private Question[] questions;
	public Question[] getQuestions() {
		return questions;
	}
	public void setQuestions(Question[] questions) {
		this.questions = questions;
	}
	@Override
	public String toString() {
		return "QuestionsInTest [questions=" + Arrays.toString(questions) + "]";
	}
}
