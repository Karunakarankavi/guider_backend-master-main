package com.guider.entity;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;

@Entity
public class UserStats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer noofInterview;
    private Long total_score;
    private Integer avg;

    @Column(columnDefinition = "json")
    private String studyMaterial; // Stored as JSON string

    @Column(columnDefinition = "json")
    private String skills; // Stored as JSON string

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getStudyMaterial() {
        return studyMaterial;
    }

    public void setStudyMaterial(String studyMaterial) {
        this.studyMaterial = studyMaterial;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // Utility methods
    public List<Integer> getStudyMaterialList() throws JsonProcessingException {
        return convertJsonToList(studyMaterial, new TypeReference<List<Integer>>() {});
    }

    public void setStudyMaterialList(List<Integer> list) throws JsonProcessingException {
        this.studyMaterial = convertListToJson(list);
    }

    public List<String> getSkillsList() throws JsonProcessingException {
        return convertJsonToList(skills, new TypeReference<List<String>>() {});
    }

    public void setSkillsList(List<String> list) throws JsonProcessingException {
        this.skills = convertListToJson(list);
    }

    private <T> List<T> convertJsonToList(String json, TypeReference<List<T>> typeRef) throws JsonProcessingException {
        return new ObjectMapper().readValue(json, typeRef);
    }

    private <T> String convertListToJson(List<T> list) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(list);
    }
}
