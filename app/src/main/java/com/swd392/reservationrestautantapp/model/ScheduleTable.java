package com.swd392.reservationrestautantapp.model;

import java.sql.Date;
import java.sql.Time;

public class ScheduleTable {
    private int id;

    private Time startTime;
    private Time endTime;
    private Date date;

    private TableRestautant tableRestautant;

    public int getId() {
        return id;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public TableRestautant getTableRestautant() {
        return tableRestautant;
    }

    public void setTableRestautant(TableRestautant tableRestautant) {
        this.tableRestautant = tableRestautant;
    }
}
