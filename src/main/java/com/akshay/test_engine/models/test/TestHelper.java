package com.akshay.test_engine.models.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestHelper {
	@Autowired
	TestDAO dao;

	public int addTest(Test test) {
		int testCreated = 0;
		try {
			testCreated = dao.addTest(test);
		} catch (Exception e) {
			return -1;
		}
		if (testCreated > 0) {
			int testId = dao.getTestId(test.getName());
			boolean result = dao.addTestMap(testId, test.getCreatedBy());
			if (!result) {
				dao.deleteTest(testId);
				return 0;
			}
		}
		return testCreated;
	}

	public Scorecard evalTest(EvaluateTest evalTest) {
		return dao.evalTest(evalTest);
	}

	public boolean checkEligibility(String userid, int testid) {
		return dao.checkEligibility(userid,testid);
	}
}
