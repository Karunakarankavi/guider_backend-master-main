package com.guider.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guider.entity.Interviewer;


public interface InterviewerRepo extends JpaRepository<Interviewer, Long>  {
    Optional<Interviewer> findByUsernameOrEmailOrPhoneNumber(String username, String email, String phoneNumber);

}
