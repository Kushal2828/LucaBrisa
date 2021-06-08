package com.app.LucaBrasi.CustomViews.EditText;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

public class MediumEditText extends androidx.appcompat.widget.AppCompatEditText {
    public MediumEditText(Context context) {
        super(context);
        init();
    }

    public MediumEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MediumEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @SuppressLint("WrongConstant")
    public void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "Nunito-SemiBold.ttf");
        setTypeface(tf, 1);
    }
}
