package com.swd392.reservationrestautantapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class GuestInfoActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "MY_APP";
    private static final String PREF_BOOKING_PHONE_GUEST_KEY = "BOOKING_INFO_PHONE_GUEST";

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
                EditText phoneEditText = findViewById(R.id.editTextGuestPhone);
                String phone = phoneEditText.getText().toString();

                // Save the phone number into SharedPreferences
                saveGuestPhone(phone);
            }

            // Dùng cái này để lấy sđt guest khi cần
            /*SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
            String guestPhone = sharedPreferences.getString(PREF_BOOKING_PHONE_GUEST_KEY, "");*/

            private void saveGuestPhone(String phone) {
                SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                // Save the phone number into SharedPreferences
                editor.putString(PREF_BOOKING_PHONE_GUEST_KEY, phone);

                editor.apply();
            }
        });
    }
}