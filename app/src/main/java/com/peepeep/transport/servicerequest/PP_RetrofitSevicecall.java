package com.peepeep.transport.servicerequest;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.internal.LinkedTreeMap;
import com.peepeep.transport.R;
import com.peepeep.transport.controller.AppController;
import com.peepeep.transport.interfaces.PPRequestInterface;
import com.peepeep.transport.interfaces.ResponceCallback;
import com.peepeep.transport.servicerequest.responsemodels.LoginDataset;
import com.peepeep.transport.servicerequest.responsemodels.RegistrationDataset;
import com.peepeep.transport.servicerequest.responsemodels.RetrofitErrorResponse;
import com.peepeep.transport.servicerequest.retrofitrequestparams.Retrofit_RequestParams;
import com.peepeep.transport.utils.CommonHelper;
import com.peepeep.transport.utils.Constants;


import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Query;

/**
 * Created by ramesh.eerla on 4/4/2019.
 */

public class PP_RetrofitSevicecall {
    public Context mContext;
    /*

    public Call<Object> mService;
    public Call<LoginDataset> mService_login;

     public Call<RetrofitResponse> mService_submit;*/
   // ProgressDialog mProgressDialog;
    CommonHelper mCommonHelper;
    ResponceCallback mResponceCallback;

    public PP_RetrofitSevicecall(Context mContext) {
        this.mContext = mContext;
        this.mResponceCallback = (ResponceCallback) mContext;
        this.mCommonHelper = new CommonHelper();
    }

    public PP_RetrofitSevicecall(Context mContext, Object mObject) {
        this.mContext = mContext;
        this.mResponceCallback = (ResponceCallback) mObject;
        this.mCommonHelper = new CommonHelper();
    }

