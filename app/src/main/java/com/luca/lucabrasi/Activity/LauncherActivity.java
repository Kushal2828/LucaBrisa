package com.luca.lucabrasi.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.luca.lucabrasi.R;
import com.luca.lucabrasi.Utils.BaseActivity;

public class LauncherActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {

                if (mAppPreference.getMemberID() != null && !mAppPreference.getMemberID().equals("")) {
                    startActivity(new Intent(LauncherActivity.this, HomeActivity.class));
                    finish();

                }else {
                    startActivity(new Intent(LauncherActivity.this, LoginActivity.class));
                    finish();
                }



               /* Intent intent = new Intent(LauncherActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();*/
            }
        }, 2500);
    }
}