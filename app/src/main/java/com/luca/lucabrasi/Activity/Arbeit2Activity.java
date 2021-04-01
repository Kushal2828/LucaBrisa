package com.luca.lucabrasi.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.luca.lucabrasi.APIclient.CommanAPI;
import com.luca.lucabrasi.CustomViews.bounceview.BounceView;
import com.luca.lucabrasi.Interfaces.OnDataResponseListner;
import com.luca.lucabrasi.Model.Commonmodel;
import com.luca.lucabrasi.Model.Gettimestampmodel;
import com.luca.lucabrasi.R;
import com.luca.lucabrasi.Utils.BaseActivity;
import com.luca.lucabrasi.Utils.Helper;
import com.luca.lucabrasi.Utils.HttpParams;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Arbeit2Activity extends BaseActivity implements OnDataResponseListner {


    @BindView(R.id.tvHour)
    TextView tvHour;

    @BindView(R.id.tvMinute)
    TextView tvMinute;

    @BindView(R.id.tvSecond)
    TextView tvSecond;

    @BindView(R.id.tvstartHour)
    TextView tvstartHour;

    @BindView(R.id.tvstartMinute)
    TextView tvstartMinute;

    @BindView(R.id.tvstartSecond)
    TextView tvstartSecond;


    private Handler handler;
    private String TAG = "Arbeit2Activity";
    LocalDateTime localDateTime = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arbeit2);
        ButterKnife.bind(this);
        setTitle(getString(R.string.home), View.GONE);
        getcurrentTimestamp();

    }

    private void getcurrentTimestamp() {
        if (Helper.isNetworkConnected(this)) {

            CommanAPI commanAPI = new CommanAPI(HttpParams.GETDAYTIMESTAMP, this);
            Map<String, String> params = new HashMap<>();
            params.put(HttpParams.driver_id, mAppPreference.getMemberID());
            commanAPI.postRequest(HttpParams.getdaytimestamp, params);
            Helper.showProgressBar(this, getResources().getString(R.string.please_wait));
            Log.e(TAG, "getcurrentTimestamp: " + HttpParams.GETDAYTIMESTAMP);
            Log.e(TAG, "getcurrentTimestamp: " + params.toString());
        } else {
            showShortToast(this, getString(R.string.No_Internet));
        }


    }

    @OnClick({R.id.tvfertig})
    public void onViewClicked(View view) {

        switch (view.getId()) {
            case R.id.tvfertig:

                setDialog(this, getString(R.string.surewanttotime));
                // startActivity(new Intent(this, Arbeit3Activity.class));
                break;
        }
    }

    private void setDialog(Arbeit2Activity arbeit2Activity, String string) {
        AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.surewanttotime));
        builder.setTitle(R.string.Alert);
        builder.setCancelable(false);
        builder.setNegativeButton(getResources().getString(R.string.no), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setPositiveButton(getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                callapi();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();

        BounceView.addAnimTo(alert);        //Call before showing the dialog

        Button nbutton = alert.getButton(DialogInterface.BUTTON_NEGATIVE);
        nbutton.setTextColor(getResources().getColor(R.color.purple_200));
        Button pbutton = alert.getButton(DialogInterface.BUTTON_POSITIVE);
        pbutton.setTextColor(getResources().getColor(R.color.purple_200));
    }

    private void callapi() {
        if (Helper.isNetworkConnected(this)) {

            CommanAPI commanAPI = new CommanAPI(HttpParams.STOPTIMER, this);
            Map<String, String> params = new HashMap<>();
            params.put(HttpParams.driver_id, mAppPreference.getMemberID());
            params.put(HttpParams.day_id, mAppPreference.getDayid());
            Log.e(TAG, "onViewClicked: " + params.toString());
            commanAPI.postRequest(HttpParams.stoptimer, params);
            Helper.showProgressBar(this, getResources().getString(R.string.please_wait));
            hideKeyboard(this);
        } else {
            showShortToast(this, getString(R.string.No_Internet));
        }
    }

    private void setTimer(final TextView tvHour, TextView tvminute, TextView tvSecond) {
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
                long time = convertInMilisecond(timer) + 1000;
                if (time == 0) {
                    // llSpecialOffer.setVisibility(View.GONE);
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public void Response(String methodName, String response, boolean isResponse) {
        Helper.hideProgressBar();
        Log.e(TAG, "Response: " + response);
        if (isResponse) {
            if (methodName.equals(HttpParams.GETDAYTIMESTAMP)) {
                if (response != null) {
                    Gettimestampmodel gettimestampmodel = new Gson().fromJson(response, new TypeToken<Gettimestampmodel>() {
                    }.getType());
                    if (gettimestampmodel.status.equals(HttpParams.success)) {

                        mAppPreference.setStarttime(gettimestampmodel.data.timestamp);
                        Log.e(TAG, "Response:MemberID " + mAppPreference.getStarttime());


                        setstartTime(gettimestampmodel);

                        String pattern = "yyyy-MM-DD HH:mm:SS";
                        DateFormat formatter = new SimpleDateFormat(pattern);
                        Date date = null;
                        try {
                            date = formatter.parse(gettimestampmodel.data.currentTime);
                            Log.e(TAG, "Response:converted time " + date.getTime());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(date);
                        Log.e(TAG, "Response: hours"+calendar.get(Calendar.HOUR_OF_DAY)+" "+calendar.get(Calendar.MINUTE) );
                        int hours = calendar.get(Calendar.HOUR_OF_DAY);
                        int minutes = calendar.get(Calendar.MINUTE);
                        int seconds = calendar.get(Calendar.SECOND);


                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-DD HH:mm:SS");
                        sdf.format(date);








                        tvHour.setText(String.valueOf(hours));
                        tvMinute.setText(String.valueOf(minutes));
                        tvSecond.setText(String.valueOf(seconds));
                        setTimer(tvHour, tvMinute, tvSecond);
                        Log.e(TAG, "Response: " + hours + " " + minutes + " " + seconds);


                    } else {
                        showLongToast(this, gettimestampmodel.message);
                    }

                    Log.e("TAG", "Response: " + new Gson().toJson(gettimestampmodel));

                }
            } else if (methodName.equals(HttpParams.STOPTIMER)) {
                if (response != null) {
                    Commonmodel commonmodel = new Gson().fromJson(response, new TypeToken<Commonmodel>() {
                    }.getType());
                    if (commonmodel.status.equals(HttpParams.success)) {
                        showLongToast(this, getString(R.string.timestoped));
                        startActivity(new Intent(this, Arbeit3Activity.class));
                        finish();
                    } else {
                        showLongToast(this, commonmodel.message);
                    }
                }

            }
        } else {
            showLongToast(this, getString(R.string.somethingwrong));
            Log.e(TAG, "Response: ");
        }

    }

    private void setstartTime(Gettimestampmodel timestamp) {

        String pattern = "yyyy-MM-DD HH:mm:ss";
        DateFormat formatter = new SimpleDateFormat(pattern);
        Date date = null;
        try {
            date = formatter.parse(timestamp.data.timestamp);
            Log.e(TAG, "Response:converted time " + date.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar calendar = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            calendar = Calendar.getInstance();
            calendar.setTime(date);
            int hours = calendar.get(Calendar.HOUR_OF_DAY);
            int minutes = calendar.get(Calendar.MINUTE);
            int seconds = calendar.get(Calendar.SECOND);

            tvstartHour.setText(String.valueOf(hours));
            tvstartMinute.setText(String.valueOf(minutes));
            tvstartSecond.setText(String.valueOf(seconds));

        }

    }
}