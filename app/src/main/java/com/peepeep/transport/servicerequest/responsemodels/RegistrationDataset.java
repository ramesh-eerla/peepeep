package com.peepeep.transport.servicerequest.responsemodels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegistrationDataset {

    @SerializedName("responseMsg")
    @Expose
    private String responseMsg;
    @SerializedName("isResponseSuccess")
    @Expose
    private Boolean isResponseSuccess;
    @SerializedName("responseCode")
    @Expose
    private Integer responseCode;
    @SerializedName("registrationtype")
    @Expose
    private Object registrationtype;
    @SerializedName("registration_id")
    @Expose
    private Integer registrationId;
    @SerializedName("mobile_verification")
    @Expose
    private Object mobileVerification;

    public String getResponseMsg() {
        return responseMsg;
    }

    public void setResponseMsg(String responseMsg) {
        this.responseMsg = responseMsg;
    }

    public Boolean getIsResponseSuccess() {
        return isResponseSuccess;
    }

    public void setIsResponseSuccess(Boolean isResponseSuccess) {
        this.isResponseSuccess = isResponseSuccess;
    }

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    public Object getRegistrationtype() {
        return registrationtype;
    }

    public void setRegistrationtype(Object registrationtype) {
        this.registrationtype = registrationtype;
    }

    public Integer getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(Integer registrationId) {
        this.registrationId = registrationId;
    }

    public Object getMobileVerification() {
        return mobileVerification;
    }

    public void setMobileVerification(Object mobileVerification) {
        this.mobileVerification = mobileVerification;
    }

}