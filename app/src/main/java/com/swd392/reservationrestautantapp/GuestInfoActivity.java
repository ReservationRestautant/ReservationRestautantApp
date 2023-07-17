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
    private static final String PREF_BOOKING_INFO_PHONE_CUS_KEY = "BOOKING_INFO_PHONE_CUS";
    private static final String PREFS_GUEST_ROLE = "GUEST";

    EditText guestFullname, guestPhone;

//    @Override
//    protected void onStop() {
//        super.onStop();
//        //xóa hết share reference
//        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.clear();
//        editor.commit();
//    }
    Button conGuest_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_info);

//        guestFullname = findViewById(R.id.editTextFullName);
        guestPhone = findViewById(R.id.editTextGuestPhone);
        conGuest_btn = findViewById(R.id.conGuestButton);

        conGuest_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText phoneEditText = findViewById(R.id.editTextGuestPhone);
                String phone = phoneEditText.getText().toString();

                if (!isValidPhone(phone)) {
                    // Show an error for invalid phone number
                    phoneEditText.setError("Invalid phone number. Phone number should contain exactly 10 digits.");
                    phoneEditText.requestFocus();
                    return;
                }

                // Save the phone number into SharedPreferences
                saveGuestPhone(phone);

                // Registration successful, navigate to LoginActivity
                Intent intent = new Intent(GuestInfoActivity.this, HomePage.class);
                startActivity(intent);
            }

            // Dùng cái này để lấy sđt guest khi cần
            /*SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
            String guestPhone = sharedPreferences.getString(PREF_BOOKING_PHONE_GUEST_KEY, "");*/

            private void saveGuestPhone(String phone) {
                SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                // Save the phone number into SharedPreferences
                editor.putString(PREF_BOOKING_PHONE_GUEST_KEY, phone);
                editor.putString(PREF_BOOKING_INFO_PHONE_CUS_KEY, "0000000000");
                editor.putString(PREFS_GUEST_ROLE, "true");

                editor.apply();
            }
        });
    }
    private boolean isValidPhone(String phone) {
        // Check if phone number contains exactly 10 digits
        return phone.matches("\\d{10}");
    }

    private boolean isValidName(String name) {
        // Check if name only contains letters and its length is less than 50
        return name.matches("[a-zA-Z ]+") && name.length() > 5 && name.length() < 50;
    }
}