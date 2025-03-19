package com.guider.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guider.entity.MockInterview;
import com.guider.entity.User;
import com.guider.repository.MockInterviewRespository;

@RestController
@RequestMapping("/mock")
public class MockInterviwer {
	
	private final MockInterviewRespository mockInterviewRespository ;
	
	public MockInterviwer(MockInterviewRespository mockInterviewRespository ) {
		this.mockInterviewRespository = mockInterviewRespository;
	}
	
	@PostMapping("/register")
	public String registerInterview(@RequestBody MockInterview mockinterview ) {
		mockinterview.setInterviewDateTime(mockinterview.getInterviewDateTime());
		mockinterview.setInterviewerId(mockinterview.getInterviewerId());
		mockinterview.setIntervieweeId(mockinterview.getIntervieweeId());
		mockInterviewRespository.save(mockinterview);
		return null;

	};
}
