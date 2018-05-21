package com.canyou.model.Account;

public class AccountVO {
	private int id;
	private String password;
	private String email;
	private String state;
	
	public int getId(){
		return id;
	}
	public String getPassword(){
		return password;
	}
	public String getEmail(){
		return email;
	}
	public String getState(){
		return state;
	}
	public void setId(int id){
		this.id = id;
	}
	public void setPassword(String password){
		this.password = password;
	}
	public void setEmail(String email){
		this.email = email;
	}
	public void setState(String state){
		this.state = state;
	}
}
