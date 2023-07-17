package com.swd392.reservationrestautantapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.swd392.reservationrestautantapp.ApiService.ApiService;
import com.swd392.reservationrestautantapp.model.ResponseObject;
import com.swd392.reservationrestautantapp.model.UserSystem;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OnBoard extends AppCompatActivity {
    private static final String PREFS_NAME = "MY_APP";

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
        setContentView(R.layout.activity_on_board);

        // button next onboard activity 1
        Button btnob = findViewById(R.id.buttonOnBoard);

        // switch to OnBoard activity 2
        btnob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OnBoard.this, OnBoard2.class);
                startActivity(intent);
            }
        });

        //test call api
        ApiService.apiService.getAllUser().enqueue(new Callback<ResponseObject<List<UserSystem>>>() {
            @Override
            public void onResponse(Call<ResponseObject<List<UserSystem>>> call, Response<ResponseObject<List<UserSystem>>> response) {
                for (UserSystem user: response.body().getData()) {
                    Log.e("USER", user.toString());
                }
            }

            @Override
            public void onFailure(Call<ResponseObject<List<UserSystem>>> call, Throwable t) {
                Log.e("USER", "call api fail");
            }
        });

    }
}