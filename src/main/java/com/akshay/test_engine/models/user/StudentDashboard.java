package com.akshay.test_engine.models.user;

import java.util.List;

import com.akshay.test_engine.models.test.ScoreHistory;
import com.akshay.test_engine.models.test.TestsInfo;
import com.akshay.test_engine.utils.ReturnType;

public class StudentDashboard implements ReturnType {
private TestsInfo assignedTests;
private List<ScoreHistory> scores;
public TestsInfo getAssignedTests() {
	return assignedTests;
}
public void setAssignedTests(TestsInfo assignedTests) {
	this.assignedTests = assignedTests;
}
public List<ScoreHistory> getScores() {
	return scores;
}
public void setScores(List<ScoreHistory> scores) {
	this.scores = scores;
}

}
