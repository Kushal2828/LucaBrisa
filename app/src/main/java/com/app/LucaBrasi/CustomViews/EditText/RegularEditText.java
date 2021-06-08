package com.app.LucaBrasi.CustomViews.EditText;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

public class RegularEditText extends androidx.appcompat.widget.AppCompatEditText {
    public RegularEditText(Context context) {
        super(context);
        init();
    }

    public RegularEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RegularEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @SuppressLint("WrongConstant")
    public void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "Nunito-Regular.ttf");
        setTypeface(tf, 1);
    }
}
