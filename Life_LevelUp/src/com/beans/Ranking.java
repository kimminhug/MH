package com.beans;

import java.io.Serializable;

public class Ranking implements Serializable {
	private static final long serialVersionUID = 1L;
	private String ID, name, intro;
	private int level, up_level;
	
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getUp_level() {
		return up_level;
	}
	public void setUp_level(int up_level) {
		this.up_level = up_level;
	}
}
