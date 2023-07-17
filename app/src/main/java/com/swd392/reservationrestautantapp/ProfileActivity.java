package com.swd392.reservationrestautantapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.swd392.reservationrestautantapp.ApiService.ApiService;
import com.swd392.reservationrestautantapp.model.ResponseObject;
import com.swd392.reservationrestautantapp.model.Spam;
import com.swd392.reservationrestautantapp.model.UserSystem;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {
    private static final String PREFS_NAME = "MY_APP";
    private static final String PREFS_PHONE = "phone";
    private static final String PREFS_GUEST_ROLE = "GUEST";
    Button editbtn,historybtn, logoutbtn;

    ImageView profileimg;

    TextView profilename;

    String guest;
    BottomNavigationView btv;


//    @Override
//    protected void onStop() {
//        super.onStop();
//        //xóa hết share reference
//        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.clear();
//        editor.commit();
//    }

    //@SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        editbtn = findViewById(R.id.editProfileButton);
        historybtn = findViewById(R.id.historyProfileButton);
        logoutbtn = findViewById(R.id.logoutProfileButton);

        profileimg = findViewById(R.id.profileImg);

        profilename = findViewById(R.id.profileName);
        setupNavBottom();

        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        guest = sharedPreferences.getString(PREFS_GUEST_ROLE, "");
        //get detial to map name in screen
        ApiService.apiService.detail(sharedPreferences.getString(PREFS_PHONE, "")).enqueue(new Callback<ResponseObject<UserSystem>>() {
            @Override
            public void onResponse(Call<ResponseObject<UserSystem>> call, Response<ResponseObject<UserSystem>> response) {
                if(response.isSuccessful()){
                    profilename.setText(response.body().getData().getName());
                }
            }

            @Override
            public void onFailure(Call<ResponseObject<UserSystem>> call, Throwable t) {

            }
        });

        editbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(guest.equals("true")){
                    Toast.makeText(ProfileActivity.this, "Not allow", Toast.LENGTH_LONG).show();
                }else{
                    Intent intent = new Intent(ProfileActivity.this,EditProfileActivity.class);
                    startActivity(intent);
                }
            }
        });

        historybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(guest.equals("true")){
                    Toast.makeText(ProfileActivity.this, "Not allow", Toast.LENGTH_LONG).show();
                }else{
                    Intent intent = new Intent(ProfileActivity.this,History.class);
                    startActivity(intent);
                }
            }
        });

        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //xóa hết share reference
                SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();

                Intent intent = new Intent(ProfileActivity.this,WelcomeActivity.class);
                startActivity(intent);
            }
        });


    }

    private void setupNavBottom() {
        btv = findViewById(R.id.bottom_nav);
        btv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.ac_home){
                    System.out.println("btv_home_page");
                    startActivity(new Intent(ProfileActivity.this, HomePage.class));
                } else if(item.getItemId() == R.id.ac_history){
                    SharedPreferences sharedPreferences = getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
                    String guest_login = sharedPreferences.getString("GUEST", "");
                    if(guest_login.equals("true")){
                        Toast.makeText(ProfileActivity.this, "Not Allow", Toast.LENGTH_SHORT).show();
                    }else {
                        System.out.println("btv_ac_search_click");
                        startActivity(new Intent(ProfileActivity.this, History.class));
                    }
                }else if(item.getItemId() == R.id.ac_user) {
                    System.out.println("btv_ac_favorite_click");
                    startActivity(new Intent(ProfileActivity.this, ProfileActivity.class));
                }
                return true;
            }
        });
    };
}