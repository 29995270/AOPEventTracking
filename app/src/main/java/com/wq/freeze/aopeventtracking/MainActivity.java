package com.wq.freeze.aopeventtracking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.wq.freeze.aopeventtracking.lib.EventTracking;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @EventTracking(eventId = "login")
    private void login() {
        Toast.makeText(this, "login", Toast.LENGTH_SHORT).show();
    }

    @EventTracking(eventId = "share", keys = {"share_type", "share_uri"}, values = {"pic", "https://docs.oracle.com/javase/webdesign/pubs8/im/a.gif"})
    private void share() {
        Toast.makeText(this, "share", Toast.LENGTH_SHORT).show();
    }

    public void loginClick(View v){
        login();
    }

    public void shareClick(View v) {
        share();
    }
}
