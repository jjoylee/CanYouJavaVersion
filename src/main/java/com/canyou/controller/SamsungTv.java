package com.canyou.controller;

public class SamsungTv implements TV{

	@Override
	public void powerOn() {
		System.out.println("SAMSUNG ON");
	}

	@Override
	public void powerOff() {
		System.out.println("SAMSUNG OFF");
	}

	@Override
	public void volumeUp() {
		System.out.println("SAMSUNG UP");
	}

	@Override
	public void volumeDown() {
		System.out.println("SAMSUNG DOWN");
	}

}
