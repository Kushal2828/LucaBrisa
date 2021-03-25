package com.luca.lucabrasi.Utils;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.luca.lucabrasi.R;

public class BaseActivity extends AppCompatActivity {
    public long backPressedTime;
    public Toast backToast;
    TextView tvTitle;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }
    public void setTitle(String title, int gone) {
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        imageView = (ImageView) findViewById(R.id.back);
        tvTitle.setText(title);
        imageView.setVisibility(gone);

        tvTitle.setTextColor(getResources().getColor(R.color.white));
    }


}