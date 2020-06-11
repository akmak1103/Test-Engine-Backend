package com.akshay.test_engine.controllers;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.akshay.test_engine.models.group.GroupInfo;
import com.akshay.test_engine.models.test.ScoreHistory;
import com.akshay.test_engine.models.test.Test;
import com.akshay.test_engine.models.test.TestsInfo;
import com.akshay.test_engine.models.user.DashboardService;
import com.akshay.test_engine.models.user.IUserService;
import com.akshay.test_engine.models.user.User;
import com.akshay.test_engine.models.user.UserInfo;
import com.akshay.test_engine.utils.Lang;
import com.akshay.test_engine.utils.MessageBundle;
import com.akshay.test_engine.utils.ReturnType;
import com.akshay.test_engine.utils.StringResponse;

@CrossOrigin
@RestController
public class UserController implements Lang {
	@Autowired
	private MessageBundle messageBundle;

	@Autowired
	private IUserService userService;

	@Autowired
	private DashboardService dashboardService;
	@SuppressWarnings("unused")
	private Logger logger = Logger.getLogger(UserController.class);

	@CrossOrigin
	@RequestMapping(method = RequestMethod.POST, path = "/login", consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public <T extends ReturnType> T performLogin(@RequestBody User userObject) {
		UserInfo userInfo = userService.login(userObject);
		if (userInfo == null) {
			StringResponse response = new StringResponse();
			messageBundle.setLang(ENGLISH);
			response.setMsg(messageBundle.getMessage("login.fail"));
			return (T) response;
		}
		return (T) userInfo;
	}

	@CrossOrigin
	@PostMapping(path = "/signup", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public <T extends ReturnType> T signup(@RequestBody User user) {
		UserInfo userInfo = userService.signup(user);
		if (userInfo == null) {
			StringResponse response = new StringResponse();
			messageBundle.setLang(ENGLISH);
			response.setMsg(messageBundle.getMessage("userid.duplicate"));
			return (T) response;
		}
		return (T) userInfo;
	}

	@GetMapping(path = "/alltests", produces = MediaType.APPLICATION_JSON_VALUE)
	public <T> T fetchTests(
			@RequestParam(value = "userid", required = true, defaultValue = "") String userid) {
		List<Test> testsInfo = userService.fetchTests(userid);
		if (testsInfo.size() == 0) {
			StringResponse response = new StringResponse();
			messageBundle.setLang(ENGLISH);
			response.setMsg(messageBundle.getMessage("fetchTests.fail"));
			return (T) response;
		}
		return (T) testsInfo;
	}

	@GetMapping(path = "/assignedTests", produces = MediaType.APPLICATION_JSON_VALUE)
	public <T extends ReturnType> T assignedTests(
			@RequestParam(value = "userid", required = true, defaultValue = "") String userid) {
		TestsInfo testsInfo = userService.assignedTests(userid);
		if (testsInfo.getTests().length == 0) {
			StringResponse response = new StringResponse();
			messageBundle.setLang(ENGLISH);
			response.setMsg(messageBundle.getMessage("assignTests.fail"));
			return (T) response;
		}
		return (T) testsInfo;
	}
	
	@GetMapping(path="/allgroups", produces = MediaType.APPLICATION_JSON_VALUE)
	public <T> T groups(@RequestParam(value = "userid", required = true, defaultValue = "") String userid) {
		List<GroupInfo> groups = userService.groups(userid);
		if(groups.size()==0) {
			StringResponse response = new StringResponse();
			messageBundle.setLang(ENGLISH);
			response.setMsg(messageBundle.getMessage("allGroups.fail"));
			return (T) response;
		}
		return (T) groups;
	}
	
	@GetMapping(path="/previousTests", produces = MediaType.APPLICATION_JSON_VALUE)
	public <T> T previousScores(@RequestParam(value = "userid", required = true, defaultValue = "") String userid) {
		List<ScoreHistory> scores = userService.scores(userid);
		if(scores.size()==0) {
			StringResponse response = new StringResponse();
			messageBundle.setLang(ENGLISH);
			response.setMsg(messageBundle.getMessage("previousTests.fail"));
			return (T) response;
		}
		return (T) scores;
	}

	@GetMapping(path = "/dashboard", produces = MediaType.APPLICATION_JSON_VALUE)
	public <T extends ReturnType> T dashboard(@RequestParam(required = true, defaultValue = "") String userid,
			@RequestParam(required = true, defaultValue = "") String role) {
		if (role.equals("Teacher")) {
			return (T) dashboardService.teacher(userid);
		}
		else if (role.equals("Student"))
			return (T) dashboardService.student(userid);
		return null;
	}

}
