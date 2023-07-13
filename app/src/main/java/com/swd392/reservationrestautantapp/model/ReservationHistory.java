package com.swd392.reservationrestautantapp.model;

import java.sql.Date;
import java.sql.Time;

public class ReservationHistory {
    private int id;

    private String startTime;
    private String endTime;
    private Date date;
    //30 bàn, mỗi bàn max 6 chỗ
    private int number_guest;

    private String description;

    private boolean status;

    private float price;

    private int discount;

    private String feedback;

    private UserSystem userSysterm;

    private String phone_guest;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getNumber_guest() {
        return number_guest;
    }

    public void setNumber_guest(int number_guest) {
        this.number_guest = number_guest;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public UserSystem getUserSysterm() {
        return userSysterm;
    }

    public void setUserSysterm(UserSystem userSysterm) {
        this.userSysterm = userSysterm;
    }

    public String getPhone_guest() {
        return phone_guest;
    }

    public void setPhone_guest(String phone_guest) {
        this.phone_guest = phone_guest;
    }

    public ReservationHistory() {
    }

    public ReservationHistory(int id, String startTime, String endTime, Date date, int number_guest, String description, boolean status, float price, int discount, String feedback, UserSystem userSysterm, String phone_guest) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = date;
        this.number_guest = number_guest;
        this.description = description;
        this.status = status;
        this.price = price;
        this.discount = discount;
        this.feedback = feedback;
        this.userSysterm = userSysterm;
        this.phone_guest = phone_guest;
    }
}
