package com.guider.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.guider.entity.JobDetails;
import com.guider.repository.JobRepo;

@Service
public class JobService {
	
	private final JobRepo jobrepo;
	
	public JobService(JobRepo jobrepo) {
		this.jobrepo = jobrepo;
	}
	
	public ResponseEntity<Map<String, Object>>  postJob(JobDetails jobdetails){
        Map<String, Object> response = new HashMap<>();
		try {
			JobDetails savedJob = jobrepo.save(jobdetails);
			response.put("success", true);
            response.put("message", "Job posted successfully!");
            response.put("jobId", savedJob.getJob_id()); // Returning the saved user ID
            return ResponseEntity.status(HttpStatus.CREATED).body(response);	
		}catch(Exception e) {
			 response.put("success", false);
             response.put("message", "An error occurred while posting job.");
             response.put("error", e.getMessage()); // Provide detailed error message for debugging
             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
		
	}

}
