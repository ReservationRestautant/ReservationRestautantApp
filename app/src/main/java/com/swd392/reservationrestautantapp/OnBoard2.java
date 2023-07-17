package com.swd392.reservationrestautantapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OnBoard2 extends AppCompatActivity {
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
        setContentView(R.layout.activity_on_board2);

        // button next onboard activity 2
        Button btnob = findViewById(R.id.buttonOnBoard);

        // switch to Create/Login activity
        btnob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OnBoard2.this, WelcomeActivity.class);
                startActivity(intent);
            }
        });

    }
}