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

public class LoginActivity extends AppCompatActivity {

    Button signupbtn, forgotpasswordButton, loginButton;

    private static final String PREFS_NAME = "MyPrefs";
    private static final String PREF_ID_KEY = "id";
    private static final String PREF_PHONE_KEY = "phone";
    private static final String PREF_BOOKING_PHONE_KEY = "BOOKING_INFO_PHONE_CUS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signupbtn = findViewById(R.id.signupButton);
        forgotpasswordButton = findViewById(R.id.forgotpasswordButton);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText phoneEditText = findViewById(R.id.editTextPhone);
                EditText passwordEditText = findViewById(R.id.editTextPassword);

                String phone = phoneEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                // Check if the account exists
                boolean isAccountExists = checkAccountExists(phone, password);

                if (isAccountExists) {
                    // Call the API to retrieve user data
                    ApiService.apiService.getUserData(phone).enqueue(new Callback<ResponseObject<UserSystem>>() {
                        @Override
                        public void onResponse(Call<ResponseObject<UserSystem>> call, Response<ResponseObject<UserSystem>> response) {
                            if (response.isSuccessful()) {
                                ResponseObject<UserSystem> responseObject = response.body();
                                UserSystem user = responseObject.getData();

                                // Save id and phone into SharedPreferences
                                saveUserData(user.getId(), user.getPhone());
                            } else {
                                Log.e("ERROR", "Login fail");
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseObject<UserSystem>> call, Throwable t) {
                            Log.e("ERROR", "call api fail");
                        }
                    });
                }
            }

            private void saveUserData(int id, String phone) {
                SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                // Save id and phone into SharedPreferences
                editor.putInt(PREF_ID_KEY, id);
                editor.putString(PREF_PHONE_KEY, phone);

                // Save phone into the BOOKING_INFO_PHONE_CUS variable
                editor.putString(PREF_BOOKING_PHONE_KEY, phone);

                editor.apply();
            }

            private boolean checkAccountExists(String phone, String password) {
                // Implement your logic to check if the account exists
                // Return true if the account exists, otherwise return false
                // Example:
                return phone.equals("example") && password.equals("password");
            }
        });






        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

        forgotpasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ForgotPassword.class);
                startActivity(intent);
            }
        });
    }
}