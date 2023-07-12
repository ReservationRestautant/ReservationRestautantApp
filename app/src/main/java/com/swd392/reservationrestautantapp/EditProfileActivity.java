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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileActivity extends AppCompatActivity {

    Button returnbtn, savebtn;

    EditText fullName,phone,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        returnbtn = findViewById(R.id.returnEditButton);
        savebtn = findViewById(R.id.saveProfileButton);

        fullName = findViewById(R.id.editProfileTextFullName);
        phone = findViewById(R.id.editProfilePhone);
        password = findViewById(R.id.editProfilePassword);

        returnbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditProfileActivity.this,ProfileActivity.class);
                startActivity(intent);
            }
        });

        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the edited profile fields
                EditText fullNameEditText = findViewById(R.id.editProfileTextFullName);
                EditText phoneEditText = findViewById(R.id.editProfilePhone);
                EditText passwordEditText = findViewById(R.id.editProfilePassword);

                String fullName = fullNameEditText.getText().toString();
                String phone = phoneEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                // Call the API to update the profile
                updateProfile(fullName, phone, password);
            }

            private void updateProfile(String fullName, String phone, String password) {
                // Call the API to update the profile using the provided data
                Call<ResponseObject> call = ApiService.apiService.updateProfile(fullName, phone, password);
                call.enqueue(new Callback<ResponseObject>() {
                    @Override
                    public void onResponse(Call<ResponseObject> call, Response<ResponseObject> response) {
                        if (response.isSuccessful()) {
                            // Profile update successful

                            // Log the edited profile fields
                            Log.d("Edited Profile", "Full Name: " + fullName);
                            Log.d("Edited Profile", "Phone: " + phone);
                            Log.d("Edited Profile", "Password: " + password);
                        } else {
                            // Profile update failed
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseObject> call, Throwable t) {
                        // Profile update API call failed
                    }
                });
            }
        });
    }
}