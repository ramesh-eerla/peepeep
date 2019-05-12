package com.peepeep.transport.fragments;


import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.fragment.app.Fragment;

import com.peepeep.transport.R;
import com.peepeep.transport.controller.AppController;
import com.peepeep.transport.interfaces.PPRequestInterface;
import com.peepeep.transport.interfaces.ResponceCallback;
import com.peepeep.transport.servicerequest.PP_RetrofitSevicecall;
import com.peepeep.transport.servicerequest.responsemodels.LoginDataset;
import com.peepeep.transport.servicerequest.responsemodels.RegistrationDataset;
import com.peepeep.transport.servicerequest.retrofitrequestparams.Retrofit_RequestParams;
import com.peepeep.transport.utils.Constants;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment implements View.OnClickListener, ResponceCallback {

    @BindView(R.id.user_fname)
    EditText mUser_fname;
    @BindView(R.id.user_lname)
    EditText mUser_lname;
    @BindView(R.id.phone_edit)
    EditText mPhonenumber;
    @BindView(R.id.email_edit)
    EditText mEmail;
    @BindView(R.id.pwd_edit)
    EditText mPassword;
    @BindView(R.id.cnfpwd_edit)
    EditText mConfirmpassword;
    @BindView(R.id.termscheckbox)
    CheckBox mAcceptcheckbox;
    @BindView(R.id.checkboxtext)
    TextView mTermsText;
    @BindView(R.id.register_button)
    Button mRegisterButton;
    @BindView(R.id.orgname_edit)
    EditText mBusiness_name;
    @BindView(R.id.gst_edit)
    EditText mGst_number;
    @BindView(R.id.business_toggle)
    ToggleButton mBusiness_toggle;

    private String firstName, lastName, email,password,businessName,gstNumber,userId,datecreated,modifieddate,createduserid,
            modifieduserid,usertypeid,peepeepUserId,socialNetworkIdentifier,accountType,displayPicId,loginTypeId,displayPic;
    private long mobileNumber;
    boolean isBusinessUser;
    private PP_RetrofitSevicecall mRt_retrofitSevicecall;;
    private Context mContext;

    private String pwd_value = "";

    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.registration_fragment, container, false);
        ButterKnife.bind(this,view);
        mContext=getActivity();
        mRt_retrofitSevicecall = new PP_RetrofitSevicecall(getActivity(),this);
        mConfirmpassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                pwd_value = mPassword.getText().toString();
                if (!b && !TextUtils.isEmpty(pwd_value)) {
                    String cnfpwd = mConfirmpassword.getText().toString();
                    if (!pwd_value.equalsIgnoreCase(cnfpwd)) {
                        mConfirmpassword.setError("Password Missmatch");
                    } else {
                        mConfirmpassword.setError(null);
                    }

                }
            }
        });
        mRegisterButton.setOnClickListener(this);
        mBusiness_toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    mGst_number.setVisibility(View.VISIBLE);
                    mBusiness_name.setVisibility(view.VISIBLE);
                }else{
                    mGst_number.setVisibility(View.GONE);
                    mBusiness_name.setVisibility(view.GONE);
                }
            }
        });
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_button :
                PPRequestInterface mApiService = AppController.getInterfaceService(mContext);
                firstName=mUser_fname.getText().toString();
                lastName=mUser_lname.getText().toString();
                mobileNumber=Long.parseLong(mPhonenumber.getText().toString());
                email=mEmail.getText().toString();
                password=mPassword.getText().toString();
                businessName=mBusiness_name.getText().toString();
                gstNumber=mGst_number.getText().toString();
                isBusinessUser=mBusiness_toggle.isChecked();
                Retrofit_RequestParams.RegistrationParams params=new Retrofit_RequestParams().new RegistrationParams().registrationData(firstName, lastName, email, mobileNumber,password,businessName,gstNumber,userId,datecreated,modifieddate,createduserid,
                        modifieduserid,usertypeid,peepeepUserId,socialNetworkIdentifier,accountType,displayPicId,loginTypeId,displayPic,isBusinessUser);
                Call<RegistrationDataset> mService= mApiService.peeppRegistration(mContext.getString(R.string.regist_url),  params);
                mRt_retrofitSevicecall.Registration_post(mService,params);
               // mService= mApiService.submitRoom(methodname,(RequestParams.Room_POST)fileObject);
             //  if( validateallfields())
            break;
        }
    }

    private boolean validateallfields() {
        boolean status=false;

    return status;
    }

    @Override
    public void callback(JSONObject responce) {

    }

    @Override
    public void callback(Object responce, int requesttype) {

    }

    @Override
    public void errorcallback(String errortitle, String errormessage) {

    }
}
