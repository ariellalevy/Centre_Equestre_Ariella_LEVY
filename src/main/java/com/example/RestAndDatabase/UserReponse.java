package com.example.RestAndDatabase;

public class UserReponse {
    private int status;
    private String type;
    private String message;
    private User userReponse;

    public UserReponse(int status, String type, String message, User userReponse) {
        this.status = status;
        this.type = type;
        this.message = message;
        this.userReponse = userReponse;
    }

    public int getStatus() {
        return status;
    }

    public String getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    public User getUserReponse() {
        return userReponse;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setUserReponse(User userReponse) {
        this.userReponse = userReponse;
    }
}
