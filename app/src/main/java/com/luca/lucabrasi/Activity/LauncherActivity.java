package com.luca.lucabrasi.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.luca.lucabrasi.R;
import com.luca.lucabrasi.Utils.BaseActivity;

public class LauncherActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(LauncherActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3000);

/*<LinearLayout
                    android:layout_width="match_parent"
                    android:layout_height="match_parent"
                    android:orientation="vertical">
                    <LinearLayout
                        android:layout_width="match_parent"
                        android:layout_height="wrap_content">

                        <ImageView
                            android:layout_width="match_parent"
                            android:layout_height="@dimen/_100sdp"
                            android:layout_gravity="center"
                            android:padding="@dimen/_5sdp"
                            android:src="@drawable/logolucabrasi"/>

                    </LinearLayout>

                    <LinearLayout
                        android:layout_width="match_parent"
                        android:layout_height="match_parent"
                        android:orientation="vertical"
                        android:padding="@dimen/_20sdp">

                        <LinearLayout
                            android:layout_width="match_parent"
                            android:layout_height="wrap_content"
                            android:layout_margin="@dimen/_10sdp">

                            <EditText
                                style="@style/txt_white_medium"
                                android:layout_width="match_parent"
                                android:layout_height="@dimen/_35sdp"
                                android:background="@drawable/bg"
                                android:drawableStart="@drawable/ic_email"
                                android:padding="@dimen/_5sdp" />
                        </LinearLayout>

                        <LinearLayout
                            android:layout_width="match_parent"
                            android:layout_height="wrap_content"
                            android:layout_margin="@dimen/_10sdp">

                            <EditText
                                style="@style/txt_white_medium"
                                android:layout_width="match_parent"
                                android:layout_height="@dimen/_35sdp"
                                android:background="@drawable/bg"
                                android:drawableStart="@drawable/ic_password"
                                android:padding="@dimen/_5sdp" />
                        </LinearLayout>

                        <LinearLayout
                            android:layout_width="match_parent"
                            android:layout_height="wrap_content"
                            android:layout_margin="@dimen/_10sdp">

                            <TextView
                                style="@style/txt_white_medium"
                                android:layout_width="match_parent"
                                android:layout_height="@dimen/_35sdp"
                                android:background="@drawable/colorbg"
                                android:gravity="center"
                                android:text="@string/login" />
                        </LinearLayout>

                    </LinearLayout>


            </LinearLayout>*/
    }
}