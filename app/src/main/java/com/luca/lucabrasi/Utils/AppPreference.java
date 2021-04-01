package com.luca.lucabrasi.Utils;

import android.content.Context;
import android.content.SharedPreferences;

/*
 * Created by Kushal Prajapati on 22/10/2020
 */
public class AppPreference {
    private static final String PREFERENCE_NAMEs = "services";

    public String MemberData = "MemberData";
    public String MemberRole = "MemberRole";



    public String Dayid = "Dayid";
    public String MemberID = "MemberID";
    public String Carid = "Carid";
    public String Stepstatus = "Stepstatus";

    public String Starttime = "Starttime";



    private int PRIVATE_MODE = 0;

    private Context mContext;

    private SharedPreferences mSharedPreference;
    private SharedPreferences.Editor mEditors;

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    public AppPreference(Context context) {
        this.mContext = context;
        SharedPreferences sharedPreferences = context.getSharedPreferences("service", 0);
        this.mSharedPreferences = sharedPreferences;
        this.mEditor = sharedPreferences.edit();

        SharedPreferences sharedPreferences2 = this.mContext.getSharedPreferences(PREFERENCE_NAMEs, this.PRIVATE_MODE);
        this.mSharedPreference = sharedPreferences2;
        this.mEditors = sharedPreferences2.edit();
    }









    public String getMemberID() {
        return this.mSharedPreferences.getString(this.MemberID, "");
    }

    public void setMemberID(String memberID) {
        this.mEditor.putString(this.MemberID, memberID).commit();
    }

    public String getMemberData() {
        return this.mSharedPreferences.getString(this.MemberData, "");
    }

    public void setMemberData(String memberData) {
        this.mEditor.putString(this.MemberData, memberData).commit();
    }

    public String getDayid() {
        return this.mSharedPreferences.getString(this.Dayid, "");
    }

    public void setDayid(String memberData) {
        this.mEditor.putString(this.Dayid, memberData).commit();
    }




    public String getCaridID() {
        return this.mSharedPreferences.getString(this.Carid, "0");
    }

    public void setCarid(String dayid) {
        this.mEditor.putString(this.Carid, dayid).commit();
    }


    public String getStepstatus() {
        return this.mSharedPreferences.getString(this.Stepstatus, "0");
    }

    public void setStepstatus(String dayid) {
        this.mEditor.putString(this.Stepstatus, dayid).commit();
    }


    public String getStarttime() {
        return this.mSharedPreferences.getString(this.Starttime, "0");
    }

    public void setStarttime(String starttime) {
        this.mEditor.putString(this.Starttime, starttime).commit();
    }


    public void ClearSharedpreference() {
        this.mEditor.clear();
        this.mEditor.commit();
    }
}