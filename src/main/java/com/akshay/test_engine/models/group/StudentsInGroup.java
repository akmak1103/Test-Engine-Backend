package com.akshay.test_engine.models.group;

import java.util.Arrays;

public class StudentsInGroup {
private int gid;
private String[] mailids;
public int getGid() {
	return gid;
}
public void setGid(int gid) {
	this.gid = gid;
}
public String[] getMailids() {
	return mailids;
}
public void setMailids(String[] mailids) {
	this.mailids = mailids;
}
@Override
public String toString() {
	return "StudentsInGroup [gid=" + gid + ", mailids=" + Arrays.toString(mailids) + "]";
}
}
