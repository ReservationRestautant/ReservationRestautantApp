package com.swd392.reservationrestautantapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.CalendarView;

import java.util.Calendar;
import java.util.Date;

public class TestTime extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_time);

        CalendarView calendarView = findViewById(R.id.calendarView);
        Calendar calendar = Calendar.getInstance();
        //set date min
        calendar.add(Calendar.DAY_OF_WEEK, 1);
        Date DATEMIN = calendar.getTime();
        calendarView.setMinDate(DATEMIN.getTime());

        //set date max
        calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.MONTH, 3);
        Date DATEMAX = calendar.getTime();

        Date now = new Date();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Log.e("month","month: " + calendar.getTime());
        calendarView.setMaxDate(DATEMAX.getTime());
    }
}