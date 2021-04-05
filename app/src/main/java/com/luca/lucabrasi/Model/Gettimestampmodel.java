package com.luca.lucabrasi.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Gettimestampmodel {

    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("data")
    @Expose
    public Data data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
    public class Data {


            @SerializedName("timestamp")
            @Expose
            public String timestamp;
            @SerializedName("current_time")
            @Expose
            public String currentTime;
            @SerializedName("time_difference")
            @Expose
            public String timeDifference;

            public String getTimestamp() {
                return timestamp;
            }

            public void setTimestamp(String timestamp) {
                this.timestamp = timestamp;
            }

            public String getCurrentTime() {
                return currentTime;
            }

            public void setCurrentTime(String currentTime) {
                this.currentTime = currentTime;
            }

            public String getTimeDifference() {
                return timeDifference;
            }

            public void setTimeDifference(String timeDifference) {
                this.timeDifference = timeDifference;
            }



    }
}