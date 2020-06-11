package com.akshay.test_engine.models.question;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QuestionHelper {
	@Autowired
	QuestionDAO dao;
//	public int addQuestion(Question question)
//	{
//		return dao.addQuestion(question);
//	}
	
	public int addQuestion (Question question) {
		int queAdded = dao.addQuestion(question);
		if (queAdded>0) {
			int qid[] = new int[1];
			qid[0] = dao.getQuestionID(question.getName());
			return dao.mapTestQue(question.getTestid(), qid);
		}
		return 0;
	}
	
	public int mapTestQue(int testid,int qid[])
	{
		return dao.mapTestQue(testid, qid);
	}
	
	public Question getQuestionByID(int qid) {
		return dao.getQuestionByID(qid);
	}
	
	public List<Question> getQuestionByTestID(int testid) {
		return dao.getQuestionByTestID(testid);
	}

}
