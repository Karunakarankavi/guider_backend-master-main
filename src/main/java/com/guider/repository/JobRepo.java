package com.guider.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guider.entity.JobDetails;


public interface JobRepo extends JpaRepository< JobDetails , Long>  {

}
