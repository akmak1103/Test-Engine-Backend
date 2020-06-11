package com.akshay.test_engine.utils;

public interface Query {
	
	//input userid and password
	String LOGIN_SQL = "select userid, user.name as uname, phone, address ,email, role.name as rolename, rights.name as rightname, rights.url from user_mst user, role_mst role, user_role_mapping urmap,right_mst rights,role_right_mapping rrmap  where email=? and password=? and user.uid=urmap.uid and role.roleid=urmap.roleid and rrmap.roleid=role.roleid and rrmap.rightid=rights.rightid";
	
	//input user details
	String SIGNUP = "insert into user_mst (userid,password,name,address,phone,email) values (?,?,?,?,?,?)";

	//input role name
	String GET_ROLEID="select roleid from role_mst where name=?";
	
	//input uid,roleid
	String MAP_USER_ROLE = "insert into user_role_mapping (uid,roleid) values (?,?)";
	
	//input email
	String GET_UID_BY_EMAIL = "select uid from user_mst where email=?";
	
	//input userid
	String GET_TESTS = "select testid,name, descr,duration,attempts,passingScore from test where createdby=?";
	
	//input test information
	String TEST_ADD_SQL = "insert into test (name,descr, duration, attempts, createdby, creationtime, passingscore) values(?,?,?,?,?,?,?)";
	
	//input userid
	String GET_UID_BY_USERNAME = "select uid from user_mst where userid=?";
	
	//input testid and uid
	String TEST_MAPPING_ADD_SQL = "insert into user_test_mapping (testid,uid) values(?,?)";
	
	//input testid
	String DELETE_TEST = "delete from test where testid=?";
	
	//input testname
	String TEST_SELECT_BY_ID = "select testid from test where name=?";
	
	//input question info
	String QUESTION_ADD_SQL = "insert into question (name,descr,score) values (?,?,?)";
	
	//input question name
	String QUESTION_SELECT_BY_ID = "select qid from question where name=?";
	
	//input answer info
	String ANSWER_ADD_SQL = "insert into answer (qid,name,descr,isCorrect) values (?,?,?,?)";
	
	//input testid and qid[]
	String TEST_QUESTION_MAPPING_SQL = "insert into test_question_mapping (testid,qid) values (?,?)";
	
	//input qid
	String GET_QUESTION_BY_ID_SQL = "select question.name as qName,question.descr as qDescr, score, answer.name as aName,answer.descr as aDescr,isCorrect from question inner join answer on question.qid=answer.qid where question.qid=?";
	
	//input testid
	String GET_QUESTION_BY_TESTID_SQL="select q.qid,q.name as qName,score,q.descr as qDescr from test t, question q, test_question_mapping m where t.testid=m.testid and q.qid=m.qid and t.testid=?";
	
	//input group information
	String CREATE_GROUP_SQL= "insert into group_mst (name,descr) values (?,?)";
	
	//input group name
	String GET_GROUP_ID = "select gid from group_mst where name=?";
	
	//input testid
	String GET_NO_OF_ATTEMPTS = "select attempts from test where testid=?";
	
	//input testid and gid
	String TEST_GROUP_MAPPING = "insert into test_group_mapping (testid,gid) values (?,?)";
	
	//input gid and uid
	String USER_GROUP_MAPPING = "insert into user_group_mapping (gid,uid) values (?,?)";
	
	//input uid
	String GET_GROUP_WITH_STUDENTS = "select group_mst.name,group_mst.descr,u.name as uname from user_group_mapping as ugmap,group_mst,(select gid from user_group_mapping where uid=?) as g,user_mst as u,user_role_mapping as urmap where g.gid=ugmap.gid and ugmap.uid=u.uid and urmap.roleid=2 and urmap.uid=u.uid and group_mst.gid=g.gid order by group_mst.name";
	
	//input uid
	String ASSIGNED_TESTS="select test.testid,test.name,test.descr,test.duration,test.passingScore,test.attempts from test,user_group_mapping,test_group_mapping,user_mst where user_mst.userid=? and user_mst.uid=user_group_mapping.uid and user_group_mapping.gid=test_group_mapping.gid and test_group_mapping.testid=test.testid";
	
	//input testid
	String ALL_QUESTIONS_WITH_ANSWERS="select q.qid,q.name as qName,score,q.descr as qDescr,aid,answer.name as aName,answer.descr as aDescr,isCorrect from test t, question q, test_question_mapping m,answer where t.testid=m.testid and q.qid=m.qid and q.qid=answer.qid and t.testid=?";
	
	//input testid
	String CORRECT_ANSWERS_OF_TEST = "select q.qid,score,aid,answer.name from test t, question q, test_question_mapping m,answer where t.testid=m.testid and q.qid=m.qid and q.qid=answer.qid and answer.isCorrect=1 and t.testid=?";
	
	//input scorecard information
	String MAKE_SCORECARD="insert into scorecard (uid,testid,qid,score) values (?,?,?,?) on duplicate key update score=?";
	
	//input uid,testid
	String MARK_ATTEMPTED = "insert into attempts (uid,testid,attemptsCount) values (?,?,?) on duplicate key update attemptsCount=?";
	
	//input uid,testid,score,maxScore
	String ADD_TO_HISTORY = "insert into score_history (uid,testid,score,maxScore) values (?,?,?,?)";
	
	//input userid
	String GET_PREVIOUS_TEST_SCORES = "select test.name,score,maxscore from score_history,user_mst,test where userid=? and user_mst.uid=score_history.uid and test.testid=score_history.testid";
	
	//input uid,testid
	String ATTEMPTS_MADE = "select attemptsCount from attempts where uid=? and testid=?";
}