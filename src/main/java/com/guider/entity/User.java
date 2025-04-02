package com.guider.entity;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.io.IOException;
import jakarta.persistence.*;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email"),
        @UniqueConstraint(columnNames = "phoneNumber")
})
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;
    
    public Integer getNoofInterview() {
		return noofInterview;
	}

	public void setNoofInterview(Integer noofInterview) {
		this.noofInterview = noofInterview;
	}

	public Long getTotal_score() {
		return total_score;
	}

	public void setTotal_score(Long total_score) {
		this.total_score = total_score;
	}

	public Integer getAvg() {
		return avg;
	}

	public void setAvg(Integer avg) {
		this.avg = avg;
	}

	private Integer noofInterview;
    private Long total_score;
    private Integer avg;
    
    
    @Column(columnDefinition = "json")  // Store JSON as a String
    private String studyMaterial; // Integer array stored as JSON string

    @Column(columnDefinition = "json")
    private String skills; // String array stored as JSON string

    // Convert JSON String to List<Integer> for studyMaterial
    public List<Integer> getStudyMaterialList() throws JsonMappingException, JsonProcessingException {
        return convertJsonToList(studyMaterial, new TypeReference<List<Integer>>() {});
    }

    public void setStudyMaterialList(List<Integer> studyMaterialList) throws JsonProcessingException {
        this.studyMaterial = convertListToJson(studyMaterialList);
    }

    // Convert JSON String to List<String> for skills
    public List<String> getSkillsList() throws JsonMappingException, JsonProcessingException {
        return convertJsonToList(skills, new TypeReference<List<String>>() {});
    }

    public void setSkillsList(List<String> skillsList) throws JsonProcessingException {
        this.skills = convertListToJson(skillsList);
    }

    // Generic method to convert JSON String to List<T>
    private <T> List<T> convertJsonToList(String json, TypeReference<List<T>> typeRef) throws JsonMappingException, JsonProcessingException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(json, typeRef);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Generic method to convert List<T> to JSON String
    private <T> String convertListToJson(List<T> list) throws JsonProcessingException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(list);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Getters and Setters
    public Long getId() {
        return user_id;
    }

    public void setId(Long id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}


