package com.guider.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.guider.entity.Interviewer;
import com.guider.repository.InterviewerRepo;

@Service

public class InterviewerService {

	private final InterviewerRepo interviewerRepo;
	
	public InterviewerService(InterviewerRepo interviewerRepo) {
		this.interviewerRepo = interviewerRepo;
	}
	
	public ResponseEntity<Map<String, Object>>  getAllInterviewer(){
        Map<String, Object> response = new HashMap<>();
		try {
			List<Interviewer> allInterviewer = this.interviewerRepo.findAll();
			response.put("success", true);
            response.put("message", "All interviewers");
            response.put("allInterviewers", allInterviewer);
            return ResponseEntity.status(HttpStatus.OK).body(response);
		}catch(Exception e) {
			 response.put("success", false);
             response.put("message", "An error occurred while finding interviewer.");
             response.put("error", e.getMessage()); // Provide detailed error message for debugging
             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
	
	public ResponseEntity<Map<String, Object>>  saveInterviewer(Interviewer interviewer){
        Map<String, Object> response = new HashMap<>();
        try {
			Interviewer savedInterviewer = this.interviewerRepo.save(interviewer);
			response.put("success", true);
            response.put("message", "Interview registered successfully");
            response.put("interviewerId", savedInterviewer.getInterviewer_id());
            return ResponseEntity.status(HttpStatus.OK).body(response);
		}catch(Exception e) {
			 response.put("success", false);
             response.put("message", "An error occurred while registering interviewer.");
             response.put("error", e.getMessage()); // Provide detailed error message for debugging
             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
        
	}
	
	
	
	
}
