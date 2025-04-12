package com.guider.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guider.entity.User;

public interface UserRepository extends JpaRepository<User, Long>   {
     
    Optional<User> findByUsernameOrEmailOrPhoneNumber(String username, String email, String phoneNumber); 
    
    Optional<User> findByEmailOrPhoneNumber(String email, String phoneNumber); 

    

}
