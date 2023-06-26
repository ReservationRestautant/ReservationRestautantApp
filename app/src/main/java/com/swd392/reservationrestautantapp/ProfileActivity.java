package com.swd392.reservationrestautantapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import org.w3c.dom.Text;

public class ProfileActivity extends AppCompatActivity {

    Button editbtn,historybtn, logoutbtn;

    ImageView profileimg;

    Text profilename;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        editbtn = findViewById(R.id.editProfileButton);
        historybtn = findViewById(R.id.historyProfileButton);
        logoutbtn = findViewById(R.id.logoutProfileButton);

        profileimg = findViewById(R.id.profileImg);

        profilename = findViewById(R.id.profileName);

        editbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this,EditProfileActivity.class);
                startActivity(intent);
            }
        });

        historybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}