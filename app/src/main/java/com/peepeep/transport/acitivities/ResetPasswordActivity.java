package com.peepeep.transport.acitivities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.peepeep.transport.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ResetPasswordActivity extends AppCompatActivity {

    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.reg_email) EditText mResetpwd;
    @BindView(R.id.reset) AppCompatButton mReset;
    @BindView(R.id.rest_pwd_layout) LinearLayout mReset_pwd_layout;
    @BindView(R.id.digi_layout) LinearLayout mDigi_layout;
    int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Reset Password");
        mReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mResetpwd.setVisibility(View.GONE);
                if(i==0){
                mReset_pwd_layout.setVisibility(View.VISIBLE);
                    mDigi_layout.setVisibility(View.GONE);

                i=1;
                }else{
                    mDigi_layout.setVisibility(View.VISIBLE);
                    mReset_pwd_layout.setVisibility(View.GONE);
                    i=0;
                }

            }
        });


    }
    public boolean onOptionsItemSelected(MenuItem item){
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

}
