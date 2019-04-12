package com.peepeep.transport.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.peepeep.transport.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {

   @BindView(R.id.user_edit) EditText mUser_name;
    @BindView(R.id.phone_edit) EditText mPhonenumber;
    @BindView(R.id.email_edit) EditText mEmail;
    @BindView(R.id.pwd_edit) EditText mPassword;
    @BindView(R.id.cnfpwd_edit) EditText mConfirmpassword;

    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.registration_fragment, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

}
