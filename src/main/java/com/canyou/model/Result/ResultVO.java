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
		String type = (name.contains("영역"))? "개의 영역" : "학점";
		if( requirement > score ){
			return (requirement - score) + type + "이 부족합니다.";
		}
		if(requirement == score){
			return "이수 요건을 만족했습니다.";
		}
		return (score-requirement) + type +"을 초과 이수했습니다";
	}
	
	public boolean isPassed(){
		return (requirement <= score);
	}
}
