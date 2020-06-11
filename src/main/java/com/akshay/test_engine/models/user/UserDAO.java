package com.akshay.test_engine.models.user;

import com.akshay.test_engine.models.test.TestsInfo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.akshay.test_engine.models.group.GroupInfo;
import com.akshay.test_engine.models.test.ScoreHistory;
import com.akshay.test_engine.models.test.Test;
import com.akshay.test_engine.utils.Query;

@Repository
public class UserDAO {

	static List<Test> tests;
	static String userid;
	@Autowired
	JdbcTemplate jdbcTemplate;

	static List<Right> rights;
	@SuppressWarnings("unused")
	private Logger logger = Logger.getLogger(UserDAO.class);

	public UserInfo doLogin(User user) {
		rights = new ArrayList<>();
		List<UserInfo> list = jdbcTemplate.query(Query.LOGIN_SQL, new Object[] { user.getEmail(), user.getPassword() },
				new UserInfoMapper());
		if (list == null) {
			return null;
		}
		if (list.size() == 0) {
			return null;
		}
		list.get(0).setRights(rights);
		return list.get(0);
	}

	public List<Test> fetchTests(String userid) {
		tests = new ArrayList<Test>();
		jdbcTemplate.query(Query.GET_TESTS, new Object[] { userid }, new TestMapper());
		return tests;
	}

	public TestsInfo assignedTests(String userid) {
		UserDAO.userid = userid;
		tests = new ArrayList<Test>();
		jdbcTemplate.query(Query.ASSIGNED_TESTS, new Object[] { userid }, new AssignedTestMapper());
		Test[] allTests = new Test[tests.size()];
		TestsInfo assignedTests = new TestsInfo();
		assignedTests.setTests(tests.toArray(allTests));
		return assignedTests;
	}

	public int signup(User user) {
		int records;
		try {
			records = jdbcTemplate.update(Query.SIGNUP, new Object[] { user.getUserid(), user.getPassword(),
					user.getName(), user.getAddress(), user.getPhone(), user.getEmail() });
		} catch (DuplicateKeyException e) {
			return 0;
		}
		return records;
	}

	public void mapRole(String userid, String role) {
		int uid = jdbcTemplate.queryForObject(Query.GET_UID_BY_USERNAME, new Object[] { userid }, Integer.class);
		int roleid = jdbcTemplate.queryForObject(Query.GET_ROLEID, new Object[] { role }, Integer.class);
		jdbcTemplate.update(Query.MAP_USER_ROLE, new Object[] { uid, roleid });
	}

	public List<GroupInfo> groups(String userid) {
		int uid = jdbcTemplate.queryForObject(Query.GET_UID_BY_USERNAME, new Object[] { userid }, Integer.class);
		return jdbcTemplate.query(Query.GET_GROUP_WITH_STUDENTS, new Object[] { uid }, new GroupInfoExtractor());
	}

	public List<ScoreHistory> previousTests(String userid) {
		return jdbcTemplate.query(Query.GET_PREVIOUS_TEST_SCORES,new Object[] {userid}, new PreviousScoreExtractor());
	}
}

class UserInfoMapper implements RowMapper<UserInfo> {

	@Override
	public UserInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		UserInfo userInfo = new UserInfo();
		userInfo.setName(rs.getString("uname"));
		userInfo.setUserid(rs.getString("userid"));
		userInfo.setRoleName(rs.getString("rolename"));
		userInfo.setAddress(rs.getString("address"));
		userInfo.setEmail(rs.getString("email"));
		userInfo.setPhone(rs.getLong("phone"));
		Right right = new Right();
		right.setName(rs.getString("rightname"));
		right.setUrl(rs.getString("url"));
		UserDAO.rights.add(right);
		return userInfo;
	}

}

class TestMapper implements RowMapper<Test> {

	@Override
	public Test mapRow(ResultSet rs, int rowNum) throws SQLException {
		Test test = new Test();
		test.setId(rs.getInt("testid"));
		test.setName(rs.getString("name"));
		test.setDescr(rs.getString("descr"));
		test.setDuration(rs.getInt("duration"));
		test.setNoOfAttempts(rs.getInt("attempts"));
		test.setPassingScore(rs.getInt("passingScore"));
		UserDAO.tests.add(test);
		return null;
	}

}

class GroupInfoExtractor implements ResultSetExtractor<List<GroupInfo>> {

	@Override
	public List<GroupInfo> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<GroupInfo> allGroups = new ArrayList<GroupInfo>();
		boolean isLast = false;
		String[] s = {""};
		while (rs.next()) {
			GroupInfo gInfo = new GroupInfo();
			gInfo.setName(rs.getString("name"));
			gInfo.setDescr(rs.getString("descr"));
			List<String> students = new ArrayList<String>();
			while (rs.getString("name").equals(gInfo.getName())) {
				students.add(rs.getString("uname"));
				if(rs.isLast()) {
					isLast=true;
					break;
				}
				else rs.next();
			}
			gInfo.setStudents(students.toArray(s));
			allGroups.add(gInfo);
			if(!isLast)
				rs.previous();
		}
		return allGroups;
	}
}

class PreviousScoreExtractor implements ResultSetExtractor<List<ScoreHistory>> {

	@Override
	public List<ScoreHistory> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<ScoreHistory> previousScores = new ArrayList<ScoreHistory>();
		boolean isLast = false;
		Integer s[] = {0};
		while (rs.next()) {
			ScoreHistory sInfo = new ScoreHistory();
			sInfo.setName(rs.getString("name"));
			sInfo.setMaxScore(rs.getInt("maxscore"));
			List<Integer> scores = new ArrayList<Integer>();
			while (rs.getString("name").equals(sInfo.getName())) {
				scores.add(rs.getInt("score"));
				if(rs.isLast()) {
					isLast=true;
					break;
				}
				else rs.next();
			}
			sInfo.setScore(scores.toArray(s));
			previousScores.add(sInfo);
			if(!isLast)
				rs.previous();
		}
		return previousScores;
	}
}


class AssignedTestMapper implements RowMapper<Test> {

	@Override
	public Test mapRow(ResultSet rs, int rowNum) throws SQLException {
		Test test = new Test();
		test.setId(rs.getInt("testid"));
		test.setName(rs.getString("name"));
		test.setDescr(rs.getString("descr"));
		test.setDuration(rs.getInt("duration"));
		test.setNoOfAttempts(rs.getInt("attempts"));
		test.setPassingScore(rs.getInt("passingScore"));
		UserDAO.tests.add(test);
		return null;
	}
}