    public void loginpost(final int requestType,String muserName,String mpassword) {

        /*if (mCommonHelper!=null&& mContext != null)
            mProgressDialog = mCommonHelper.showDialog(mContext);
*/
        PPRequestInterface mApiService = AppController.getInterfaceService(mContext);
        Call<LoginDataset>  mService= mApiService.user_login(mContext.getString(R.string.login_url),  muserName,mpassword);

        mService.enqueue(new Callback<LoginDataset>() {

            @Override
            public void onResponse(Call<LoginDataset> call, Response<LoginDataset> response) {
               // mProgressDialog.dismiss();
                if (response.isSuccessful()) {
                    SharedPreferences mPrefs = mContext.getSharedPreferences("Login_repsonse",mContext.MODE_PRIVATE);
                     SharedPreferences.Editor prefsEditor = mPrefs.edit();
                    Gson gson = new Gson();
                    String json = gson.toJson(response.body());
                    prefsEditor.putString("MyObject", json);
                    prefsEditor.commit();
                    mResponceCallback.callback(response.body(), requestType);

                } else{
                    JsonParser parser = new JsonParser();
                    JsonElement mJson = null;
                    try {
                        mJson = parser.parse(response.errorBody().string());
                        Gson gson = new Gson();
                        RetrofitErrorResponse errorResponse = gson.fromJson(mJson, RetrofitErrorResponse.class);
                        String error_message = errorResponse.getMessage();
                        CommonHelper.showErrorAlertDiaolog(mContext, "Login Failure", error_message);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }


            }

            @Override
            public void onFailure(Call<LoginDataset> call, Throwable t) {
                //mProgressDialog.dismiss();
                call.cancel();
                if(t instanceof SocketTimeoutException)
                    CommonHelper.showErrorAlertDiaolog(mContext, "Connection Timeout", Constants.PP_CONECTIONTIMEOUT_Error_Message);
                else if(t instanceof UnknownHostException)
                    CommonHelper.showErrorAlertDiaolog(mContext, "UnknownHost", t.getMessage());
                else
                    CommonHelper.showErrorAlertDiaolog(mContext, "No Network", Constants.PP_NONETWORK_Error_Message);
            }
        });
    }

    public void Registration_post(Call<RegistrationDataset> mService, Object mUploadResumeFile) {



        mService.enqueue(new Callback<RegistrationDataset>() {
            @Override
            public void onResponse(Call<RegistrationDataset> call, Response<RegistrationDataset> response) {
               /* if(requestType!=Constants.RT_SEARCH_LOCATION)
                    mProgressDialog.dismiss();*/
                if (response.isSuccessful()) {

                    try {
                        List<RegistrationDataset> data = (List<RegistrationDataset>) response.body();
                        mResponceCallback.callback(data,0);
                    } catch (Exception e) {
                       // LinkedTreeMap data=(LinkedTreeMap) response.body();
                         //String valu=data.get("message").toString();

                      //  CommonHelper.showErrorAlertDiaolog(mContext, "Message", valu);

                    }
                }




            }

            @Override
            public void onFailure(Call<RegistrationDataset> call, Throwable t) {
              /*  if(requestType!=Constants.PP_SEARCH_LOCATION) {
                    mProgressDialog.dismiss();*/
                    call.cancel();
                    if (t instanceof SocketTimeoutException)
                        CommonHelper.showErrorAlertDiaolog(mContext, "Connection Timeout", Constants.PP_CONECTIONTIMEOUT_Error_Message);
                    else if (t instanceof UnknownHostException)
                        CommonHelper.showErrorAlertDiaolog(mContext, "UnknownHost", t.getMessage());
                    else
                        CommonHelper.showErrorAlertDiaolog(mContext, "No Network", Constants.PP_NONETWORK_Error_Message);
                //}
            }
        });
    }

    /*public void submitPost(final int requestType,String methodname, Object mUploadResumeFile) {

        mService_submit=getRequestTypemethod(requestType,methodname,mUploadResumeFile);
        if (mCommonHelper!=null&& mContext != null&&requestType!=Constants.RT_SEARCH_LOCATION)
            mProgressDialog = mCommonHelper.showDialog(mContext);

        mService_submit.enqueue(new Callback<RetrofitResponse>() {
            @Override
            public void onResponse(Call<RetrofitResponse> call, Response<RetrofitResponse> response) {
                if(requestType!=Constants.RT_SEARCH_LOCATION)
                    mProgressDialog.dismiss();
                if (response.isSuccessful()) {
                mResponceCallback.callback(response.body(), requestType);
               }
            }

            @Override
            public void onFailure(Call<RetrofitResponse> call, Throwable t) {
                if(requestType!=Constants.RT_SEARCH_LOCATION) {
                    mProgressDialog.dismiss();
                    call.cancel();
                    if (t instanceof SocketTimeoutException)
                        CommonHelper.showErrorAlertDiaolog(mContext, "Connection Timeout", Constants.ST_CONECTIONTIMEOUT_Error_Message);
                    else if (t instanceof UnknownHostException)
                        CommonHelper.showErrorAlertDiaolog(mContext, "UnknownHost", t.getMessage());
                    else
                        CommonHelper.showErrorAlertDiaolog(mContext, "No Network", Constants.ST_NONETWORK_Error_Message);
                }
            }
        });
    }

    public Call<RetrofitResponse> getRequestTypemethod(int requestType, String methodname, Object fileObject) {

        RTRequestInterface mApiService = AppController.getInterfaceService(mContext);

        switch (requestType){
            case 0:
                 mService_submit= mApiService.submitRoom(methodname,(RequestParams.Room_POST)fileObject);
                return mService_submit;
            case 1:
                mService_submit= mApiService.submitHome(methodname,(RequestParams.Home_POST)fileObject);
                return mService_submit;

            case 2:
                mService_submit= mApiService.submitRommate(methodname,(RequestParams.Roommate_POST)fileObject);
                return mService_submit;
            case 3:
                mService_submit= mApiService.submitPayinggust(methodname,(RequestParams.PayingGust_POST)fileObject);
                return mService_submit;
            case 4:
                mService_submit= mApiService.submitHostel(methodname,(RequestParams.Hostel_POST)fileObject);
                return mService_submit;

            case 5:
                mService_submit= mApiService.updateuserdata(methodname,(RequestParams.PersonalProfile)fileObject);
                return mService_submit;
            case 6:
                mService_submit= mApiService.updatePassword(methodname,(RequestParams.ChangePassword)fileObject);
                return mService_submit;
            case 7:
                mService_submit= mApiService.updatePassword(methodname,(RequestParams.ChangePassword)fileObject);
                return mService_submit;
            case 8:
                mService_submit= mApiService.sendmail(methodname,(RequestParams.SendMail)fileObject);
                return mService_submit;
            case 9 :
            case 10:
                mService_submit= mApiService.bookmars(methodname,(RequestParams.BookMarkset)fileObject);
                return mService_submit;
            *//*case  Constants.ST_ATTACHMENTDELETE:
                Call<DeleteResumeDataset>  mService3= mApiService.DeleteAttachment(mContext.getString(R.string.webapi_urn_5),"DeleteAdditionalDocFile",(RequestParams.RemoveAdditionalDocuments)fileObject);
                return mService3;*//*
        }
        return null;
    }

    public void getAera(final int requestType, String methodname, Map map) {
        RTRequestInterface mApiService = AppController.getInterfaceService(mContext);
        Call<List<Countriesmodel>>  mService_area;
        if(map!=null)
            mService_area = mApiService.getareadata(methodname,map);
        else
            mService_area= mApiService.getarea(methodname);
        *//*if (mCommonHelper!=null&& mContext != null&&requestType!=Constants.RT_SEARCH_LOCATION)
            mProgressDialog = mCommonHelper.showDialog(mContext);*//*

        mService_area.enqueue(new Callback<List<Countriesmodel>>() {
            @Override
            public void onResponse(Call<List<Countriesmodel>> call, Response<List<Countriesmodel>> response) {
               *//* if(requestType!=Constants.RT_SEARCH_LOCATION)
                    mProgressDialog.dismiss();*//*
                if (response.isSuccessful())
                    mResponceCallback.callback(response.body(), requestType);


            }

            @Override
            public void onFailure(Call<List<Countriesmodel>> call, Throwable t) {
                  *//* mProgressDialog.dismiss();*//*
                    call.cancel();
                    if (t instanceof SocketTimeoutException)
                        CommonHelper.showErrorAlertDiaolog(mContext, "Connection Timeout", Constants.ST_CONECTIONTIMEOUT_Error_Message);
                    else if (t instanceof UnknownHostException)
                        CommonHelper.showErrorAlertDiaolog(mContext, "UnknownHost", t.getMessage());
                    else
                        CommonHelper.showErrorAlertDiaolog(mContext, "No Network", Constants.ST_NONETWORK_Error_Message);

            }
        });
    }

    private Call getRequestType(int requestType, Object fileObject) {

        RTRequestInterface mApiService = AppController.getInterfaceService(mContext);

        switch (requestType){
            case Constants.RT_SEARCH_LOCATION:
                Call<List<Dataset>>  mService= mApiService.searchtypes(mContext.getString(R.string.autosearch),(RequestParams.SearchValues)fileObject);
                return mService;
            case Constants.RT_SEARCH:
                Call<Object>  mServie= mApiService.searchrooms(mContext.getString(R.string.searchrooms),(RequestParams.SearchTypes)fileObject);
                return mServie;
            case Constants.RT_SEARCH_HOSTEL:
                Call<Object>  mServic= mApiService.searchrooms(mContext.getString(R.string.searchhostel),(RequestParams.SearchTypes)fileObject);
                return mServic;
            case Constants.RT_LOGIN:
                Call<LoginDataset>  mService2= mApiService.login(mContext.getString(R.string.user_login),(RequestParams.LoginDetails)fileObject);
                return mService2;
            case Constants.MY_PROPERTIES:
                Call<Object>  mServe= mApiService.getMy_Properties(mContext.getString(R.string.getpropertydetails),(RequestParams.MyProperties)fileObject);
                return mServe;
            case Constants.BOOKMARKS:
                Call<Object>  mSere= mApiService.getMy_Properties("getbookmarkslist",(RequestParams.MyProperties)fileObject);
                return mSere;

            *//*case  Constants.ST_ATTACHMENTDELETE:
                Call<DeleteResumeDataset>  mService3= mApiService.DeleteAttachment(mContext.getString(R.string.webapi_urn_5),"DeleteAdditionalDocFile",(RequestParams.RemoveAdditionalDocuments)fileObject);
                return mService3;*//*
        }
       return null;
    }*/
}

