package com.akshay.test_engine.models.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {
	@Autowired
	UserService userService;
	
	public TeacherDashboard teacher(String userid) {
		TeacherDashboard dashboard = new TeacherDashboard();
		dashboard.setTests(userService.fetchTests(userid));
		dashboard.setGroups(userService.groups(userid));
		return dashboard;
	}
	
	public StudentDashboard student(String userid) {
		StudentDashboard dashboard = new StudentDashboard();
		dashboard.setAssignedTests(userService.assignedTests(userid));
		dashboard.setScores(userService.scores(userid));
		return dashboard;
	}
}
