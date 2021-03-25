package com.luca.lucabrasi.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.luca.lucabrasi.R;
import com.luca.lucabrasi.Utils.BaseActivity;

import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.iwgang.countdownview.CountdownView;

public class Arbeit2Activity extends BaseActivity {


    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arbeit2);
        ButterKnife.bind(this);

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

    private void setTimer(final TextView tvHour, TextView tvminute, TextView tvSecond, final LinearLayout llSpecialOffer) {
        if (handler != null) {
            handler.removeCallbacks(null);

        } else {
            handler = new Handler();
        }

        final int delay = 1000; //milliseconds
        handler.postDelayed(new Runnable() {
            public void run() {
                //do something
                handler.postDelayed(this, delay);
                String timer = tvHour.getText().toString() + ":" + tvminute.getText().toString() + ":" + tvSecond.getText().toString();
                long time = convertInMilisecond(timer) - 1000;
                if (time == 0) {
                    llSpecialOffer.setVisibility(View.GONE);
                } else {
                    tvHour.setText(TimeUnit.MILLISECONDS.toHours(time) + "");
                    tvminute.setText((TimeUnit.MILLISECONDS.toMinutes(time) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(time))) + "");
                    tvSecond.setText((TimeUnit.MILLISECONDS.toSeconds(time) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(time))) + "");

                }
            }
        }, delay);
    }
    private long convertInMilisecond(String time) {

        String[] tokens = time.split(":");
        int secondsToMs = Integer.parseInt(tokens[2]) * 1000;
        int minutesToMs = Integer.parseInt(tokens[1]) * 60000;
        int hoursToMs = Integer.parseInt(tokens[0]) * 3600000;
        long total = secondsToMs + minutesToMs + hoursToMs;
        return total;
    }
}