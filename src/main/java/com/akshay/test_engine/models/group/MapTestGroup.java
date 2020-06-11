package com.akshay.test_engine.models.group;

public class MapTestGroup {
private int testid;
private int gid;
public int getTestid() {
	return testid;
}
public void setTestid(int testid) {
	this.testid = testid;
}
public int getGid() {
	return gid;
}
public void setGid(int gid) {
	this.gid = gid;
}
@Override
public String toString() {
	return "MapTestGroup [testid=" + testid + ", gid=" + gid + "]";
}

}
