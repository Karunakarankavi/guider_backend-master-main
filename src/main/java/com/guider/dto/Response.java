package com.guider.dto;

public class Response {
    private boolean success;
    private String message;
    private Long userId; // Optional, can be null if not applicable
    private String error;

    public Response() {}

    public Response(boolean success, String message, Long userId, String error) {
        this.success = success;
        this.message = message;
        this.userId = userId;
        this.error = error;
    }

    // Getters and Setters
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
