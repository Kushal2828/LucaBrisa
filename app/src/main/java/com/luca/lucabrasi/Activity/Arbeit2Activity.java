package com.luca.lucabrasi.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.luca.lucabrasi.R;
import com.luca.lucabrasi.Utils.BaseActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.iwgang.countdownview.CountdownView;

public class Arbeit2Activity extends BaseActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arbeit2);
        ButterKnife.bind(this);
        CountdownView mCvCountdownView = (CountdownView)findViewById(R.id.timer);
        mCvCountdownView.start(25200000); // Millisecond
        setTitle("Arbeit", View.GONE);

    }
    @OnClick({R.id.tvfertig})
    public void onViewClicked(View view) {

        switch (view.getId()) {
            case R.id.tvfertig:
                startActivity(new Intent(this, Arbeit3Activity.class));
                break;
        }
    }
}