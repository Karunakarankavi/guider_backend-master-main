package com.guider.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guider.dto.Response;
import com.guider.entity.MockInterview;
import com.guider.entity.User;
import com.guider.repository.MockInterviewRespository;
import com.guider.services.MockInterviewService;

@RestController
@RequestMapping("/mock")
public class MockInterviwer {
	
	private final MockInterviewService mockInterviewService ;
	
	public MockInterviwer(MockInterviewService mockInterviewService ) {
		this.mockInterviewService = mockInterviewService;
	}
	
	@PostMapping("/register")
	public ResponseEntity<Response> registerInterview(@RequestBody MockInterview mockinterview ) {
		System.out.println("print ln register");
		return mockInterviewService.bookInterview(mockinterview);

	};
	
	@PostMapping("/test")
	public void test(@RequestBody MockInterview mockinterview ) {
		System.out.println("print");
	}
}
