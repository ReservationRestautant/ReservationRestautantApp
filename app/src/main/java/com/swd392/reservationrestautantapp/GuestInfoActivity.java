package com.swd392.reservationrestautantapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class GuestInfoActivity extends AppCompatActivity {

    EditText guestFullname, guestPhone;

    Button conGuest_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_info);

        guestFullname = findViewById(R.id.editTextFullName);
        guestPhone = findViewById(R.id.editTextGuestPhone);
        conGuest_btn = findViewById(R.id.conGuestButton);

        conGuest_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}