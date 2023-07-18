package com.swd392.reservationrestautantapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ForgotPassword extends AppCompatActivity {
    Button SendButton;
    TextView loginLink, SignupLink;
    EditText edt_phone, edt_oldpwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        findData();

        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForgotPassword.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        SignupLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForgotPassword.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        SendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForgotPassword.this, ForgotPassword2.class);
                String phone = edt_phone.getText().toString();
                intent.putExtra("phone", phone);
                startActivity(intent);
            }
        });
    }

    private void findData() {
        SendButton = findViewById(R.id.SendButton);
        loginLink = findViewById(R.id.loginLink);
        SignupLink = findViewById(R.id.SignupLink);
        edt_phone = findViewById(R.id.edt_phone);
        edt_oldpwd = findViewById(R.id.edt_oldpwd);
    }
}