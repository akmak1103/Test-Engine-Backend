package com.akshay.test_engine.models.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupService {
	@Autowired
	GroupHelper helper;

	public int createGroup(Group group) {
		return helper.createGroup(group);
	}
//	public int addTest (int testid, int gid) {
//		return helper.addTest(testid, gid);
//	}
//	
//	public int addStudents(int gid, String[] mailids) {
//		return helper.addStudents(gid, mailids);
//	}
}
