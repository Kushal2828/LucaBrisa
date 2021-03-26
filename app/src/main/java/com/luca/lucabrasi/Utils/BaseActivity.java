package com.luca.lucabrasi.Utils;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
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
   public AppPreference mAppPreference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        mAppPreference = new AppPreference(BaseActivity.this);
    }
    public void setTitle(String title, int gone) {
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        imageView = (ImageView) findViewById(R.id.back);
        tvTitle.setText(title);
        imageView.setVisibility(gone);

        tvTitle.setTextColor(getResources().getColor(R.color.white));
    }

    public void showShortToast(Context context, String msg){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
    public void showLongToast(Context context,String msg){
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

}