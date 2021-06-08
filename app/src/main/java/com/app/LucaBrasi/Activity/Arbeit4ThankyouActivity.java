package com.app.LucaBrasi.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.app.LucaBrasi.R;
import com.app.LucaBrasi.Utils.BaseActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class Arbeit4ThankyouActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arbeit4_thankyou);
        ButterKnife.bind(this);
        setTitle(getString(R.string.home), View.GONE);
    }

    @OnClick({R.id.tvgotohome})
    public void onViewClicked(View view) {

        switch (view.getId()) {
            case R.id.tvgotohome:
                Intent intent = new Intent(this, HomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
                break;
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