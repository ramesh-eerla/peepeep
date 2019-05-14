package com.peepeep.transport.servicerequest.responsemodels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


import java.util.List;


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
    private String displayPic;
    @SerializedName("Profile")
    @Expose
    private Profile profile;
    @SerializedName("Goods")
    @Expose
    private List<Good> goods = null;

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

    public String getDisplayPic() {
        return displayPic;
    }

    public void setDisplayPic(String displayPic) {
        this.displayPic = displayPic;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public List<Good> getGoods() {
        return goods;
    }

    public void setGoods(List<Good> goods) {
        this.goods = goods;
    }


 public class Good {

    @SerializedName("GoodsId")
    @Expose
    private Integer goodsId;
    @SerializedName("GoodsName")
    @Expose
    private String goodsName;

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

}

 public class Profile {

    @SerializedName("FirstName")
    @Expose
    private String firstName;
    @SerializedName("LastName")
    @Expose
    private String lastName;
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("MobileNumber")
    @Expose
    private Integer mobileNumber;
    @SerializedName("BusinessName")
    @Expose
    private String businessName;
    @SerializedName("GstNumber")
    @Expose
    private String gstNumber;
    @SerializedName("ProfilePic")
    @Expose
    private String profilePic;
    @SerializedName("PeePeePUserId")
    @Expose
    private String peePeePUserId;
    @SerializedName("UserId")
    @Expose
    private Integer userId;
    @SerializedName("CreatedUserId")
    @Expose
    private Integer createdUserId;
    @SerializedName("CreatedDate")
    @Expose
    private String createdDate;
    @SerializedName("ModifiedDate")
    @Expose
    private String modifiedDate;
    @SerializedName("AccountTypeId")
    @Expose
    private Integer accountTypeId;
    @SerializedName("LoginTypeId")
    @Expose
    private Integer loginTypeId;
    @SerializedName("ProfilePicID")
    @Expose
    private Integer profilePicID;
    @SerializedName("IsBusinessUser")
    @Expose
    private Boolean isBusinessUser;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(Integer mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getGstNumber() {
        return gstNumber;
    }

    public void setGstNumber(String gstNumber) {
        this.gstNumber = gstNumber;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getPeePeePUserId() {
        return peePeePUserId;
    }

    public void setPeePeePUserId(String peePeePUserId) {
        this.peePeePUserId = peePeePUserId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCreatedUserId() {
        return createdUserId;
    }

    public void setCreatedUserId(Integer createdUserId) {
        this.createdUserId = createdUserId;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Integer getAccountTypeId() {
        return accountTypeId;
    }

    public void setAccountTypeId(Integer accountTypeId) {
        this.accountTypeId = accountTypeId;
    }

    public Integer getLoginTypeId() {
        return loginTypeId;
    }

    public void setLoginTypeId(Integer loginTypeId) {
        this.loginTypeId = loginTypeId;
    }

    public Integer getProfilePicID() {
        return profilePicID;
    }

    public void setProfilePicID(Integer profilePicID) {
        this.profilePicID = profilePicID;
    }

    public Boolean getIsBusinessUser() {
        return isBusinessUser;
    }

    public void setIsBusinessUser(Boolean isBusinessUser) {
        this.isBusinessUser = isBusinessUser;
    }

}
}