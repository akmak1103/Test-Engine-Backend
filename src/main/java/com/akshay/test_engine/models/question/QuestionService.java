package com.akshay.test_engine.models.question;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {
	@Autowired
	QuestionHelper helper;
	public int addQuestion(Question question)
	{
		return helper.addQuestion(question);
	}
	
	public int mapTestQue (int testid,int qid[])
	{
		return helper.mapTestQue(testid, qid);
	}
	
	public Question getQuestionByID(int qid) {
		return helper.getQuestionByID(qid);
	}
	
	public List<Question> getQuestionByTestID(int testid) {
		return helper.getQuestionByTestID(testid);
	}
}
