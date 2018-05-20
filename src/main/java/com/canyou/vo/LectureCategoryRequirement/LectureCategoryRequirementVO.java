package com.canyou.vo.LectureCategoryRequirement;

public class LectureCategoryRequirementVO {
	private int id;
	private int accountId;
	private int lectureCategoryId;
	private int cutline;
	private String lectureCategoryName;
	
	public int getId(){
		return id;
	}
	public int getAccountId(){
		return accountId;
	}
	public int getLectureCategoryId(){
		return lectureCategoryId;
	}
	public int getCutline(){
		return cutline;
	}
	public String getLectureCategoryName(){
		return lectureCategoryName;
	}
	public void setId(int id){
		this.id = id;
	}
	public void setAccountId(int accountId){
		this.accountId = accountId;
	}
	public void setLectureCategoryId(int lectureCategoryId){
		this.lectureCategoryId = lectureCategoryId;
	}
	public void setCutline(int cutline){
		this.cutline = cutline;
	}
	public void setLectureCategoryName(String lectureCategoryName){
		this.lectureCategoryName = lectureCategoryName;
	}
}
