package com.app.LucaBrasi.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.app.LucaBrasi.APIclient.CommanAPI;
import com.app.LucaBrasi.Interfaces.OnDataResponseListner;
import com.app.LucaBrasi.Model.Loginmodel;
import com.app.LucaBrasi.R;
import com.app.LucaBrasi.Utils.BaseActivity;
import com.app.LucaBrasi.Utils.Helper;
import com.app.LucaBrasi.Utils.HttpParams;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;





/**
 * Created by Kushal on 7/6/18.
 */

public class LoginActivity extends BaseActivity implements OnDataResponseListner {

    private String TAG="LoginActivity";

    @BindView(R.id.email)
    EditText etemail;

    @BindView(R.id.password)
    EditText etpassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    public void loginclick(View view) {

        if (!Helper.emailValidator(etemail.getText().toString().trim())) {
            Toast.makeText(this, getString(R.string.entervalid_email), Toast.LENGTH_SHORT).show();
        } else if(etpassword.getText().toString().isEmpty()) {
            Toast.makeText(this, getString(R.string.entervalid_password), Toast.LENGTH_SHORT).show();
        }  else  {
            if (Helper.isNetworkConnected(this)) {
                CommanAPI commanAPI = new CommanAPI(HttpParams.LOGIN, this);
                Map<String, String> params = new HashMap<>();
                params.put(HttpParams.username,etemail.getText().toString().trim());
                params.put(HttpParams.password,etpassword.getText().toString());
                commanAPI.postRequest(HttpParams.login, params);
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
            if (methodName == HttpParams.LOGIN) {
                if (response != null) {
                    Loginmodel loginmodel = new Gson().fromJson(response, new TypeToken<Loginmodel>() {
                    }.getType());
                    if (loginmodel.status.equals(HttpParams.success)) {
                        mAppPreference.setMemberID(loginmodel.data.driverId);
                        Log.e(TAG, "Response:MemberID "+ mAppPreference.getMemberID());
                        startActivity(new Intent(this, HomeActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK));
                    } else {
                        showLongToast(this, loginmodel.message);
                    }

                    Log.e("TAG", "Response: " + new Gson().toJson(loginmodel));
                }
            }

        } else {
            showShortToast(this, getString(R.string.somethingwrong));
            Log.e(TAG, "Response: "+response );
        }

    }
}