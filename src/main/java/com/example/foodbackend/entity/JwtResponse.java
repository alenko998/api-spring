package com.example.foodbackend.entity;

public class JwtResponse {
    private UserEntity userEntity;
    private String jwtToken;

    public JwtResponse(UserEntity userEntity, String jwtToken) {
        this.userEntity = userEntity;
        this.jwtToken = jwtToken;
    }

    public UserEntity getUser() {
        return userEntity;
    }

    public void setUser(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}
