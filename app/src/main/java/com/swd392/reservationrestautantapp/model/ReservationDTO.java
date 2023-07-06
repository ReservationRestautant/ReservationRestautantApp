package com.swd392.reservationrestautantapp.model;

import java.sql.Date;
import java.sql.Time;

public class ReservationDTO {
    private Time startTime;
    private Time endTime;
    private Date date;
    private int number_guest;
    private String description;
    private String phone_cus;
    private String phone_guest;

    @Override
    public String toString() {
        return "ReservationDTO{" +
                "startTime=" + startTime +
                ", endTime=" + endTime +
                ", date=" + date +
                ", number_guest=" + number_guest +
                ", description='" + description + '\'' +
                ", phone_cus='" + phone_cus + '\'' +
                ", phone_guest='" + phone_guest + '\'' +
                '}';
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

    public String getPhone_cus() {
        return phone_cus;
    }

    public void setPhone_cus(String phone_cus) {
        this.phone_cus = phone_cus;
    }

    public String getPhone_guest() {
        return phone_guest;
    }

    public void setPhone_guest(String phone_guest) {
        this.phone_guest = phone_guest;
    }
}
