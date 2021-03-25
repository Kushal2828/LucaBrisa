package com.luca.lucabrasi.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.luca.lucabrasi.R;
import com.luca.lucabrasi.Utils.BaseActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        setTitle("Home",View.GONE);

    }

    @OnClick({R.id.arbeit,R.id.tanken,R.id.profile,R.id.logout})
    public void onViewClicked(View view) {

        switch (view.getId()) {
            case R.id.arbeit:
                startActivity(new Intent(this, Arbeit1Activity.class));
                break;
            case R.id.tanken:
                startActivity(new Intent(this, TankenActivity.class));
                break;
            case R.id.profile:
                startActivity(new Intent(this, ReportActivity.class));
                break;
            case R.id.logout:
                startActivity(new Intent(this, LoginActivity.class));
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            backToast.cancel();
            super.onBackPressed();
            return;
        } else {
            backToast = Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT);
            backToast.show();
        }
        backPressedTime = System.currentTimeMillis();
    }

}