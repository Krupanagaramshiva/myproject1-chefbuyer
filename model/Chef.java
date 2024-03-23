package com.food.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Chef {
	@Id
	private String chefid;
	private String name;
	private int age;
	private String gender;
	private String gmail;
	private Long mobile;
	private String profileimg;
	private String profession;
	private String experiences;
	private String location;
	public Chef() {
		super();
	}
	public Chef(String chefid, String name, int age, String gender, String gmail, Long mobile, String profileimg,
			String profession, String experiences, String location) {
		super();
		this.chefid = chefid;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.gmail = gmail;
		this.mobile = mobile;
		this.profileimg = profileimg;
		this.profession = profession;
		this.experiences = experiences;
		this.location = location;
	}
	@Override
	public String toString() {
		return "Chef [chefid=" + chefid + ", name=" + name + ", age=" + age + ", gender=" + gender + ", gmail=" + gmail
				+ ", mobile=" + mobile + ", profileimg=" + profileimg + ", profession=" + profession + ", experiences="
				+ experiences + ", location=" + location + "]";
	}
	public String getChefid() {
		return chefid;
	}
	public void setChefid(String chefid) {
		this.chefid = chefid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getGmail() {
		return gmail;
	}
	public void setGmail(String gmail) {
		this.gmail = gmail;
	}
	public Long getMobile() {
		return mobile;
	}
	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}
	public String getProfileimg() {
		return profileimg;
	}
	public void setProfileimg(String profileimg) {
		this.profileimg = profileimg;
	}
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	public String getExperiences() {
		return experiences;
	}
	public void setExperiences(String experiences) {
		this.experiences = experiences;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	

}
