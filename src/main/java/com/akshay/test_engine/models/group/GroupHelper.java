package com.akshay.test_engine.models.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GroupHelper {
	@Autowired
	GroupDAO dao;

	public int createGroup(Group group) {
		int groupCreated = dao.createGroup(group);
		int success = 0;
		int gid = 0;
		if (groupCreated > 0) {
			gid = dao.getGID(group.getName());
			success = dao.mapUser(gid, dao.getUID(group.getUserid()));
			success = addTest(group.getTestid(), gid);
			success = addStudents(gid, group.getMailids());
		}
		return success;
	}

	private int addTest(int testid, int gid) {
		return dao.mapTest(testid, gid);
	}
	
	private int addStudents(int gid,String[] mailids) {
		return dao.addStudents(gid, mailids);
	}
}