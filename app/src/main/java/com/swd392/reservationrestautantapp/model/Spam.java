package com.swd392.reservationrestautantapp.model;

public class Spam {
    private String phone;

    private short spamDay;  //đếm số lần spam/ 1 ngày hiện tại

    private short spamWeek; //đếm số ngày spam liên tục

    private boolean block;

    private String timeUnBlock;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public short getSpamDay() {
        return spamDay;
    }

    public void setSpamDay(short spamDay) {
        this.spamDay = spamDay;
    }

    public short getSpamWeek() {
        return spamWeek;
    }

    public void setSpamWeek(short spamWeek) {
        this.spamWeek = spamWeek;
    }

    public boolean isBlock() {
        return block;
    }

    public void setBlock(boolean block) {
        this.block = block;
    }

    public String getTimeUnBlock() {
        return timeUnBlock;
    }

    public void setTimeUnBlock(String timeUnBlock) {
        this.timeUnBlock = timeUnBlock;
    }
}
