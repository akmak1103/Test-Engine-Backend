package com.akshay.test_engine.models.user;

import java.util.List;

import org.springframework.stereotype.Component;

import com.akshay.test_engine.utils.ReturnType;

@Component
public class UserInfo implements ReturnType{ 
	private String name;
	private String userid;
	private String roleName;
	private long phone;
	private String email;
	private String address;
	private List<Right> rights;
	
	public List<Right> getRights() {
		return rights;
	}
	public void setRights(List<Right> rights) {
		this.rights = rights;
	}
	public String getUserid() {
		if (userid==null)return "";
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getRoleName() {
		if (roleName==null)return "";
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getName() {
		if (name==null)return "";
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getPhone() {
		return phone;
	}
	public void setPhone(long phone) {
		this.phone = phone;
	}
	public String getEmail() {
		if (email==null)return "";
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		if (email==null)return "";
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "UserInfo [name=" + name + ", userid=" + userid + ", roleName=" + roleName + ", phone=" + phone
				+ ", email=" + email + ", address=" + address + ", rights=" + rights + "]";
	}
	
	
}
