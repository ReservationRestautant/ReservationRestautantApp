package com.swd392.reservationrestautantapp.model;

import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;
import java.util.List;

public class Reservation {
    private int id;

    private Time startTime;
    private Time endTime;
    private Date date;
      //30 bàn, mỗi bàn max 6 chỗ
    private int number_guest;

    private String description;

    private boolean status;

    private float price;

    private int discount;

    private String feedback;

    private UserSystem userSysterm;

    private String phone_guest;  //dành cho cus ko login vào system, lúc này usersystem nó sẽ gán = unkknow
    private List<TableRestautant> tableRestautants;

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
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

    public List<TableRestautant> getTableRestautants() {
        return tableRestautants;
    }

    public void setTableRestautants(List<TableRestautant> tableRestautants) {
        this.tableRestautants = tableRestautants;
    }
}
