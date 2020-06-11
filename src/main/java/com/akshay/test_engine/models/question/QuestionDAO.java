package com.akshay.test_engine.models.question;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.akshay.test_engine.utils.Query;

@Repository
public class QuestionDAO {
	@Autowired
	JdbcTemplate jdbcTemplate;

	static List<Answer> answers;
	static List<Question> questions;

	public int addQuestion(Question question) {
		int records = jdbcTemplate.update(Query.QUESTION_ADD_SQL, question.getName(), question.getDescr(),
				question.getScore());
		if (records > 0)
			addAnswers(question.getName(), question.getAnswers());
		return records;
	}

	public int getQuestionID(String name) {
		int qID = jdbcTemplate.queryForObject(Query.QUESTION_SELECT_BY_ID, new Object[] { name }, Integer.class);
		return qID;
	}

	public int addAnswers(String qName, Answer answers[]) {
		int qid = getQuestionID(qName);
		int answersAdded = 0;
		for (int index = 0; index < answers.length; index++) {
			answersAdded += jdbcTemplate.update(Query.ANSWER_ADD_SQL, qid, answers[index].getName(),
					answers[index].getDescr(), answers[index].getIsCorrect());
		}
		return answersAdded;

	}

	public int mapTestQue(int testid, int qid[]) {
		int records = 0;
		for (int index = 0; index < qid.length; index++) {
			try {
				records += jdbcTemplate.update(Query.TEST_QUESTION_MAPPING_SQL, testid, qid[index]);
			} catch (Exception e) {
				records += 0;
			}
		}
		return records;
	}

	public Question getQuestionByID(int qid) {
		answers = new ArrayList<Answer>();
		List<Question> list = jdbcTemplate.query(Query.GET_QUESTION_BY_ID_SQL, new Object[] { qid },
				new QuestionInfoMapper());
		Answer[] ans = new Answer[answers.size()];
		list.get(0).setAnswers(answers.toArray(ans));
		list.get(0).setQid(qid);
		return list.get(0);
	}
	
	public List<Question> getQuestionByTestID(int testid) {
		List<Question> list = jdbcTemplate.query(Query.ALL_QUESTIONS_WITH_ANSWERS, new Object[] { testid },
				new QuestionTestInfoExtractor());
		return list;
	}

}

class QuestionInfoMapper implements RowMapper<Question> {

	@Override
	public Question mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		Question queInfo = new Question();
		queInfo.setName(rs.getString("qName"));
		queInfo.setDescr(rs.getString("qDescr"));
		queInfo.setScore(rs.getInt("score"));
		Answer answer = new Answer();
		answer.setAid(rs.getInt("aid"));
		answer.setName(rs.getString("aName"));
		answer.setDescr(rs.getString("aDescr"));
		answer.setIsCorrect(rs.getInt("isCorrect"));
		QuestionDAO.answers.add(answer);
		return queInfo;
	}

}

class QuestionTestInfoExtractor implements ResultSetExtractor<List<Question>> {

	@Override
	public List<Question> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<Question> questions = new ArrayList<Question>();
		boolean isLast = false;
		while (rs.next()) {
			Question question = new Question();
			question.setQid(rs.getInt("qid"));
			question.setName(rs.getString("qName"));
			question.setScore(rs.getInt("score"));
			question.setDescr(rs.getString("qDescr"));
			List<Answer> answers = new ArrayList<Answer>();
			while (rs.getString("qname").equals(question.getName())) {
				Answer answer = new Answer();
				answer.setAid(rs.getInt("aid"));
				answer.setName(rs.getString("aName"));
				answer.setDescr(rs.getString("aDescr"));
				answer.setIsCorrect(rs.getInt("isCorrect"));
				answers.add(answer);
				if (rs.isLast()) {
					isLast = true;
					break;
				} else
					rs.next();
			}
			Answer[] ans = new Answer[answers.size()];
			question.setAnswers(answers.toArray(ans));
			questions.add(question);
			if(!isLast)
				rs.previous();
		}
		return questions;
	}

}
