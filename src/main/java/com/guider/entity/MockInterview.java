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
	public Long getInterviewerId() {
		return interviewerId;
	}
	public void setInterviewerId(Long interviewerId) {
		this.interviewerId = interviewerId;
	}
	public Long getIntervieweeId() {
		return intervieweeId;
	}
	public void setIntervieweeId(Long intervieweeId) {
		this.intervieweeId = intervieweeId;
	}
	
	 @JsonProperty("dateTime") // This ensures correct mapping from JSON
	    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	    @Column(nullable = false)
	private LocalDateTime interviewDateTime;
    private Long interviewerId;
    private Long intervieweeId;
}
