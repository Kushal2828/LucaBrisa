package com.luca.lucabrasi.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
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
import com.luca.lucabrasi.Model.Commonmodel;
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

public class TankenActivity extends BaseActivity implements OnDataResponseListner, OnDataResponceListner {

    @BindView(R.id.etkilometer)
    EditText etkilometer;

    @BindView(R.id.etzapcode)
    EditText etzapcode;


    @BindView(R.id.etdiesel)
    EditText etdiesel;

    @BindView(R.id.eteuro)
    EditText eteuro;

    @BindView(R.id.oilgentek)
    Switch oilgentek;

    @BindView(R.id.bluefulent)
    Switch bluefulent;

    @BindView(R.id.rv_filter)
    RecyclerView rv_filter;

    @BindView(R.id.llsuugest)
    LinearLayout llsuugest;

    @BindView(R.id.etkenizichen)
    EditText etkenizichen;



    int isbluefulent,isoilgetank;
    private String TAG="TankenActivity";
    FilterAdapter filterAdapter;
    List<Carlistmodel.Datum> carlist = new ArrayList<>();
    int Carid;
    String Carname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tanken);
        ButterKnife.bind(this);
        setTitle("Tanken",View.VISIBLE);
        getcarlist();
        setFilterAdapter();

        Log.e(TAG, "onCreate: "+mAppPreference.getCaridID() );


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











        oilgentek.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    isoilgetank=1;
                }else {
                    isoilgetank=0;
                }
            }
        });
        bluefulent.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    isbluefulent=1;
                }else {
                    isbluefulent=0;
                }
            }
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

    private void getcarlist() {
        if (Helper.isNetworkConnected(this)) {
            CommanAPI commanAPI = new CommanAPI(HttpParams.GETCARLIST, this);
            commanAPI.getResponse(HttpParams.getcarlist);
            Helper.showProgressBar(this, getResources().getString(R.string.please_wait));
        } else {
            Toast.makeText(this, getString(R.string.No_Internet), Toast.LENGTH_SHORT).show();
        }
    }
    public void addfuleclick(View view) {

        if(Carid == 0){

        } else if (etkilometer.getText().toString().isEmpty()) {
            showShortToast(this, getString(R.string.entervalid_kilometer));
        } else if(etzapcode.getText().toString().isEmpty()) {
            showShortToast(this, getString(R.string.entervalid_zipcode));
        } else if(etdiesel.getText().toString().isEmpty()) {
            showShortToast(this, getString(R.string.entervalid_disel));
        } else if(eteuro.getText().toString().isEmpty()) {
            showShortToast(this, getString(R.string.entervalid_euro));
        } else {

            if (Helper.isNetworkConnected(this)) {

                CommanAPI commanAPI = new CommanAPI(HttpParams.ADDFULE, this);
                Map<String, String> params = new HashMap<>();
              //  params.put(HttpParams.day_id,mAppPreference.getDayid());
                params.put(HttpParams.driver_id,mAppPreference.getMemberID());
                params.put(HttpParams.kilometer,etkilometer.getText().toString());
                params.put(HttpParams.zipcode,etzapcode.getText().toString());
                params.put(HttpParams.fuel_amount,etdiesel.getText().toString());
                params.put(HttpParams.amount,eteuro.getText().toString());
                params.put(HttpParams.oil_status, String.valueOf(isoilgetank));
                params.put(HttpParams.tanked_status, String.valueOf(isbluefulent));
                params.put(HttpParams.car_id, String.valueOf(Carid));


                commanAPI.postRequest(HttpParams.addfule, params);
                Log.e("TAG", "addfuleclick: "+params.toString() );
                Helper.showProgressBar(this, getResources().getString(R.string.please_wait));
                hideKeyboard(this);
            } else {
                Toast.makeText(this, getString(R.string.No_Internet), Toast.LENGTH_SHORT).show();
            }

        }

    }

    @Override
    public void Response(String methodName, String response, boolean isResponse) {
        Helper.hideProgressBar();

        if(methodName == HttpParams.ADDFULE){
            if (response != null) {
                Commonmodel commonmodel = new Gson().fromJson(response, new TypeToken<Commonmodel>() {
                }.getType());
                if (commonmodel.status.equals(HttpParams.success)) {
                showLongToast(this,getString(R.string.fuleinfoadded));
                }else {
                    showLongToast(this,commonmodel.message);
                }
            }
            Log.e("TAG", "Response: "+response);




        }else if (methodName == HttpParams.GETCARLIST) {
            if (response != null) {
                Log.e("TAG", "Response: "+response);
                Carlistmodel carlistmodel = new Gson().fromJson(response, new TypeToken<Carlistmodel>() {
                }.getType());
                if (carlistmodel.status.equals(HttpParams.success)) {

                    carlist.addAll(carlistmodel.data);
                    filterAdapter.notifyDataSetChanged();

                    if(!mAppPreference.getCaridID().equals("0")){
                        Log.e(TAG, "onCreate:getcarid "+mAppPreference.getCaridID() );
                        Log.e(TAG, "onCreate:carlistsize "+carlist.size() );
                        if(carlist.size() > 0 ){
                            for (int i = 0; i <carlist.size() ; i++) {

                                if(mAppPreference.getCaridID().equals(carlist.get(i).carId)){
                                    Log.e(TAG, "onCreate:carid "+carlist.get(i).carId );
                                    Carid = Integer.parseInt(carlist.get(i).carId);
                                    Carname = carlist.get(i).carNoplate;
                                    etkenizichen.setText(Carname);
                                    llsuugest.setVisibility(View.GONE);

                                }
                            }
                        }else {
                            Log.e(TAG, "carlist is empty" );
                        }

                    }









                    Log.e(TAG, "Response:carlist " + new Gson().toJson(carlist));
                } else {
                    showLongToast(this, carlistmodel.message);
                }

                Log.e("TAG", "Response: " + new Gson().toJson(carlistmodel));

            }
        }



    }

    @Override
    public void OnClick(String carnumber, String carid) {
        Carid = Integer.parseInt(carid);
        Carname = carnumber;
        etkenizichen.setText(Carname);
        llsuugest.setVisibility(View.GONE);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity(new Intent(this, HomeActivity.class));
        finish();
    }
}