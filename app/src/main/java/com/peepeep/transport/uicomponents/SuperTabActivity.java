package com.peepeep.transport.uicomponents;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.peepeep.transport.R;
import com.peepeep.transport.adapters.ViewPagerAdapter;
import com.peepeep.transport.fragments.LoginFragment;
import com.peepeep.transport.fragments.RegisterFragment;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class SuperTabActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.login_imagelayout)
    LinearLayout mLogin_image_layout;
    @BindView(R.id.toolbar_title)
    AppCompatImageView mToolbar_title;
    @BindView(R.id.tool_text_title)
    AppCompatTextView mTitle_text;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(getDisplayHomeAsUpEnabled());
        getSupportActionBar().setDisplayShowTitleEnabled(getDisplayShowTitleEnabled());
        getSupportActionBar().setDisplayShowHomeEnabled(false);

        if(getTitleVisible()){
            mToolbar_title.setVisibility(View.VISIBLE);
            mLogin_image_layout.setVisibility(View.GONE);
        }else{
            mToolbar_title.setVisibility(View.GONE);
            mLogin_image_layout.setVisibility(View.VISIBLE);
        }
if(getTextTitleVisible()){
    mTitle_text.setVisibility(View.VISIBLE);
    mToolbar_title.setVisibility(View.GONE);
}else{
    mToolbar_title.setVisibility(View.VISIBLE);
    mTitle_text.setVisibility(View.GONE);
}

mTitle_text.setText(getTitleText());
    }

    public void setupViewPager(ViewPager viewPager, ArrayList<Fragment> mFragment, ArrayList<String> mFragment_name) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        for(int i=0;i<mFragment.size();i++) {
        adapter.addFragment(mFragment.get(i),mFragment_name.get(i));
        }

        viewPager.setAdapter(adapter);
    }
    public abstract boolean getTitleVisible();

    public abstract boolean getTextTitleVisible();
    public abstract String getTitleText();

    public abstract boolean getDisplayShowTitleEnabled();

    public abstract boolean getDisplayHomeAsUpEnabled();






}
