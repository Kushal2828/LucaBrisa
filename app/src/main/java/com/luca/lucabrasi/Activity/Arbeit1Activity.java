package com.luca.lucabrasi.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.luca.lucabrasi.R;
import com.luca.lucabrasi.Utils.BaseActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class Arbeit1Activity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arbeit1);
        ButterKnife.bind(this);
        setTitle("Arbeit", View.GONE);
    }
    @OnClick({R.id.tvplayerupdate})
    public void onViewClicked(View view) {

        switch (view.getId()) {
            case R.id.tvplayerupdate:
                startActivity(new Intent(this, Arbeit2Activity.class));
                break;
        }
    }
}