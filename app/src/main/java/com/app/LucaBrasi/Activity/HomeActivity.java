package com.app.LucaBrasi.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.app.LucaBrasi.APIclient.CommanAPI;
import com.app.LucaBrasi.Interfaces.OnDataResponseListner;
import com.app.LucaBrasi.Model.Getstatusmodel;
import com.app.LucaBrasi.R;
import com.app.LucaBrasi.Utils.BaseActivity;
import com.app.LucaBrasi.Utils.Helper;
import com.app.LucaBrasi.Utils.HttpParams;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends BaseActivity implements OnDataResponseListner {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        setTitle(getString(R.string.home), View.GONE);
        Log.e("TAG", "onCreate:driverid" + mAppPreference.getMemberID());

    }

    private void getstatus() {
        if (Helper.isNetworkConnected(this)) {
            CommanAPI commanAPI = new CommanAPI(HttpParams.GETDAYSTATUS, this);
            Map<String, String> params = new HashMap<>();
            params.put(HttpParams.driver_id, mAppPreference.getMemberID());
            commanAPI.postRequest(HttpParams.getdaystatus, params);
            Helper.showProgressBar(this, getResources().getString(R.string.please_wait));
        } else {
            Toast.makeText(this, getString(R.string.No_Internet), Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick({R.id.arbeit, R.id.tanken, R.id.profile, R.id.logout})
    public void onViewClicked(View view) {

        switch (view.getId()) {
            case R.id.arbeit:
                Intent intent = null;
                if (mAppPreference.getStepstatus().equals("1")) {
                    intent = new Intent(this, Arbeit2Activity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                } else if (mAppPreference.getStepstatus().equals("2")) {
                    intent = new Intent(this, Arbeit3Activity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                } else if (mAppPreference.getStepstatus().equals("3")) {
                    intent = new Intent(this, Arbeit4ThankyouActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                } else if (mAppPreference.getStepstatus().equals("0")) {
                    intent = new Intent(this, Arbeit1Activity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                }
                startActivity(intent);
                break;
            case R.id.tanken:
                Intent i = new Intent(this, TankenActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
                break;
            case R.id.profile:
                Intent i1 = new Intent(this, ReportActivity.class);
                i1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i1);
                break;
            case R.id.logout:
                setLogoutDialog(this, getString(R.string.logout), getString(R.string.do_you_really_want_to_signout));
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
            backToast = Toast.makeText(getBaseContext(), R.string.pressbackagain_exit, Toast.LENGTH_SHORT);
            backToast.show();
        }
        backPressedTime = System.currentTimeMillis();
    }

    @Override
    public void Response(String methodName, String response, boolean isResponse) {
        Helper.hideProgressBar();
        if (isResponse) {
            if (response != null) {
                Getstatusmodel getstatusmodel = new Gson().fromJson(response, new TypeToken<Getstatusmodel>() {
                }.getType());
                if (getstatusmodel.status.equals("success")) {
                    Log.e("TAG", "Response: " + response);
                    mAppPreference.setCarid(getstatusmodel.data.carId);
                    mAppPreference.setStepstatus(getstatusmodel.data.dayStepStatus);
                    Log.e("TAG", "Response: " + mAppPreference.getStepstatus());
                    Log.e("TAG", "Response:carid " + mAppPreference.getCaridID());
                }
            }
        }

        Log.e("TAG", "Response: " + response);

    }

    @Override
    protected void onResume() {
        super.onResume();
        getstatus();
        Log.e("TAG", "onResume: onresume" );
    }
}