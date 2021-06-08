package com.app.LucaBrasi.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Loginmodel {




        @SerializedName("status")
        @Expose
        public String status;
        @SerializedName("message")
        @Expose
        public String message;
        @SerializedName("data")
        @Expose
        public Data data;

        public Loginmodel withStatus(String status) {
            this.status = status;
            return this;
        }

        public Loginmodel withMessage(String message) {
            this.message = message;
            return this;
        }

        public Loginmodel withData(Data data) {
            this.data = data;
            return this;
        }

    public class Data {

        @SerializedName("driver_id")
        @Expose
        public String driverId;

        public Data withDriverId(String driverId) {
            this.driverId = driverId;
            return this;
        }

    }

}
