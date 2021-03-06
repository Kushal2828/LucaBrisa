package com.app.LucaBrasi.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Scroller;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.app.LucaBrasi.APIclient.CommanAPI;
import com.app.LucaBrasi.Interfaces.OnDataResponseListner;
import com.app.LucaBrasi.Model.Commonmodel;
import com.app.LucaBrasi.R;
import com.app.LucaBrasi.Utils.BaseActivity;
import com.app.LucaBrasi.Utils.Helper;
import com.app.LucaBrasi.Utils.HttpParams;

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
        setTitle(getString(R.string.promeldenheader), View.VISIBLE);
        etmessage.setScroller(new Scroller(this));
        etmessage.setVerticalScrollBarEnabled(true);
        etmessage.setMovementMethod(new ScrollingMovementMethod());
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
                    Commonmodel commonmodel = new Gson().fromJson(response, new TypeToken<Commonmodel>() {}.getType());
                    if (commonmodel.status.equals(HttpParams.success)) {
                        showLongToast(this, getString(R.string.messagesend));
                        etmessage.getText().clear();
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(this, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}