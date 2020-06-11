package com.akshay.test_engine.models.group;

import java.util.Arrays;

public class GroupInfo {
private String name;
private String descr;
private String[] students;
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getDescr() {
	return descr;
}
public void setDescr(String descr) {
	this.descr = descr;
}
public String[] getStudents() {
	return students;
}
public void setStudents(String[] students) {
	this.students = students;
}
@Override
public String toString() {
	return "GroupInfo [name=" + name + ", descr=" + descr + ", students=" + Arrays.toString(students) + "]";
}

}