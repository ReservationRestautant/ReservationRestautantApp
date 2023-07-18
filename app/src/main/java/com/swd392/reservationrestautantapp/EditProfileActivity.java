package com.swd392.reservationrestautantapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.swd392.reservationrestautantapp.ApiService.ApiService;
import com.swd392.reservationrestautantapp.model.ResponseObject;
import com.swd392.reservationrestautantapp.model.UserSystem;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileActivity extends AppCompatActivity {
    private static final String PREFS_PHONE = "phone";
    private static final String PREFS_NAME = "MY_APP";
    Button returnbtn, savebtn;

    EditText fullName,phone,password;
    UserSystem userSystem;

//    @Override
//    protected void onStop() {
//        super.onStop();
//        //xóa hết share reference
//        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.clear();
//        editor.commit();
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        returnbtn = findViewById(R.id.returnEditButton);
        savebtn = findViewById(R.id.saveProfileButton);

        fullName = findViewById(R.id.editProfileTextFullName);
        phone = findViewById(R.id.editProfilePhone);
        password = findViewById(R.id.editProfilePassword);

        phone.setEnabled(false);

        //map data ra trước
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        //get detial to map name in screen
        ApiService.apiService.detail(sharedPreferences.getString("TOKEN", ""), sharedPreferences.getString(PREFS_PHONE, "")).enqueue(new Callback<ResponseObject<UserSystem>>() {
            @Override
            public void onResponse(Call<ResponseObject<UserSystem>> call, Response<ResponseObject<UserSystem>> response) {
                if(response.isSuccessful()){
                    fullName.setText(response.body().getData().getName());
                    phone.setText(response.body().getData().getPhone());
                    password.setText(response.body().getData().getPassword());
                }
            }

            @Override
            public void onFailure(Call<ResponseObject<UserSystem>> call, Throwable t) {

            }
        });


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

                //valid data
                // Perform validation checks
                if (!isValidName(fullName)) {
                    // Show an error for invalid name
                    fullNameEditText.setError("Invalid name. Name should only contain letters and have a maximum length of 50.");
                    fullNameEditText.requestFocus();
                    return;
                }
                if (!isValidPassword(password)) {
                    // Show an error for invalid password
                    passwordEditText.setError("Invalid password. Password should be 8-15 characters long and include at least one capital letter, one number, and one special character.");
                    passwordEditText.requestFocus();
                    return;
                }

                //get user by phone
                Call<ResponseObject<UserSystem>> call = ApiService.apiService.detail(sharedPreferences.getString("TOKEN", ""),sharedPreferences.getString(PREFS_PHONE, ""));
                ApiService.apiService.detail(sharedPreferences.getString("TOKEN", ""),sharedPreferences.getString(PREFS_PHONE, "")).enqueue(new Callback<ResponseObject<UserSystem>>() {
                    @Override
                    public void onResponse(Call<ResponseObject<UserSystem>> call, Response<ResponseObject<UserSystem>> response) {
                        if(response.isSuccessful()){
                            userSystem  =response.body().getData();
                            //set name, password update
                            userSystem.setName(fullName);
                            userSystem.setPassword(password);

                            // Call the API to update the profile
                            ApiService.apiService.updateProfile(sharedPreferences.getString("TOKEN", ""),userSystem).enqueue(new Callback<ResponseObject<UserSystem>>() {
                                @Override
                                public void onResponse(Call<ResponseObject<UserSystem>> call, Response<ResponseObject<UserSystem>> response) {
                                    if (response.isSuccessful()) {
                                        // Profile update successful

                                        // Log the edited profile fields
                                        Log.d("Edited Profile", "Full Name: " + fullName);
                                        Log.d("Edited Profile", "Phone: " + phone);
                                        Log.d("Edited Profile", "Password: " + password);

                                        Intent intent = new Intent(EditProfileActivity.this,ProfileActivity.class);
                                        startActivity(intent);
                                    } else {
                                        // Profile update failed
                                    }
                                }

                                @Override
                                public void onFailure(Call<ResponseObject<UserSystem>> call, Throwable t) {
                                    // Profile update API call failed
                                }
                            });
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseObject<UserSystem>> call, Throwable t) {

                    }
                });


            }

            private void updateProfile(UserSystem userSystem) {
                // Call the API to update the profile using the provided data
                Call<ResponseObject<UserSystem>> call = ApiService.apiService.updateProfile(sharedPreferences.getString("TOKEN", ""),userSystem);
                call.enqueue(new Callback<ResponseObject<UserSystem>>() {
                    @Override
                    public void onResponse(Call<ResponseObject<UserSystem>> call, Response<ResponseObject<UserSystem>> response) {
                        if (response.isSuccessful()) {
                            // Profile update successful

                            // Log the edited profile fields
                            Log.d("Edited Profile", "Full Name: " + fullName);
                            Log.d("Edited Profile", "Phone: " + phone);
                            Log.d("Edited Profile", "Password: " + password);

                            Intent intent = new Intent(EditProfileActivity.this,ProfileActivity.class);
                            startActivity(intent);
                        } else {
                            // Profile update failed
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseObject<UserSystem>> call, Throwable t) {
                        // Profile update API call failed
                    }
                });
            }
        });
    }

    private boolean isValidName(String name) {
        // Check if name only contains letters and its length is less than 50
        return name.matches("[a-zA-Z ]+") && name.length() < 50;
    }

    private boolean isValidPassword(String password) {
        // Check if password matches the required criteria
        // Minimum length is 8 characters, maximum length is 20 characters
        // It must contain at least 1 capital letter, 1 number, and 1 special character
        String regex = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$";
        return password.matches(regex);
    }
}