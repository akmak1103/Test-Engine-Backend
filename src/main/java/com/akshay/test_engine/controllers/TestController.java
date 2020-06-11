package com.akshay.test_engine.controllers;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.akshay.test_engine.models.test.EvaluateTest;
import com.akshay.test_engine.models.test.Scorecard;
import com.akshay.test_engine.models.test.Test;
import com.akshay.test_engine.models.test.TestService;
import com.akshay.test_engine.utils.Lang;
import com.akshay.test_engine.utils.MessageBundle;
import com.akshay.test_engine.utils.StringResponse;

@RestController
@CrossOrigin
public class TestController implements Lang {
	@Autowired
	private TestService service;
	@Autowired
	private MessageBundle messageBundle;

	@PostMapping(path = "/addtest", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody StringResponse createTest(@RequestBody Test test) {
		test.setCreationDateTime(new Date());
		int records = service.addTest(test);
		StringResponse response = new StringResponse();
		messageBundle.setLang(ENGLISH);
		if (records > 0)
			response.setMsg(messageBundle.getMessage("createTest.success"));
		else if (records<0)
			response.setMsg(messageBundle.getMessage("createTest.duplicate"));
		else
			response.setMsg(messageBundle.getMessage("createTest.fail"));
		return response;
	}

	@PostMapping(path = "/evaluate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Scorecard evaluateTest(@RequestBody EvaluateTest evalTest) {
		return service.evalTest(evalTest);

	}
	
	@GetMapping(path = "/checkEligibility",produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody boolean checkEligibility (
			@RequestParam(value = "userid", required = true, defaultValue = "") String userid,
			@RequestParam(value = "testid", required = true, defaultValue = "") int testid)
	{
		return service.checkEligibility(userid,testid);
	}
}