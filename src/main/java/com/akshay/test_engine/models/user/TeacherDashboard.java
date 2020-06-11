package com.akshay.test_engine.models.user;

import java.util.List;

import com.akshay.test_engine.models.group.GroupInfo;
import com.akshay.test_engine.models.test.Test;
import com.akshay.test_engine.utils.ReturnType;

public class TeacherDashboard implements ReturnType {
private List<Test> tests;
private List<GroupInfo> groups;

public List<GroupInfo> getGroups() {
	return groups;
}
public void setGroups(List<GroupInfo> groups) {
	this.groups = groups;
}
public List<Test> getTests() {
	return tests;
}
public void setTests(List<Test> tests) {
	this.tests = tests;
}
}
