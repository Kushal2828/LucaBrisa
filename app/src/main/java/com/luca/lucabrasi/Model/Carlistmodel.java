package com.luca.lucabrasi.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Carlistmodel {

    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("data")
    @Expose
    public List<Datum> data = null;

    public Carlistmodel withStatus(String status) {
        this.status = status;
        return this;
    }

    public Carlistmodel withMessage(String message) {
        this.message = message;
        return this;
    }

    public Carlistmodel withData(List<Datum> data) {
        this.data = data;
        return this;
    }
    public class Datum {

        @SerializedName("car_id")
        @Expose
        public String carId;
        @SerializedName("car_noplate")
        @Expose
        public String carNoplate;

        public Datum withCarId(String carId) {
            this.carId = carId;
            return this;
        }

        public Datum withCarNoplate(String carNoplate) {
            this.carNoplate = carNoplate;
            return this;
        }

    }
}