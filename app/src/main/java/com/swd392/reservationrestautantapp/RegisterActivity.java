package com.swd392.reservationrestautantapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.swd392.reservationrestautantapp.ApiService.ApiService;
import com.swd392.reservationrestautantapp.model.ResponseObject;
import com.swd392.reservationrestautantapp.model.UserSystem;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    Button registerButton1, loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerButton1 = findViewById(R.id.registerButton1);
        loginButton = findViewById(R.id.loginButton);
        registerButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }

            private void signUp() {
                // Retrieve input values from EditText fields
                EditText fullNameEditText = findViewById(R.id.editTextFullName);
                EditText phoneEditText = findViewById(R.id.editTextPhone);
                EditText passwordEditText = findViewById(R.id.editTextPassword);
                EditText repeatPasswordEditText = findViewById(R.id.editTextRePassword);

                String fullName = fullNameEditText.getText().toString().trim();
                String phone = phoneEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();
                String repeatPassword = repeatPasswordEditText.getText().toString().trim();

                // Perform validation checks
                if (!isValidName(fullName)) {
                    // Show an error for invalid name
                    fullNameEditText.setError("Invalid name. Name should only contain letters and have a maximum length of 50.");
                    fullNameEditText.requestFocus();
                    return;
                }

                if (!isValidPhone(phone)) {
                    // Show an error for invalid phone number
                    phoneEditText.setError("Invalid phone number. Phone number should contain exactly 10 digits.");
                    phoneEditText.requestFocus();
                    return;
                }

                if (!isValidPassword(password)) {
                    // Show an error for invalid password
                    passwordEditText.setError("Invalid password. Password should be 8-15 characters long and include at least one capital letter, one number, and one special character.");
                    passwordEditText.requestFocus();
                    return;
                }

                if (!password.equals(repeatPassword)) {
                    // Show an error if passwords do not match
                    repeatPasswordEditText.setError("Passwords do not match.");
                    repeatPasswordEditText.requestFocus();
                    return;
                }

                // All validation checks passed, proceed with registration
                // Create a UserSystem object and populate its fields
                UserSystem user = new UserSystem();
                user.setName(fullName);
                user.setPhone(phone);
                user.setPassword(password);
                user.setRole('c'); // Set the default role to "c"

                //Call API
                ApiService.apiService.createUser(user).enqueue(new Callback<ResponseObject<List<UserSystem>>>() {
                    @Override
                    public void onResponse(Call<ResponseObject<List<UserSystem>>> call, Response<ResponseObject<List<UserSystem>>> response) {
                        for (UserSystem userSystem : response.body().getData()) {
                            Log.d("User Fields", "Name: " + userSystem.getName());
                            Log.d("User Fields", "Phone: " + userSystem.getPhone());
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseObject<List<UserSystem>>> call, Throwable t) {
                        Log.e("ERROR", "call api fail");
                    }
                });

                // Registration successful, navigate to LoginActivity
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish(); // Optional: If you want to close the RegisterActivity
            }

            private boolean isValidName(String name) {
                // Check if name only contains letters and its length is less than 50
                return name.matches("[a-zA-Z ]+") && name.length() < 50;
            }

            private boolean isValidPhone(String phone) {
                // Check if phone number contains exactly 10 digits
                return phone.matches("\\d{10}");
            }

            private boolean isValidPassword(String password) {
                // Check if password matches the required criteria
                // Minimum length is 8 characters, maximum length is 15 characters
                // It must contain at least 1 capital letter, 1 number, and 1 special character
                String regex = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=])(?=\\S+$).{8,15}$";
                return password.matches(regex);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}