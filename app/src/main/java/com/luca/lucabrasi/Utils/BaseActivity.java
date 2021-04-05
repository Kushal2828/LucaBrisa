package com.luca.lucabrasi.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.luca.lucabrasi.Activity.LoginActivity;
import com.luca.lucabrasi.CustomViews.bounceview.BounceView;
import com.luca.lucabrasi.R;

public class BaseActivity extends AppCompatActivity {
    public long backPressedTime;
    public Toast backToast;
    TextView tvTitle;
    ImageView imageView;
    LinearLayout llback;
    public AppPreference mAppPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        mAppPreference = new AppPreference(BaseActivity.this);
    }

    public void setTitle(String title, int gone) {
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        imageView = (ImageView) findViewById(R.id.back);
        tvTitle.setText(title);
        imageView.setVisibility(gone);
        llback = (LinearLayout) findViewById(R.id.llback);
        tvTitle.setText(title);
        imageView.setVisibility(gone);
        llback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvTitle.setTextColor(getResources().getColor(R.color.white));
    }

    public void showShortToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public void showLongToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }


    public void hideKeyboard(Activity activity) {
       /* InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
*/

        InputMethodManager inputManager = (InputMethodManager) activity.getSystemService(
                Context.INPUT_METHOD_SERVICE);
        View focusedView = activity.getCurrentFocus();
        /*
         * If no view is focused, an NPE will be thrown
         *
         * Maxim Dmitriev
         */

        if (focusedView != null) {
            inputManager.hideSoftInputFromWindow(focusedView.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
   public void setLogoutDialog(Context context,String title,String message) {
           AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(this);
            builder.setMessage(message);
            builder.setTitle(title);
            builder.setCancelable(false);
            builder.setNegativeButton(getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            builder.setPositiveButton(getResources().getString(R.string.sure), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {

                        mAppPreference.ClearSharedpreference();
                        Intent intent = new Intent(context, LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                }
            });

            AlertDialog alert = builder.create();
            alert.show();
            BounceView.addAnimTo(alert);        //Call before showing the dialog
            Button nbutton = alert.getButton(DialogInterface.BUTTON_NEGATIVE);
           nbutton.setTextColor(getResources().getColor(R.color.textcolor));
            Button pbutton = alert.getButton(DialogInterface.BUTTON_POSITIVE);
            pbutton.setTextColor(getResources().getColor(R.color.purple_200));
        }
}
