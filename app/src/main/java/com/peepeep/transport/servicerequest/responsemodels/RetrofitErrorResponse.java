package com.peepeep.transport.servicerequest.responsemodels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RetrofitErrorResponse {
    @Expose
    @SerializedName("message")
    private String message;

    @SerializedName("status")
    private String status;

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
