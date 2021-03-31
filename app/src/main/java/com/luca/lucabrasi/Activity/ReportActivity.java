package com.luca.lucabrasi.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.luca.lucabrasi.APIclient.CommanAPI;
import com.luca.lucabrasi.Interfaces.OnDataResponseListner;
import com.luca.lucabrasi.Model.Commonmodel;
import com.luca.lucabrasi.R;
import com.luca.lucabrasi.Utils.BaseActivity;
import com.luca.lucabrasi.Utils.Helper;
import com.luca.lucabrasi.Utils.HttpParams;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReportActivity extends BaseActivity implements OnDataResponseListner {


    private static final String TAG = "ReportActivity";

    @BindView(R.id.etmessage)
    EditText etmessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        ButterKnife.bind(this);
        setTitle("Problem Melden", View.VISIBLE);

    }

    public void reportclick(View view) {

        if (etmessage.getText().toString().isEmpty()) {
            showShortToast(this, getString(R.string.plsentermessage));
        } else {
            if (Helper.isNetworkConnected(this)) {

                CommanAPI commanAPI = new CommanAPI(HttpParams.SENDMESSAGE, this);
                Map<String, String> params = new HashMap<>();
                params.put(HttpParams.driver_id, mAppPreference.getMemberID());
                params.put(HttpParams.message, etmessage.getText().toString());
                Log.e(TAG, "onViewClicked: " + params.toString());
                commanAPI.postRequest(HttpParams.sendmessage, params);
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
        if (isResponse) {


            if (methodName == HttpParams.SENDMESSAGE) {
                if (response != null) {
                    Commonmodel commonmodel = new Gson().fromJson(response, new TypeToken<Commonmodel>() {
                    }.getType());
                    if (commonmodel.status.equals(HttpParams.success)) {
                        showLongToast(this, getString(R.string.messagesend));
                    } else {
                        showLongToast(this, commonmodel.message);
                    }
                }
                Log.e("TAG", "Response: " + response);
            }

        }else {
            showLongToast(this, getString(R.string.somethingwrong));
        }
    }
}