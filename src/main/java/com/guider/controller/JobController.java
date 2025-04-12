package com.guider.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guider.entity.JobDetails;
import com.guider.services.JobService;

@RestController
@RequestMapping("/job")
public class JobController {
	
	private final JobService jobService;
	
	public JobController(JobService jobService) {
		this.jobService = jobService;
		
	}
	
	@PostMapping("/post")
    public ResponseEntity<Map<String, Object>>  postJob(@RequestBody JobDetails jobDetails) {
		return jobService.postJob(jobDetails);
	}
	
	
}
