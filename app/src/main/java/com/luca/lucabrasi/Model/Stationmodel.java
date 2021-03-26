package com.luca.lucabrasi.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Stationmodel {


    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("data")
    @Expose
    public List<Datum> data = null;

    public Stationmodel withStatus(String status) {
        this.status = status;
        return this;
    }

    public Stationmodel withMessage(String message) {
        this.message = message;
        return this;
    }

    public Stationmodel withData(List<Datum> data) {
        this.data = data;
        return this;
    }
    public class Datum {


        @SerializedName("destination_id")
        @Expose
        public String destinationId;
        @SerializedName("name")
        @Expose
        public String name;

        public Datum withDestinationId(String destinationId) {
            this.destinationId = destinationId;
            return this;
        }

        public Datum withName(String name) {
            this.name = name;
            return this;
        }

    }
}
