package com.beans;

import java.io.Serializable;

public class Board implements Serializable {
	private static final long serialVersionUID = 1L;
	private String day, name, contents;
	private int level, good, bad, reple, views;
	
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContents() {
		return contents;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public int getGood() {
		return good;
	}
	public void setGood(int good) {
		this.good = good;
	}
	public int getBad() {
		return bad;
	}
	public void setBad(int bad) {
		this.bad = bad;
	}
	public int getReple() {
		return reple;
	}
	public void setReple(int reple) {
		this.reple = reple;
	}
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}
}
