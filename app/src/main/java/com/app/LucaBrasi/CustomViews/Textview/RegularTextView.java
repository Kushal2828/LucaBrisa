package com.app.LucaBrasi.CustomViews.Textview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

public class RegularTextView extends androidx.appcompat.widget.AppCompatTextView {
    public RegularTextView(Context context) {
        super(context);
        init();
    }

    public RegularTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RegularTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @SuppressLint("WrongConstant")
    public void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "Nunito-Regular.ttf");
        setTypeface(tf, 1);
    }
}
