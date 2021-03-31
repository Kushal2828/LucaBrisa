package com.luca.lucabrasi.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.luca.lucabrasi.APIclient.CommanAPI;
import com.luca.lucabrasi.Adapter.FilterAdapter;
import com.luca.lucabrasi.Interfaces.OnDataResponceListner;
import com.luca.lucabrasi.Interfaces.OnDataResponseListner;
import com.luca.lucabrasi.Model.Carlistmodel;
import com.luca.lucabrasi.Model.Startdaymodel;
import com.luca.lucabrasi.Model.Stationmodel;
import com.luca.lucabrasi.R;
import com.luca.lucabrasi.Utils.BaseActivity;
import com.luca.lucabrasi.Utils.Helper;
import com.luca.lucabrasi.Utils.HttpParams;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Arbeit1Activity extends BaseActivity implements OnDataResponseListner, OnDataResponceListner {


    @BindView(R.id.spStationlist)
    Spinner spStationlist;

    @BindView(R.id.etstartkilo)
    EditText etstartkilo;

    @BindView(R.id.etkenizichen)
    EditText etkenizichen;

    @BindView(R.id.tvpercentage)
    TextView tvpercentage;


    @BindView(R.id.ettrankstand)
    SeekBar ettrankstand;


    @BindView(R.id.rv_filter)
    RecyclerView rv_filter;

    @BindView(R.id.llsuugest)
    LinearLayout llsuugest;


    List<String> stationlist = new ArrayList<>();
    List<Stationmodel.Datum> list = new ArrayList<>();
    List<Carlistmodel.Datum> carlist = new ArrayList<>();
    private String TAG = "Arbeit1Activity";
    FilterAdapter filterAdapter;
    int Carid;
    String Carname, locationid;
    boolean selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arbeit1);
        ButterKnife.bind(this);
        setTitle("Arbeit", View.GONE);
        stationlist.add(0, "Select Station");
        getstation();
        setFilterAdapter();
        etkenizichen.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
                llsuugest.setVisibility(View.VISIBLE);
            }
        });

        etkenizichen.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    if (etkenizichen.getText().toString().trim() != null) {
                        Log.e(TAG, "onFocusChange: +visible");
                        llsuugest.setVisibility(View.VISIBLE);
                    }
                } else if (!hasFocus) {
                    llsuugest.setVisibility(View.GONE);
                }
            }
        });

        spStationlist.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
