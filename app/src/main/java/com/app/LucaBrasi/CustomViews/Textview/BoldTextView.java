package  com.app.LucaBrasi.CustomViews.Textview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

public class BoldTextView extends androidx.appcompat.widget.AppCompatTextView {
    public BoldTextView(Context context) {
        super(context);
        init();
    }

    public BoldTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BoldTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    @SuppressLint("WrongConstant")
    public void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "Nunito-Bold.ttf");
        setTypeface(tf, 1);
    }
}
