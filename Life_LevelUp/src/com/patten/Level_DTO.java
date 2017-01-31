package com.patten;

import java.io.Serializable;

public class Level_DTO implements Serializable {
	private String ID;
	private int level;
	private double exp, req_exp, average, BMI, BMR, obesity;
	
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public double getExp() {
		return exp;
	}
	public void setExp(double exp) {
		this.exp = exp;
	}
	public double getReq_exp() {
		return req_exp;
	}
	public void setReq_exp(double req_exp) {
		this.req_exp = req_exp;
	}
	public double getBMI() {
		return BMI;
	}
	public void setBMI(double height, double weight) {
		BMI = weight / (height * height / 10000);
		this.BMI = Double.parseDouble(String.format("%.1f", BMI));
	}
	public double getBMR() {
		return BMR;
	}
	public void setBMR(int sex, int age, double height, double weight) {
		if(sex==0){
			BMR = 66 + (13.7 * weight) + (5 * height) - (6.8 * age);
			this.BMR = Double.parseDouble(String.format("%.0f", BMR));
			
		}else{
			BMR = 655 + (9.6 * weight) + (1.7 * height) - (4.7 * age);
			this.BMR = Double.parseDouble(String.format("%.0f", BMR));
		}
	}
	public double getObesity() {
		return obesity;
	}
	public void setObesity(double weight, double average) {
		obesity = (weight - average) / average * 100;
		this.obesity = Double.parseDouble(String.format("%.1f", obesity));
	}
	public double getAverage() {
		return average;
	}
	public void setAverage(int sex, double height) {
		if(sex==0){
			average = (height - 100) * 0.9;
			this.average = Double.parseDouble(String.format("%.2f", average));
			
		}else{
			average = (height - 100) * 0.85;
			this.average = Double.parseDouble(String.format("%.2f", average));
		}
	}
	
}
