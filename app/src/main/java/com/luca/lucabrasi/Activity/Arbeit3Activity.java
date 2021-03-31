package com.luca.lucabrasi.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.luca.lucabrasi.APIclient.CommanAPI;
import com.luca.lucabrasi.Interfaces.OnDataResponseListner;
import com.luca.lucabrasi.Model.Commonmodel;
import com.luca.lucabrasi.Model.Stationmodel;
import com.luca.lucabrasi.R;
import com.luca.lucabrasi.Utils.BaseActivity;
import com.luca.lucabrasi.Utils.Helper;
import com.luca.lucabrasi.Utils.HttpParams;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Arbeit3Activity extends BaseActivity implements OnDataResponseListner {


    private static final String TAG = "Arbeit3Activity";
    @BindView(R.id.etendkilo)
    EditText etendkilo;

    @BindView(R.id.isaccident)
    Switch isaccident;

    @BindView(R.id.tvlevel)
    TextView tvlevel;

    @BindView(R.id.ettrankstand)
    SeekBar ettrankstand;

    int statusAccident;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arbei3);
        ButterKnife.bind(this);
        setTitle("Arbeit", View.GONE);

        isaccident.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    statusAccident = 1;
                } else {
                    statusAccident = 0;
                }
            }
        });
        ettrankstand.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvlevel.setText(String.valueOf(ettrankstand.getProgress()) + "%");
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });


    }

    @OnClick({R.id.tvendday})
    public void onViewClicked(View view) {

        switch (view.getId()) {
            case R.id.tvendday:
                if (etendkilo.getText().toString().isEmpty()) {
                    showShortToast(this, getString(R.string.plsenterendkilo));
                }else if(!(ettrankstand.getProgress() >= 0)){
                    showShortToast(this, getString(R.string.plsenterfulelevel));
                } else {
                    if (Helper.isNetworkConnected(this)) {

                        CommanAPI commanAPI = new CommanAPI(HttpParams.FINISHDAY, this);
                        Map<String, String> params = new HashMap<>();
                        params.put(HttpParams.driver_id, mAppPreference.getMemberID());
                       // params.put(HttpParams.day_id, mAppPreference.getDayid());
                        params.put(HttpParams.accident_status, String.valueOf(statusAccident));
                        params.put(HttpParams.end_kilometer, etendkilo.getText().toString());
                        params.put(HttpParams.end_fuel_level, String.valueOf(ettrankstand.getProgress()));
                        Log.e(TAG, "onViewClicked: " + params.toString());
                        commanAPI.postRequest(HttpParams.finishday, params);
                        Helper.showProgressBar(this, getResources().getString(R.string.please_wait));
                        hideKeyboard(this);
                    } else {
                        Toast.makeText(this, getString(R.string.No_Internet), Toast.LENGTH_SHORT).show();
                    }
                }
                //
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity(new Intent(this, HomeActivity.class));
        finish();
    }

    @Override
    public void Response(String methodName, String response, boolean isResponse) {
        Helper.hideProgressBar();
        if (isResponse) {
            if (methodName == HttpParams.FINISHDAY) {
                if (response != null) {
                    Commonmodel   commonmodel= new Gson().fromJson(response, new TypeToken<Commonmodel>() {}.getType());
                    if(commonmodel.status.equals("success")){
                        showShortToast(this,getString(R.string.dayended));
                        startActivity(new Intent(this, Arbeit4ThankyouActivity.class));
                        finish();
                    }else {
                        showShortToast(this,commonmodel.message);
                    }
                }
            }
        }else {
            showShortToast(this,getString(R.string.somethingwrong));
        }
    }

}