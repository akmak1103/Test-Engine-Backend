package com.akshay.test_engine.models.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akshay.test_engine.models.group.GroupInfo;
import com.akshay.test_engine.models.test.ScoreHistory;
import com.akshay.test_engine.models.test.Test;
import com.akshay.test_engine.models.test.TestsInfo;

@Service
public class UserService implements IUserService {
	@Autowired
	UserHelper helper;
	
	public UserHelper getHelper() {
		return helper;
	}

	public void setHelper(UserHelper helper) {
		this.helper = helper;
	}

	@Override
	public UserInfo login(User userObject) {
		return helper.login(userObject);
	}
	
	@Override
	public List<Test> fetchTests(String userid) {
		return helper.fetchTests(userid);
	}

	@Override
	public TestsInfo assignedTests(String userid) {
		return helper.assignedTests(userid);
	}

	@Override
	public UserInfo signup(User user) {
		return helper.signup(user);
	}

	@Override
	public List<GroupInfo> groups(String userid) {
		return helper.groups(userid);
	}

	@Override
	public List<ScoreHistory> scores(String userid) {
		return helper.previousTests(userid);
	}
}
