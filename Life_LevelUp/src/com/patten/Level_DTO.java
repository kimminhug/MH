package com.patten;

import java.io.Serializable;

public class Level_DTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String ID;
	private int level, b_level, e_level, e_exp, e_bef_exp, e_req_exp;
	private double b_exp, average, BMI, BMR, obesity, e_rate;
	
	public double getE_rate() {
		return e_rate;
	}
	public void setE_rate(double e_rate) {
		this.e_rate = e_rate;
	}
	public int getE_bef_exp() {
		return e_bef_exp;
	}
	public void setE_bef_exp(int e_bef_exp) {
		this.e_bef_exp = e_bef_exp;
	}
	public int getE_req_exp() {
		return e_req_exp;
	}
	public void setE_req_exp(int e_req_exp) {
		this.e_req_exp = e_req_exp;
	}
	
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
	public int getB_level() {
		return b_level;
	}
	public void setB_level(int b_level) {
		this.b_level = b_level;
	}
	public int getE_level() {
		return e_level;
	}
	public void setE_level(int e_level) {
		this.e_level = e_level;
	}
	public double getB_exp() {
		return b_exp;
	}
	public void setB_exp(double b_exp) {
		this.b_exp = Double.parseDouble(String.format("%.2f", b_exp));
	}
	public int getE_exp() {
		return e_exp;
	}
	public void setE_exp(int e_exp) {
		this.e_exp = e_exp;
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
			this.BMR = Double.parseDouble(String.format("%1f", BMR));
			
		}else{
			BMR = 655 + (9.6 * weight) + (1.7 * height) - (4.7 * age);
			this.BMR = Double.parseDouble(String.format("%1f", BMR));
		}
	}
	public double getObesity() {
		return obesity;
	}
	public void setObesity(double weight, double average) {
		obesity = (weight - average) / average * 100;
		this.obesity = Double.parseDouble(String.format("%.2f", obesity));
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
