package com.akshay.test_engine.models.group;

public class Group {
private int gid;
private String name;
private String descr;
private String userid;
private int testid;
private String[] mailids;
public int getGid() {
	return gid;
}
public void setGid(int gid) {
	this.gid = gid;
}
public String getName() {
	if (name == null) return "";
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getDescr() {
	if (descr == null) return "";
	return descr;
}
public void setDescr(String descr) {
	this.descr = descr;
}
public String getUserid() {
	return userid;
}
public void setUserid(String userid) {
	this.userid = userid;
}
public int getTestid() {
	return testid;
}
public void setTestid(int testid) {
	this.testid = testid;
}
public String[] getMailids() {
	return mailids;
}
public void setMailids(String[] mailids) {
	this.mailids = mailids;
}
}
