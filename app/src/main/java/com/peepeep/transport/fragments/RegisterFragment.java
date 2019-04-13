package com.peepeep.transport.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.peepeep.transport.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment implements View.OnClickListener {

    @BindView(R.id.user_edit)
    EditText mUser_name;
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

    private String pwd_value = "";

    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.registration_fragment, container, false);
        ButterKnife.bind(this, view);
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
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }
}
