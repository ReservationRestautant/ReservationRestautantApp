package com.swd392.reservationrestautantapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ForgotPassword2 extends AppCompatActivity {

    TextView ResendLink, phoneUser;
    Button BackToLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password2);

        ResendLink = findViewById(R.id.ResendLink);
        BackToLoginButton = findViewById(R.id.BackToLoginButton);
        phoneUser = findViewById(R.id.phoneUser);

        Intent intent = getIntent();
        String phone = intent.getStringExtra("phone");
        phoneUser.setText(phone);

        BackToLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ForgotPassword2.this, LoginActivity.class));
            }
        });
    }
}