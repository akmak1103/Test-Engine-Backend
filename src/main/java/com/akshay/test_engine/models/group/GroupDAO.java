package com.akshay.test_engine.models.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.akshay.test_engine.utils.Query;

@Repository
public class GroupDAO {
	@Autowired
	JdbcTemplate jdbcTemplate;

	public int createGroup(Group group) {
		int records = 0;
		try {
			records = jdbcTemplate.update(Query.CREATE_GROUP_SQL, group.getName(), group.getDescr());
		} catch (Exception e) {
			return records;
		}
		return records;
	}

	public int getGID(String groupName) {
		int gid = jdbcTemplate.queryForObject(Query.GET_GROUP_ID, new Object[] { groupName }, Integer.class);
		return gid;
	}

	public int mapTest(int testid, int gid) {
		int records = 0;
		try {
			records = jdbcTemplate.update(Query.TEST_GROUP_MAPPING, testid, gid);
		} catch (Exception e) {
			return records;
		}
		return (records);
	}

	public int mapUser(int gid, int uid) {
		int records = 0;
		try {records = jdbcTemplate.update(Query.USER_GROUP_MAPPING, gid, uid);}
		catch (Exception e) {
			return records;
		}
		return records;
	}
	
	public int getUID(String userid) {
		int uid=0;
		try {
			uid = jdbcTemplate.queryForObject(Query.GET_UID_BY_USERNAME, new Object[] { userid }, Integer.class);
		} catch (Exception e) {
			return 0;
		}
		return uid;
	}
	
	public int addStudents(int gid,String[] mailids)
	{
		int studentsAdded = 0;
		for (int idx=0;idx<mailids.length;idx++)
		{
			int uid = jdbcTemplate.queryForObject(Query.GET_UID_BY_EMAIL, new Object[] { mailids[idx] }, Integer.class);
			if(mapUser(gid, uid)>0)
				studentsAdded+=1;
		}
		return studentsAdded;
	}
}