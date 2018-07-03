package com.canyou.model.Result;

public class ResultVO {
	
	private String name;
	private int requirement;
	private int score;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getRequirement() {
		return requirement;
	}
	public void setRequirement(int requirement) {
		this.requirement = requirement;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
	public String getResult(){
		String type = (name.contains("����"))? "���� ����" : "����";
		if( requirement > score ){
			return (requirement - score) + type + "�� �����մϴ�.";
		}
		if(requirement == score){
			return "�̼� ����� �����߽��ϴ�.";
		}
		return (score-requirement) + type +"�� �ʰ� �̼��߽��ϴ�";
	}
	
	public boolean isPassed(){
		return (requirement <= score);
	}
}
