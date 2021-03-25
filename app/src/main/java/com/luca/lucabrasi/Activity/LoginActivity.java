package com.luca.lucabrasi.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.luca.lucabrasi.R;
import com.luca.lucabrasi.Utils.BaseActivity;

public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }

    public void loginclick(View view) {
        startActivity(new Intent(this, HomeActivity.class));
    }
}