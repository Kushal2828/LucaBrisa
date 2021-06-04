package com.luca.lucabrasi.Utils;

/**
 * Created by Monil_ on 2/9/2021.
 */
public class HttpParams {
    public static final String BASE_URL = "http://lucabrasi.kpslogistik.de/api/";
    //credentials

    public static final String USERNAME = "f352e3ad70988bbdc547ec437713cf18";
    public static final String PASSWORD = "f352e3ad70988bbdc547ec437713cf18";


        //Api
    public static final String login = BASE_URL+"driver-login";
    public static final String getStations = BASE_URL+"get-destincations";
    public static final String startday = BASE_URL+"start-day";
    public static final String getcarlist = BASE_URL+"get-cars";
    public static final String addfule = BASE_URL+"add-fuel";
    public static final String getdaystatus = BASE_URL+"get-step-status";
    public static final String getdaytimestamp = BASE_URL+"get-day-start-timestamp";
    public static final String stoptimer = BASE_URL+"stop-timer";
    public static final String finishday = BASE_URL+"finish-day";
    public static final String sendmessage = BASE_URL+"send-message";



    //MethodName
    public static final String LOGIN = "login";
    public static final String GETSTATION = "station";
    public static final String STARTDAY = "startday";
    public static final String GETCARLIST = "carlist";
    public static final String ADDFULE = "addfule";
    public static final String GETDAYSTATUS = "getdaystatus";
    public static final String GETDAYTIMESTAMP = "getdaytimestamp";
    public static final String STOPTIMER = "stoptimer";
    public static final String FINISHDAY = "finishday";
    public static final String SENDMESSAGE = "sendmessage";
    //Params
    public static final String username = "username";
    public static final String password = "password";
    public static String success="success";


    public static final String station_id = "station_id";
    public static final String start_kilometer = "start_kilometer";
    public static final String car_id = "car_id";
    public static final String start_fuel_level = "start_fuel_level";
    public static final String driver_id = "driver_id";
    public static final String day_id = "day_id";
    public static final String kilometer = "kilometer";

    public static final String zipcode = "zipcode";
    public static final String fuel_amount = "fuel_amount";
    public static final String amount = "amount";
    public static final String oil_status = "oil_status";
    public static final String tanked_status = "tanked_status";


    public static String accident_status="accident_status";
    public static String end_kilometer="end_kilometer";
    public static String end_fuel_level="end_fuel_level";


    public static String message="message";
}


