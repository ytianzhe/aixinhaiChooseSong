package com.org.ixinhai.entity;

import java.sql.Timestamp;

/**
* @author Tony
* @version 创建时间：2018年7月31日 下午7:05:09
* @ClassName 类名称
* @Description 类描述
*/
public class UserInformation {
private int id;
private String employeeName;
private String employeeUserName;
private String employeePassWord;
private Timestamp firstTime;
private int status;
private String UserAuthority;
private String  employeeTelNumber;

public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getEmployeeName() {
	return employeeName;
}
public void setEmployeeName(String employeeName) {
	this.employeeName = employeeName;
}
public String getEmployeeUserName() {
	return employeeUserName;
}
public void setEmployeeUserName(String employeeUserName) {
	this.employeeUserName = employeeUserName;
}
public String getEmployeePassWord() {
	return employeePassWord;
}
public void setEmployeePassWord(String employeePassWord) {
	this.employeePassWord = employeePassWord;
}
public Timestamp getFirstTime() {
	return firstTime;
}
public void setFirstTime(Timestamp firstTime) {
	this.firstTime = firstTime;
}
public int getStatus() {
	return status;
}
public void setStatus(int status) {
	this.status = status;
}
public String getUserAuthority() {
	return UserAuthority;
}
public void setUserAuthority(String userAuthority) {
	UserAuthority = userAuthority;
}
public String getEmployeeTelNumber() {
	return employeeTelNumber;
}
public void setEmployeeTelNumber(String employeeTelNumber) {
	this.employeeTelNumber = employeeTelNumber;
}

}
