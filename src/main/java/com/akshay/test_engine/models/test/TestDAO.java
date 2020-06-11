package com.akshay.test_engine.models.test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.akshay.test_engine.utils.Query;

@Repository
public class TestDAO {
	@Autowired
	JdbcTemplate jdbcTemplate;

	public int addTest(Test test) throws DuplicateKeyException {
		int records = jdbcTemplate.update(Query.TEST_ADD_SQL, test.getName(), test.getDescr(), test.getDuration(),
				test.getNoOfAttempts(), test.getCreatedBy(), test.getCreationDateTime(), test.getPassingScore());
		return records;
	}

	public boolean deleteTest(int testid) {
		return (jdbcTemplate.update(Query.DELETE_TEST, testid) > 0);
	}

	public int getTestId(String name) {
		int testid = jdbcTemplate.queryForObject(Query.TEST_SELECT_BY_ID, new Object[] { name }, Integer.class);
		return testid;
	}

	public boolean addTestMap(int testId, String createdBy) {
		int records;
		int uid = getUID(createdBy);
		if (uid > 0)
			records = jdbcTemplate.update(Query.TEST_MAPPING_ADD_SQL, testId, uid);
		else
			return false;
		return records > 0;
	}

	private int getUID(String userid) {
		int uid=0;
		try {
			uid = jdbcTemplate.queryForObject(Query.GET_UID_BY_USERNAME, new Object[] { userid }, Integer.class);
		} catch (Exception e) {
			return 0;
		}
		return uid;
	}

	public Scorecard evalTest(EvaluateTest evalTest) {
		List<CorrectAns> list = jdbcTemplate.query(Query.CORRECT_ANSWERS_OF_TEST, new Object[] { evalTest.getTestid() },
				new CorrectAnsMapper());
		CorrectAns[] correctAns = new CorrectAns[list.size()];
		correctAns = list.toArray(correctAns);
		int totalScore = 0;
		int maxScore = 0;
		Scorecard scorecard = new Scorecard(evalTest.getAnswers().length);
		for (int i = 0; i < correctAns.length; i++) {
			int score = 0;
			if (evalTest.getAnswers()[i].getAid() == correctAns[i].getAid())
				score = correctAns[i].getScore();
			totalScore += score;
			maxScore+=correctAns[i].getScore();
			jdbcTemplate.update(Query.MAKE_SCORECARD,
					new Object[] { getUID(evalTest.getUserid()), evalTest.getTestid(), correctAns[i].getQid(), score,score });
			scorecard.getCorrectOption()[i] = correctAns[i].getName();
		}
		scorecard.setTotalScore(totalScore);
		scorecard.setMaxScore(maxScore);
		markTestAttempted(evalTest.getUserid(), evalTest.getTestid());
		addToHistory(evalTest.getUserid(), evalTest.getTestid(), totalScore, maxScore);
		return scorecard;
	}

	private int markTestAttempted(String userid, int testid) {
		int attemptsMade = attemptsMade(userid, testid);
		return jdbcTemplate.update(Query.MARK_ATTEMPTED, new Object[] { getUID(userid), testid, attemptsMade + 1, attemptsMade + 1 });
	}

	private int addToHistory(String userid,int testid,int score,int maxScore) {
		return jdbcTemplate.update(Query.ADD_TO_HISTORY,new Object[] {getUID(userid),testid,score,maxScore});
	}
	
	
	public int attemptsMade(String userid, int testid) {
		int uid = getUID(userid);
		int attempts;
		try {
			attempts = jdbcTemplate.queryForObject(Query.ATTEMPTS_MADE, new Object[] { uid, testid }, Integer.class);
		} catch (Exception e) {
			return 0;
		}
		return attempts;
	}

	public boolean checkEligibility(String userid, int testid) {
		int attemptsMade = attemptsMade(userid,testid);
		int attemptsAllowed = jdbcTemplate.queryForObject(Query.GET_NO_OF_ATTEMPTS, new Object[] {testid}, Integer.class);
		return (attemptsMade<attemptsAllowed);
	}
}

class CorrectAnsMapper implements RowMapper<CorrectAns> {

	@Override
	public CorrectAns mapRow(ResultSet rs, int rowNum) throws SQLException {
		CorrectAns correctAns = new CorrectAns();
		correctAns.setName(rs.getString("name"));
		correctAns.setQid(rs.getInt("qid"));
		correctAns.setScore(rs.getInt("score"));
		correctAns.setAid(rs.getInt("aid"));
		return correctAns;
	}

}