package com.luca.lucabrasi.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Startdaymodel {

    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("data")
    @Expose
    public Data data;

    public Startdaymodel withStatus(String status) {
        this.status = status;
        return this;
    }

    public Startdaymodel withMessage(String message) {
        this.message = message;
        return this;
    }

    public Startdaymodel withData(Data data) {
        this.data = data;
        return this;
    }
    public class Data {

        @SerializedName("day_id")
        @Expose
        public Integer dayId;

        public Data withDayId(Integer dayId) {
            this.dayId = dayId;
            return this;
        }

    }
}