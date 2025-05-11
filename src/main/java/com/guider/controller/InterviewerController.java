package com.guider.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guider.services.InterviewerService;

@RestController
@RequestMapping("/interviewer")
public class InterviewerController {
	
	private final InterviewerService interviewerService;
	
	public InterviewerController(InterviewerService interviewerService) {
		this.interviewerService = interviewerService;
	}
	
	@GetMapping("/getAll")
	public  ResponseEntity<Map<String, Object>>  postJob(){
		return interviewerService.getAllInterviewer();
	}

}