/*
                    if(position == 0)
                    {
                        selected = false;
                    }else {
                        for (int i = 0; i <list.size() ; i++) {
                            if(list.get(i).name == )
                        }
                        selected = true;
                    }*/
                if (position != 0) {
                    Log.e(TAG, "onItemSelected: " + list.get(position - 1).name);
                    selected = true;
                    locationid = list.get(position - 1).destinationId;
                    Log.e(TAG, "onItemSelected: " + list.get(position - 1).destinationId);
                } else {

                    selected = false;
                    locationid = "Select Station";
                    Log.e(TAG, "onItemSelected: " + locationid);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
        ettrankstand.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvpercentage.setText(String.valueOf(ettrankstand.getProgress()) + "%");
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });


    }

    private void filter(String text) {
        List<Carlistmodel.Datum> filteredList = new ArrayList<>();
        for (Carlistmodel.Datum item : carlist) {
            if (item.carNoplate.toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        filterAdapter.filterList(filteredList);
    }

    private void getstation() {
        if (Helper.isNetworkConnected(this)) {
            CommanAPI commanAPI = new CommanAPI(HttpParams.GETSTATION, this);
            commanAPI.getResponse(HttpParams.getStations);
            Helper.showProgressBar(this, getResources().getString(R.string.please_wait));
        } else {
            Toast.makeText(this, getString(R.string.No_Internet), Toast.LENGTH_SHORT).show();
        }
    }

    private void getcarlist() {
        if (Helper.isNetworkConnected(this)) {
            CommanAPI commanAPI = new CommanAPI(HttpParams.GETCARLIST, this);
            commanAPI.getResponse(HttpParams.getcarlist);
            Helper.showProgressBar(this, getResources().getString(R.string.please_wait));
        } else {
            Toast.makeText(this, getString(R.string.No_Internet), Toast.LENGTH_SHORT).show();
        }
    }


    private void Set_SpinnerData(List<Stationmodel.Datum> sportapilist) {

        for (int i = 0; i < sportapilist.size(); i++) {
            stationlist.add(sportapilist.get(i).name);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.support_simple_spinner_dropdown_item, stationlist);
        spStationlist.setAdapter(adapter);

    }

    @OnClick({R.id.tvstartday})
    public void onViewClicked(View view) {

        switch (view.getId()) {
            case R.id.tvstartday:


                if (locationid.equals("Select Station")) {
                    showShortToast(this, getString(R.string.select_station));
                } else if (etstartkilo.getText().toString().isEmpty()) {
                    showShortToast(this, getString(R.string.enter_start_kilometer));
                } else if (etkenizichen.getText().toString().isEmpty()) {
                    showShortToast(this, getString(R.string.enter_start_kennzeichen));
                } else {
                    if (Helper.isNetworkConnected(this)) {

                        CommanAPI commanAPI = new CommanAPI(HttpParams.STARTDAY, this);
                        Map<String, String> params = new HashMap<>();
                        params.put(HttpParams.station_id, locationid);
                        params.put(HttpParams.start_kilometer, etstartkilo.getText().toString());
                        params.put(HttpParams.car_id, String.valueOf(Carid));
                        params.put(HttpParams.start_fuel_level, String.valueOf(ettrankstand.getProgress()));
                        params.put(HttpParams.driver_id, mAppPreference.getMemberID());
                        Log.e(TAG, "onViewClicked: "+params.toString() );
                        commanAPI.postRequest(HttpParams.startday, params);
                        Helper.showProgressBar(this, getResources().getString(R.string.please_wait));
                        hideKeyboard(this);
                    } else {
                        Toast.makeText(this, getString(R.string.No_Internet), Toast.LENGTH_SHORT).show();
                    }
                }


               // startActivity(new Intent(this, Arbeit2Activity.class));
                break;
        }

    }

    public void setFilterAdapter() {
        Log.e("TAG", "setSportlifeCertificate: " + "called");
        filterAdapter = new FilterAdapter(carlist, this, this);
        final LinearLayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv_filter.setLayoutManager(mLayoutManager);
        rv_filter.setNestedScrollingEnabled(true);
        rv_filter.setHasFixedSize(true);
        rv_filter.setItemViewCacheSize(25);
        rv_filter.setAdapter(filterAdapter);
        // sport_life_certificateAdapter.addAll(playerList);
    }

    @Override
    public void Response(String methodName, String response, boolean isResponse) {
        Helper.hideProgressBar();
        if (isResponse) {
            if (methodName == HttpParams.GETSTATION) {
                if (response != null) {
                    Stationmodel stationmodel = new Gson().fromJson(response, new TypeToken<Stationmodel>() {
                    }.getType());
                    if (stationmodel.status.equals(HttpParams.success)) {

                        list.addAll(stationmodel.data);
                        Set_SpinnerData(list);


                    } else {
                        showLongToast(this, stationmodel.message);
                    }
                    Log.e("TAG", "Response: " + new Gson().toJson(stationmodel));

                }
                getcarlist();
            } else if (methodName == HttpParams.GETCARLIST) {
                if (response != null) {
                    Carlistmodel carlistmodel = new Gson().fromJson(response, new TypeToken<Carlistmodel>() {
                    }.getType());
                    if (carlistmodel.status.equals(HttpParams.success)) {

                        carlist.addAll(carlistmodel.data);
                        filterAdapter.notifyDataSetChanged();
                        Log.e(TAG, "Response:carlist " + new Gson().toJson(carlist));
                    } else {
                        showLongToast(this, carlistmodel.message);
                    }

                    Log.e("TAG", "Response: " + new Gson().toJson(carlistmodel));

                }
            }else if (methodName == HttpParams.STARTDAY) {
                if (response != null) {
                    Startdaymodel startdaymodel = new Gson().fromJson(response, new TypeToken<Startdaymodel>() {
                    }.getType());
                    if (startdaymodel.status.equals(HttpParams.success)) {

                        Log.e(TAG, "Response:carlist " + new Gson().toJson(carlist));
                        mAppPreference.setDayid(String.valueOf(startdaymodel.data.dayId));
                        Log.e(TAG, "Response:dayid "+mAppPreference.getDayid() );

                        showShortToast(this,"Day Started Successfully");
                        startActivity(new Intent(this, Arbeit2Activity.class));
                        finish();
                    } else {
                        showLongToast(this, startdaymodel.message);
                    }

                    Log.e("TAG", "Response: " + new Gson().toJson(startdaymodel));
                }
            }

        } else {
            showShortToast(this, getString(R.string.somethingwrong));
            Log.e(TAG, "Response: " + response);
        }


    }

    @Override
    public void OnClick(String carnumber, String carid) {
        Carid = Integer.parseInt(carid);
        Carname = carnumber;
        etkenizichen.setText(Carname);
        llsuugest.setVisibility(View.GONE);
    }

    public void Startdayclick(View view) {

        /*if (etemail.getText().toString().trim()) {
            Toast.makeText(this, "Email is empty", Toast.LENGTH_SHORT).show();
        } else if(etpassword.getText().toString().isEmpty()) {
            Toast.makeText(this, "Password is empty", Toast.LENGTH_SHORT).show();
        }  else  {
            if (Helper.isNetworkConnected(this)) {

                CommanAPI commanAPI = new CommanAPI(HttpParams.LOGIN, this);
                Map<String, String> params = new HashMap<>();
                params.put(HttpParams.username,etemail.getText().toString().trim());
                params.put(HttpParams.password,etpassword.getText().toString());
                commanAPI.postRequest(HttpParams.login, params);
                Helper.showProgressBar(this, getResources().getString(R.string.please_wait));
            } else {
                Toast.makeText(this, getString(R.string.No_Internet), Toast.LENGTH_SHORT).show();
            }

        }*/


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity(new Intent(this, HomeActivity.class));
        finish();
    }
}