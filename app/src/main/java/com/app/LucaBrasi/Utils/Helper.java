package com.app.LucaBrasi.Utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.Transformation;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.view.animation.PathInterpolatorCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import com.app.LucaBrasi.R;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/*
 * Created by Kushal Prajapati on 22/10/2020
 */


public class Helper {

    public static ProgressDialog progressDialog;
    private static boolean isInternetPresent;
    private static String TAG = "Helper";
    private static AppPreference mAppPreference;

    /* Method for Check Internet Connetoion*/

    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if (ni == null) {
            // There are no active networks.
            return false;
        } else {
            return true;
        }

    }
    /*Show Toast*/

    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * validate your email address format. Ex-akhi@mani.com
     */
    public static boolean emailValidator(String email) {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /* Hide Keybord  */
    public static void hideKeyboard(Activity activity, AppCompatEditText editText) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    /* Show ProgressDialog */

    public static void showProgressBar(Context mContext, String message) {

        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.setMessage(message);
            progressDialog.setCancelable(false);
            progressDialog.dismiss();
        }
        progressDialog = new ProgressDialog(mContext,R.style.AppCompatAlertDialogStyle);
        progressDialog.setMessage(message);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    /* Dismiss ProgressDialog */

    public static void hideProgressBar() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    /**
     * Formate Date And Time
     */
    public static String formatDateTime(String date) {
//        2017-12-23 05:19:44
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        String strDate = "";

        try {
            Date mDate = sdf.parse(date);
            SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy,HH:mm");
            strDate = sdf2.format(mDate);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return strDate;
    }

    public static void setGlideProgress(Context context, String URL, ImageView imageView, final ProgressBar progressBar) {
        progressBar.setVisibility(View.VISIBLE);

        if (!URL.isEmpty()) {
            Glide.with(context).load(URL)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .listener(new RequestListener<String, GlideDrawable>() {
                        @Override
                        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                            progressBar.setVisibility(View.GONE);
                            return false;
                        }
                        @Override
                        public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                            progressBar.setVisibility(View.GONE);
                            return false;
                        }
                    })
                    .into(imageView);
        } else {
            Glide.with(context).load(Constant.placeHolderImage)
                    .error(R.mipmap.ic_launcher)
                    .placeholder(R.mipmap.ic_launcher)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .listener(new RequestListener<String, GlideDrawable>() {
                        @Override
                        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                            progressBar.setVisibility(View.GONE);
                            return false;
                        }
                        @Override
                        public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                            progressBar.setVisibility(View.GONE);
                            return false;
                        }
                    })
                    .into(imageView);
        }
    }


    public static void setGlide(Context mContext, String URL, ImageView imageView) {

        if (!URL.isEmpty()) {
            Glide.with(mContext).load(URL)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageView);
        } else {
            Glide.with(mContext).load(Constant.placeHolderImage)
                    .error(R.mipmap.ic_launcher)
                    .placeholder(R.mipmap.ic_launcher)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageView);
        }
    }


    /**
     * Sharing your Application
     */
  /*  public static void shareApp(final Context mContext, String package_name) {
        try {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, mContext.getResources().getString(R.string.share_text) + " https://play.google.com/store/apps/details?id=" + package_name);
            sendIntent.setType("text/plain");
            mContext.startActivity(sendIntent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    /**
     * Rate your Application
     */

    public static void rateThisApp(final Context mContext, String package_name) {
        try {
            mContext.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=" + package_name)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Start Any Activity
     */

    private static boolean MyStartActivity(Context mContext, Intent aIntent) {
        // TODO Auto-generated method stub
        try {
            mContext.startActivity(aIntent);
            return true;
        } catch (ActivityNotFoundException e) {
            return false;
        }
    }

   /* @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void setStatusBarGradiant(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            Drawable background = activity.getResources().getDrawable(R.drawable.gradiyant_statusbar);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(activity.getResources().getColor(android.R.color.transparent));
            window.setNavigationBarColor(activity.getResources().getColor(android.R.color.transparent));
            window.setBackgroundDrawable(background);
        }
    }*/



   /* public static String getcolorAccent(Context mContext) {

        mAppPreference = new AppPreference(mContext);
        mAppPreference.getColorAccent();

        if (mAppPreference.getColorAccent() != null && !mAppPreference.getColorAccent().isEmpty()) {
            return mAppPreference.getColorAccent();
        } else {
            return Constant.colorAccent;
        }
    }*/

 /*   public static void setThemeColor(Context mContext, String colorPrimary, String colorPrimaryDark, String colorAccent) {

        mAppPreference = new AppPreference(mContext);
        mAppPreference.setColorPrimary(colorPrimary);
        mAppPreference.setColorPrimaryDark(colorPrimaryDark);
        mAppPreference.setColorAccent(colorAccent);
    }
*/
    /*public static void setStatusbar(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = ((Activity) context).getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor(Helper.getcolorPrimary(context)));
        }
    }
*/
    public static void expandCollapse(final View view) {

        final boolean expand = view.getVisibility() == View.GONE;
        Interpolator easeInOutQuart = PathInterpolatorCompat.create(0.77f, 0f, 0.175f, 1f);

        view.measure(
                View.MeasureSpec.makeMeasureSpec(((View) view.getParent()).getWidth(), View.MeasureSpec.EXACTLY),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        );

        final int height = view.getMeasuredHeight();
        int duration = (int) (height / view.getContext().getResources().getDisplayMetrics().density);

        Animation animation = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if (expand) {
                    view.getLayoutParams().height = 1;
                    view.setVisibility(View.VISIBLE);
                    if (interpolatedTime == 1) {
                        view.getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;
                    } else {
                        view.getLayoutParams().height = (int) (height * interpolatedTime);
                    }
                    view.requestLayout();
                } else {
                    if (interpolatedTime == 1) {
                        view.setVisibility(View.GONE);
                    } else {
                        view.getLayoutParams().height = height - (int) (height * interpolatedTime);
                        view.requestLayout();
                    }
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        animation.setInterpolator(easeInOutQuart);
        animation.setDuration(duration);
        view.startAnimation(animation);
    }

   /* public static void deprecateText(Context mContext, String PriceSymbol, String PricedePrice, String SalePrice, String offervalue, TextView textView) {


        if (Constant.seperator != null && Constant.seperator.length() > 0) {

            if (Constant.seperator.equalsIgnoreCase("1")) {

                if (Constant.thousandSeperatorSymbol != null) {
                    if (!PricedePrice.isEmpty()) {
                        PricedePrice = setThousand(Double.parseDouble(PricedePrice));
                    }

                    if (!SalePrice.isEmpty()) {
                        SalePrice = setThousand(Double.parseDouble(SalePrice));
                    }

                }
            }
        }

        if (Constant.symbolPosition.equalsIgnoreCase("1")) {

            if (!PricedePrice.isEmpty()) {
                PricedePrice = PriceSymbol + PricedePrice;
            }
            if (!SalePrice.isEmpty()) {
                SalePrice = PriceSymbol + SalePrice;
            }
        } else if (Constant.symbolPosition.equalsIgnoreCase("2")) {

            if (!PricedePrice.isEmpty()) {
                PricedePrice = PricedePrice + PriceSymbol;
            }
            if (!SalePrice.isEmpty()) {
                SalePrice = SalePrice + PriceSymbol;
            }

        } else if (Constant.symbolPosition.equalsIgnoreCase("3")) {

            if (!PricedePrice.isEmpty()) {
                PricedePrice = PriceSymbol + " " + PricedePrice;
            }
            if (!SalePrice.isEmpty()) {
                SalePrice = PriceSymbol + " " + SalePrice;
            }

        } else if (Constant.symbolPosition.equalsIgnoreCase("4")) {
            if (!PricedePrice.isEmpty()) {
                PricedePrice = PricedePrice + " " + PriceSymbol;
            }
            if (!SalePrice.isEmpty()) {
                SalePrice = SalePrice + " " + PriceSymbol;
            }
        }

        String finalprice;
        finalprice = getColoredSpanned(SalePrice, Helper.getcolorPrimary(mContext));
        if (!SalePrice.isEmpty()) {
            textView.setText((Html.fromHtml("<strike><font color='#757575'>" + PricedePrice + "</font></strike>" + " " + finalprice + " " + "<font color='#757575'><small>" + offervalue + "</small> </font> ")));
        } else {
            textView.setText((Html.fromHtml(PricedePrice + " " + "<font color='#757575'> <small>" + offervalue + "</small> </font> ")));
        }
    }
*/
    public static String setThousand(Double digit) {
        return new DecimalFormat("#" + Constant.thousandSeperatorSymbol + "###.##").format(digit);
    }

    private static String getColoredSpanned(String text, String color) {
        String input = "<font color=" + color + ">" + text + "</font>";
        return input;
    }



  /*  public static String setDecimalTwo(Double digit) {
        return new DecimalFormat("##.##").format(digit);
    }*/

     /*   @SuppressLint("NewApi")
    private static void setTextViewDrawableColor(TextView textView, String color) {
        for (Drawable drawable : textView.getCompoundDrawables()) {
            if (drawable != null) {

                PorterDuffColorFilter porterDuffColorFilter = new PorterDuffColorFilter(Color.parseColor(color),
                        PorterDuff.Mode.SRC_ATOP);
                drawable.setColorFilter(porterDuffColorFilter);
            }
        }
    }*/

    /*Gson gson = new Gson();
    String json = mPrefs.getString("MyObject", "");
    MyObject obj = gson.fromJson(json, MyObject.class);*/

  /*  //Todo: used for change color of expand
    public static void changeColor(LinearLayout linearLayout, ImageView imageView, TextView textView, Activity activity) {

        PorterDuffColorFilter porterDuffColorFilter = new PorterDuffColorFilter(activity.getResources().getColor(R.color.overlay_light_90),
                PorterDuff.Mode.SRC_ATOP);
        imageView.setColorFilter(porterDuffColorFilter);
        textView.setTextColor(activity.getResources().getColor(R.color.overlay_light_90));
        GradientDrawable gradientDrawable = (GradientDrawable) linearLayout.getBackground();
        gradientDrawable.setColor(Color.parseColor(Helper.getcolorPrimary(activity)));
    }

    //Todo: used for change color of  collapse
    public static void ResetColor(LinearLayout linearLayout, ImageView imageView, TextView textView, Activity activity) {

        PorterDuffColorFilter porterDuffColorFilter = new PorterDuffColorFilter(activity.getResources().getColor(R.color.grey_1000),
                PorterDuff.Mode.SRC_ATOP);
        imageView.setColorFilter(porterDuffColorFilter);
        //textView.setTextColor(Color.parseColor(Helper.getcolorPrimary(CarDetailActivity.this)));
        textView.setTextColor(activity.getResources().getColor(R.color.grey_1000));
        GradientDrawable gradientDrawable = (GradientDrawable) linearLayout.getBackground();
        gradientDrawable.setColor(Color.parseColor("#e7e7e7"));
        //gradientDrawable.setStroke(3, Color.parseColor(Helper.getcolorPrimary(CarDetailActivity.this)));
    }
*/
}
