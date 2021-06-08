package  com.app.LucaBrasi.CustomViews.EditText;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

public class BoldEditText extends androidx.appcompat.widget.AppCompatEditText {
    public BoldEditText(Context context) {
        super(context);
        init();
    }

    public BoldEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BoldEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @SuppressLint("WrongConstant")
    public void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "Nunito-Bold.ttf");
        setTypeface(tf, 1);
    }
}
