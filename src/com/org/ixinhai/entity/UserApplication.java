package com.org.ixinhai.entity;

import java.sql.Timestamp;

/**
* @author Tony
* @version 创建时间：2018年7月25日 下午2:42:05
* @ClassName 类名称
* @Description 类描述
*/
public class UserApplication {

	private int id;
	private String  chooseUserName;
	private String 	chooseDepartment;
	private String  chooseSongName;
	private String  chooseSingerName;
	private String 	chooseWish;
	private String   chooseUserTelNumber;
	private Timestamp firstAddTime;
	private int 	createId;
	private int status;
	private String 	information;
	private String statusValue;
	private Timestamp updateTime; 
	private String nickName;
	private int msgCount;
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getChooseUserName() {
		return chooseUserName;
	}
	public void setChooseUserName(String chooseUserName) {
		this.chooseUserName = chooseUserName;
	}
	public String getChooseDepartment() {
		return chooseDepartment;
	}
	public void setChooseDepartment(String chooseDepartment) {
		this.chooseDepartment = chooseDepartment;
	}

	
	
	public String getChooseSongName() {
		return chooseSongName;
	}
	public void setChooseSongName(String chooseSongName) {
		this.chooseSongName = chooseSongName;
	}

	
	public String getChooseSingerName() {
		return chooseSingerName;
	}
	public void setChooseSingerName(String chooseSingerName) {
		this.chooseSingerName = chooseSingerName;
	}
	public String getChooseWish() {
		return chooseWish;
	}
	public void setChooseWish(String chooseWish) {
		this.chooseWish = chooseWish;
	}
	
	
	
	public String getChooseUserTelNumber() {
		return chooseUserTelNumber;
	}
	public void setChooseUserTelNumber(String chooseUserTelNumber) {
		this.chooseUserTelNumber = chooseUserTelNumber;
	}
	public Timestamp getFirstAddTime() {
		return firstAddTime;
	}
	public void setFirstAddTime(Timestamp firstAddTime) {
		this.firstAddTime = firstAddTime;
	}
	public int getCreateId() {
		return createId;
	}
	public void setCreateId(int createId) {
		this.createId = createId;
	}
	
	
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getInformation() {
		return information;
	}
	public void setInformation(String information) {
		this.information = information;
	}
	public String getStatusValue() {
		return statusValue;
	}
	public void setStatusValue(String statusValue) {
		this.statusValue = statusValue;
	}
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	public int getMsgCount() {
		return msgCount;
	}
	public void setMsgCount(int msgCount) {
		this.msgCount = msgCount;
	}
	
	
}
