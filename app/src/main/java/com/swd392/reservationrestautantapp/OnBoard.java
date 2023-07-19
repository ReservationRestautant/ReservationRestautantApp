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
import com.swd392.reservationrestautantapp.model.Spam;
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

//        //test call api
         ApiService.apiService.spam("0971724708").enqueue(new Callback<ResponseObject<Spam>>() {
                                    @Override
                                    public void onResponse(Call<ResponseObject<Spam>> call, Response<ResponseObject<Spam>> response) {
                                        if(response.isSuccessful()){
                                            Log.d("SEE", "onResponse: response success");
                                        }else {
                                            Log.d("SEE", "onResponse: response unsuccess");
                                        }

                                    }

                                    @Override
                                    public void onFailure(Call<ResponseObject<Spam>> call, Throwable t) {
                                        Log.d("SEE", "onResponse: response unsuccess FAIL");
                                    }
                                });

    }
}