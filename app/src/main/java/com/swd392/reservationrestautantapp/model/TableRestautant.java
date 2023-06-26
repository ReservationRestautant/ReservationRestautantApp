package com.swd392.reservationrestautantapp.model;

import java.util.List;

public class TableRestautant {
    private int id;

    private String name;

    private int capacity;

    private List<Reservation> reservations;

    private List<ScheduleTable> scheduleTables;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public List<ScheduleTable> getScheduleTables() {
        return scheduleTables;
    }

    public void setScheduleTables(List<ScheduleTable> scheduleTables) {
        this.scheduleTables = scheduleTables;
    }
}
