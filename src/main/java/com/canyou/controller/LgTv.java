package com.canyou.controller;

public class LgTv implements TV{

	@Override
	public void powerOn() {
		System.out.println("LG ON");
	}

	@Override
	public void powerOff() {
		System.out.println("LG OFF");
	}

	@Override
	public void volumeUp() {
		System.out.println("LG UP");
	}

	@Override
	public void volumeDown() {
		System.out.println("LG DOWN");
	}

}
