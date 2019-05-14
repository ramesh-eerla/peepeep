package com.peepeep.transport.acitivities;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;

import com.peepeep.transport.R;
import com.peepeep.transport.interfaces.ResponceCallback;
import com.peepeep.transport.servicerequest.PP_RetrofitSevicecall;
import com.peepeep.transport.utils.CommonHelper;
import com.peepeep.transport.utils.Constants;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ResetPasswordActivity extends AppCompatActivity implements ResponceCallback {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.reg_email)
    EditText mResetpwd;
    @BindView(R.id.reset)
    AppCompatButton mReset;
    @BindView(R.id.rest_pwd_layout)
    LinearLayout mReset_pwd_layout;
    @BindView(R.id.digi_layout)
    LinearLayout mDigi_layout;
    @BindView(R.id.tool_text_title)
    AppCompatTextView mTitle_text;
    int i = 0;
    private PP_RetrofitSevicecall mRt_retrofitSevicecall;
    String email;
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        mContext=this;
        mRt_retrofitSevicecall = new PP_RetrofitSevicecall(this);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mTitle_text.setText("Reset Password");
        mReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email=mResetpwd.getText().toString();
                if(!email.isEmpty())
                mRt_retrofitSevicecall.forget_Password(Constants.PP_FORGET_PASSWORD,email);
                else
                    CommonHelper.setErrorTextBackground(mResetpwd,mContext,getString(R.string.error_invalid_email));


            }
        });


    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public void callback(JSONObject responce) {

    }

    @Override
    public void callback(Object responce, int requesttype) {
        mResetpwd.setVisibility(View.GONE);

        if (i == 0) {
            mReset_pwd_layout.setVisibility(View.VISIBLE);
            mDigi_layout.setVisibility(View.GONE);

            i = 1;
        } else {
            mDigi_layout.setVisibility(View.VISIBLE);
            mReset_pwd_layout.setVisibility(View.GONE);
            i = 0;
        }

    }

    @Override
    public void errorcallback(String errortitle, String errormessage) {

    }
}
