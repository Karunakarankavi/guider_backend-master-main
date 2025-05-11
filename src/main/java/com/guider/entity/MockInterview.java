package com.guider.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class MockInterview {
     
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDateTime getInterviewDateTime() {
		return interviewDateTime;
	}
	public void setInterviewDateTime(LocalDateTime interviewDateTime) {
		this.interviewDateTime = interviewDateTime;
	}
	
	
	 @JsonProperty("dateTime") // This ensures correct mapping from JSON
	    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	    @Column(nullable = false)
	private LocalDateTime interviewDateTime;
    public String getInterviewerId() {
		return interviewerId;
	}
	public void setInterviewerId(String interviewerId) {
		this.interviewerId = interviewerId;
	}
	public String getIntervieweeId() {
		return intervieweeId;
	}
	public void setIntervieweeId(String intervieweeId) {
		this.intervieweeId = intervieweeId;
	}


	private String interviewerId;
    private String intervieweeId;
    @Column(nullable = false)
    private String name;
    public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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

    @Column(nullable = false)
	private String phoneNumber;
    @Column(nullable = false)
    private String skills;
    @Column(nullable = false)
    private String pdf;
	public String getPdf() {
		return pdf;
	}
	public void setPdf(String pdf) {
		this.pdf = pdf;
	}

}
