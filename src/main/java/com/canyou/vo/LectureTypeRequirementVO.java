package com.canyou.vo;

public class LectureTypeRequirementVO {
	private int id;
	private int accountId;
	private int lectureTypeId;
	private int cutline;
	private String lectureTypeName;
	private String lectureCategoryName;
	private int lectureCategoryId;
	
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
	public String getLectureTypeName() {
		return lectureTypeName;
	}
	public void setLectureTypeName(String lectureTypeName) {
		this.lectureTypeName = lectureTypeName;
	}
	public String getLectureCategoryName() {
		return lectureCategoryName;
	}
	public void setLectureCategoryName(String lectureCategoryName) {
		this.lectureCategoryName = lectureCategoryName;
	}
	public int getLectureCategoryId() {
		return lectureCategoryId;
	}
	public void setLectureCategoryId(int lectureCategoryId) {
		this.lectureCategoryId = lectureCategoryId;
	}
}
