package com.patten;

import java.io.Serializable;

public class Member_DTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String ID, salt, hash, name, job, area, intro;
	private int sex, age, height, weight;
	private int sex_vis, age_vis, hei_vis, wei_vis;
	
	
	public int getSex_vis() {
		return sex_vis;
	}
	public void setSex_vis(int sex_vis) {
		this.sex_vis = sex_vis;
	}
	public int getAge_vis() {
		return age_vis;
	}
	public void setAge_vis(int age_vis) {
		this.age_vis = age_vis;
	}
	public int getHei_vis() {
		return hei_vis;
	}
	public void setHei_vis(int hei_vis) {
		this.hei_vis = hei_vis;
	}
	public int getWei_vis() {
		return wei_vis;
	}
	public void setWei_vis(int wei_vis) {
		this.wei_vis = wei_vis;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getHash() {
		return hash;
	}
	public void setHash(String hash) {
		this.hash = hash;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	
}
