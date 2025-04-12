package com.guider.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.guider.dto.Response;
import com.guider.entity.JobDetails;
import com.guider.entity.MockInterview;
import com.guider.repository.MockInterviewRespository;

@Service

public class MockInterviewService {
	
	private final MockInterviewRespository mockInterviewRespository ;
	
	public MockInterviewService (MockInterviewRespository mockInterviewRespository) {
		this.mockInterviewRespository = mockInterviewRespository;
		
	}
	
	public ResponseEntity<Response>  postJob(MockInterview mockInterview){
        Map<String, Object> response = new HashMap<>();
        try {
        	MockInterview savedmockInterview = mockInterviewRespository.save(mockInterview);
            return ResponseEntity.status(HttpStatus.CREATED).body(
            		new Response(true, "Interview scheduled successfully!", savedmockInterview.getId(), null));	
		}catch(Exception e) {
             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(false, "An error occurred while registering the user.", null, e.getMessage()));
		}
		
	
	}
	
	


}
