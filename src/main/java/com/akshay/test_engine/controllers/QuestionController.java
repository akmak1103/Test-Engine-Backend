package com.akshay.test_engine.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.akshay.test_engine.models.question.MapTestQuestion;
import com.akshay.test_engine.models.question.Question;
import com.akshay.test_engine.models.question.QuestionService;
import com.akshay.test_engine.utils.Lang;
import com.akshay.test_engine.utils.MessageBundle;
import com.akshay.test_engine.utils.StringResponse;

@RestController
@CrossOrigin
public class QuestionController implements Lang {
	@Autowired
	public QuestionService service;
	@Autowired
	MessageBundle messageBundle;
	
	
	@RequestMapping(path = "/addquestion",method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody StringResponse createQuestion(@RequestBody Question question) {
		int records = service.addQuestion(question);
		StringResponse response = new StringResponse();
		messageBundle.setLang(ENGLISH);
		if (records>0) 
			response.setMsg(messageBundle.getMessage("addQuestion.success"));
		else
			response.setMsg(messageBundle.getMessage("addQuestion.fail"));
		return response;
	}
	
	@CrossOrigin
	@PostMapping(path = "/mapquestions", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody StringResponse mapQuestionsToTest(@RequestBody MapTestQuestion mapper) {
		int ques = service.mapTestQue(mapper.getTestid(),mapper.getQid());
		StringResponse response = new StringResponse();
		messageBundle.setLang(ENGLISH);
		response.setMsg(ques +" "+ messageBundle.getMessage("mapQues.success"));
		return response;
	}
	
	@CrossOrigin
	@GetMapping(path="/gettestquestion",produces = MediaType.APPLICATION_JSON_VALUE)
	public <T> T testQuestionInfo(@RequestParam(value = "testid",required = false, defaultValue = "1")  int testid) {
		List<Question> ques = service.getQuestionByTestID(testid);
		if (ques.size() == 0)
		{
			StringResponse response = new StringResponse();
			messageBundle.setLang(ENGLISH);
			response.setMsg(messageBundle.getMessage("quesInTest.fail"));
			return (T) response;
		}
		return (T) ques;
	}
	
	//can be removed ...
	@CrossOrigin
	@GetMapping(path="/getquestion",produces = MediaType.APPLICATION_JSON_VALUE)
	public Question questionInfo(@RequestParam(value = "qid",required = false, defaultValue = "1")  int qid) {
		return service.getQuestionByID(qid);
	}
	
}