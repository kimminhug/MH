package com.patten;

import java.io.Serializable;

public class Level_DTO implements Serializable {
	private String ID;
	private int level;
	private double exp, req_exp, fat_late;
	
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
	public double getFat_late() {
		return fat_late;
	}
	public void setFat_late(double fat_late) {
		this.fat_late = fat_late;
	}
}
