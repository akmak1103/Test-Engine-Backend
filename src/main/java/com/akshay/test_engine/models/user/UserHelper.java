package com.akshay.test_engine.models.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.akshay.test_engine.models.group.GroupInfo;
import com.akshay.test_engine.models.test.ScoreHistory;
import com.akshay.test_engine.models.test.Test;
import com.akshay.test_engine.models.test.TestsInfo;

@Component
public class UserHelper {
	@Autowired
	private UserDAO userDAO;
	
	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public UserInfo login(User userObject) {
		return userDAO.doLogin(userObject);
	}
	
	public List<Test> fetchTests(String userid) {
		return userDAO.fetchTests(userid);
	}

	public TestsInfo assignedTests(String userid) {
		return userDAO.assignedTests(userid);
	}

	public UserInfo signup(User user) {
		int r = userDAO.signup(user);
		if (r>0)
		{
			userDAO.mapRole(user.getUserid(),user.getRole());
			UserInfo info = userDAO.doLogin(user);
			return info;
		}
		return null;
	}

	public List<GroupInfo> groups(String userid) {
		return userDAO.groups(userid);
	}

	public List<ScoreHistory> previousTests(String userid) {
		return userDAO.previousTests(userid);
	}
}
