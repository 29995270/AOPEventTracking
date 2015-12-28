package com.wq.freeze.aopeventtracking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.wq.freeze.aopeventtracking.lib.annotations.ActivityPause;
import com.wq.freeze.aopeventtracking.lib.annotations.ActivityResume;
import com.wq.freeze.aopeventtracking.lib.annotations.EventTracking;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @ActivityResume
    @Override
    protected void onResume() {
        super.onResume();
    }

    @ActivityPause
    @Override
    protected void onPause() {
        super.onPause();
    }

    @EventTracking(eventId = "login")
    private void login() {
        Toast.makeText(this, "login", Toast.LENGTH_SHORT).show();
    }

    @EventTracking(eventId = "share", keys = {"share_type", "share_uri"}, values = {"pic", "https://XXX"})
    private void share() {
        Toast.makeText(this, "share", Toast.LENGTH_SHORT).show();
    }

    private void addFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_stack, new DummyFragment()).commit();
    }

    public void loginClick(View v){
        login();
    }

    public void shareClick(View v) {
        share();
    }

    public void addFragmentClick(View v) {
        addFragment();
    }
}
