package com.guider.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guider.entity.MockInterview;


public interface MockInterviewRespository extends JpaRepository<MockInterview, Long>  {

}
