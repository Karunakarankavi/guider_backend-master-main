package com.guider.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guider.entity.Interviewer;

public interface InterviewerRepo extends JpaRepository<Interviewer,Long> {

}
