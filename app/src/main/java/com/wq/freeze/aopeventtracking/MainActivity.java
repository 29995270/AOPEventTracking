package com.wq.freeze.aopeventtracking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.wq.freeze.aopeventtracking.lib.EventTracking;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @EventTracking(eventId = "login")
    private void login() {

    }

    public void loginClick(View v){
        login();
    }
}
