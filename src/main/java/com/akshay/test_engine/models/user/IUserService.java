package com.akshay.test_engine.models.user;

import java.util.List;

import com.akshay.test_engine.models.group.GroupInfo;
import com.akshay.test_engine.models.test.ScoreHistory;
import com.akshay.test_engine.models.test.Test;
import com.akshay.test_engine.models.test.TestsInfo;

public interface IUserService {
	public UserInfo login(User userObject);
	public List<Test> fetchTests(String userid);
	public TestsInfo assignedTests(String userid);
	public UserInfo signup(User user);
	public List<GroupInfo> groups(String userid);
	public List<ScoreHistory> scores(String userid);
}