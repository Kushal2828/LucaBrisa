package com.luca.lucabrasi.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Getstatusmodel {

    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("data")
    @Expose
    public Data data;

    public Getstatusmodel withStatus(String status) {
        this.status = status;
        return this;
    }

    public Getstatusmodel withMessage(String message) {
           this.message = message;
        return this;
    }

    public Getstatusmodel withData(Data data) {
        this.data = data;
        return this;
    }
    public class Data {

        @SerializedName("day_step_status")
        @Expose
        public String dayStepStatus;
        @SerializedName("car_id")
        @Expose
        public String carId;

        public Data withDayStepStatus(String dayStepStatus) {
            this.dayStepStatus = dayStepStatus;
            return this;
        }

        public Data withCarId(String carId) {
            this.carId = carId;
            return this;
        }

    }
}