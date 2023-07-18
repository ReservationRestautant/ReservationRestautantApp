package com.swd392.reservationrestautantapp.model;

public class DataLogin {
    private UserSystem userSystem;
    private String token;

    public UserSystem getUserSystem() {
        return userSystem;
    }

    public void setUserSystem(UserSystem userSystem) {
        this.userSystem = userSystem;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
