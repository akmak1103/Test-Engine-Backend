package com.akshay.test_engine.models.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {
	@Autowired
	TestHelper helper;
	public int addTest(Test test) {
		return helper.addTest(test);
	}
	public Scorecard evalTest(EvaluateTest evalTest) {
		return helper.evalTest(evalTest);
	}
	public boolean checkEligibility(String userid, int testid) {
		return helper.checkEligibility(userid,testid);
	}
}