package com.peepeep.transport.servicerequest.responsemodels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginDataset {

    @SerializedName("responseMsg")
    @Expose
    private String responseMsg;
    @SerializedName("isResponseSuccess")
    @Expose
    private Boolean isResponseSuccess;
    @SerializedName("responseCode")
    @Expose
    private Integer responseCode;
    @SerializedName("displayPic")
    @Expose
    private Object displayPic;

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

    public Object getDisplayPic() {
        return displayPic;
    }

    public void setDisplayPic(Object displayPic) {
        this.displayPic = displayPic;
    }

}