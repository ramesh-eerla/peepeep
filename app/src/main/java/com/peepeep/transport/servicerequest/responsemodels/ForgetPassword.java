package com.peepeep.transport.servicerequest.responsemodels;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ForgetPassword {

    @SerializedName("pin")
    @Expose
    private String pin;
    @SerializedName("isResponseSuccess")
    @Expose
    private Boolean isResponseSuccess;
    @SerializedName("responsemessage")
    @Expose
    private String responsemessage;
    @SerializedName("responsecode")
    @Expose
    private Integer responsecode;

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public Boolean getIsResponseSuccess() {
        return isResponseSuccess;
    }

    public void setIsResponseSuccess(Boolean isResponseSuccess) {
        this.isResponseSuccess = isResponseSuccess;
    }

    public String getResponsemessage() {
        return responsemessage;
    }

    public void setResponsemessage(String responsemessage) {
        this.responsemessage = responsemessage;
    }

    public Integer getResponsecode() {
        return responsecode;
    }

    public void setResponsecode(Integer responsecode) {
        this.responsecode = responsecode;
    }

}