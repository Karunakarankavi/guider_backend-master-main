package com.guider.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Interviewer {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long interviewer_id;
	public Long getInterviewer_id() {
		return interviewer_id;
	}
	public void setInterviewer_id(Long interviewer_id) {
		this.interviewer_id = interviewer_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getSkills() {
		return skills;
	}
	public void setSkills(String skills) {
		this.skills = skills;
	}
	public int getExperience() {
		return experience;
	}
	public void setExperience(int experience) {
		this.experience = experience;
	}
	private String name;
	private String email;
	private String phoneNumber;
	private String skills;
	private int experience;

}
