package com.canyou.model.SectionRequirement;

public class SectionRequirementVO {
	private int id;
	private int accountId;
	private int lectureTypeId;
	private int cutline;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public int getLectureTypeId() {
		return lectureTypeId;
	}
	public void setLectureTypeId(int lectureTypeId) {
		this.lectureTypeId = lectureTypeId;
	}
	public int getCutline() {
		return cutline;
	}
	public void setCutline(int cutline) {
		this.cutline = cutline;
	}
}
