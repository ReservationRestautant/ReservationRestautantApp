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
import android.widget.Toast;

import com.swd392.reservationrestautantapp.ApiService.ApiService;
import com.swd392.reservationrestautantapp.model.DataLogin;
import com.swd392.reservationrestautantapp.model.ResponseObject;
import com.swd392.reservationrestautantapp.model.Spam;
import com.swd392.reservationrestautantapp.model.UserSystem;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private static final String PREF_TOKEN = "TOKEN";
    Button signupbtn, forgotpasswordButton, loginButton;

    private static final String PREFS_NAME = "MY_APP";
    private static final String PREF_ID_KEY = "id";
    private static final String PREF_PHONE_KEY = "phone";
    private static final String PREF_BOOKING_PHONE_KEY = "BOOKING_INFO_PHONE_CUS";

    boolean check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signupbtn = findViewById(R.id.signupButton);
        forgotpasswordButton = findViewById(R.id.forgotpasswordButton);
        loginButton = findViewById(R.id.loginButton);
        check = false;
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText phoneEditText = findViewById(R.id.editTextPhone);
                EditText passwordEditText = findViewById(R.id.editTextPassword);

                String phone = phoneEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                //check spam
                ApiService.apiService.login(phone, password).enqueue(new Callback<ResponseObject<DataLogin>>() {
                    @Override
                    public void onResponse(Call<ResponseObject<DataLogin>> call, Response<ResponseObject<DataLogin>> response) {
                        if(response.isSuccessful()){
                            if(response.body().getData().getUserSystem().getPhone().equals(phone) && response.body().getData().getUserSystem().getPassword().equals(password)){
                                ResponseObject<DataLogin> responseObject = response.body();
                                UserSystem user = responseObject.getData().getUserSystem();
                                String token = responseObject.getData().getToken();
                                //check spam
                                ApiService.apiService.spam(token, phone).enqueue(new Callback<ResponseObject<Spam>>() {
                                    @Override
                                    public void onResponse(Call<ResponseObject<Spam>> call, Response<ResponseObject<Spam>> response) {
                                        if(response.isSuccessful()){
                                            Spam spam = response.body().getData();
                                            if(spam.isBlock() == false){
                                                //có spam rồi, nhưng chưa bị block
                                                // Save id and phone into SharedPreferences
                                                saveUserData(user.getId(), user.getPhone(), token);

                                                // Registration successful, navigate to LoginActivity
                                                Intent intent = new Intent(LoginActivity.this, HomePage.class);
                                                startActivity(intent);
                                            }else if(spam.isBlock() == true){
                                                //có spam rồi, bị block rồi
                                                Toast.makeText(LoginActivity.this, "Your accout is block until " + spam.getTimeUnBlock(), Toast.LENGTH_LONG).show();
                                            }
                                        }else {
                                            //400 not found spam hay chu7a0 spam lần nào
                                            // Save id and phone into SharedPreferences
                                            saveUserData(user.getId(), user.getPhone(), token);

                                            // Registration successful, navigate to LoginActivity
                                            Intent intent = new Intent(LoginActivity.this, HomePage.class);
                                            startActivity(intent);
                                        }

                                    }

                                    @Override
                                    public void onFailure(Call<ResponseObject<Spam>> call, Throwable t) {
//                                    Toast.makeText(LoginActivity.this, "Some error, try again", Toast.LENGTH_LONG).show();
                                        // Save id and phone into SharedPreferences
                                        saveUserData(user.getId(), user.getPhone(), token);

                                        // Registration successful, navigate to LoginActivity
                                        Intent intent = new Intent(LoginActivity.this, HomePage.class);
                                        startActivity(intent);
                                    }
                                });
                            }
                        }else{
                            Toast.makeText(LoginActivity.this, "Phone or password incorrect.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseObject<DataLogin>> call, Throwable t) {
                        Log.e("ERROR", t.getMessage());
                    }
                });
            }

            private void saveUserData(int id, String phone, String token) {
                SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                // Save id and phone into SharedPreferences
                editor.putInt(PREF_ID_KEY, id);
                editor.putString(PREF_PHONE_KEY, phone);
                editor.putString(PREF_TOKEN, token);

                // Save phone into the BOOKING_INFO_PHONE_CUS variable
                editor.putString(PREF_BOOKING_PHONE_KEY, phone);

                editor.apply();
            }

//            private boolean checkAccountExists(String phone, String password) {
//                // Implement your logic to check if the account exists
//                // Return true if the account exists, otherwise return false
//                // Example:
////                return phone.equals("example") && password.equals("password");
//                ApiService.apiService.login(phone, password).enqueue(new Callback<ResponseObject<DataLogin>>() {
//                    @Override
//                    public void onResponse(Call<ResponseObject<DataLogin>> call, Response<ResponseObject<DataLogin>> response) {
//                        if(response.body().getData().getPhone().equals(phone) && response.body().getData().getPassword().equals(password)){
//                            check = true;
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<ResponseObject<DataLogin>> call, Throwable t) {
//                        Log.e("ERROR", t.getMessage());
//                    }
//                });
//                return check;
//            }
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