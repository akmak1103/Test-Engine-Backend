package com.akshay.test_engine.models.test;

import com.akshay.test_engine.utils.ReturnType;

public class TestsInfo implements ReturnType{
	private Test[] test;

	public Test[] getTests() {
		return test;
	}

	public void setTests(Test[] test) {
		this.test = test;
	}
}