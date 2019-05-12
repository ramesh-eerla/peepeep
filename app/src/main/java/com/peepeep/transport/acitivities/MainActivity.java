package com.peepeep.transport.acitivities;

import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.peepeep.transport.R;
import com.peepeep.transport.adapters.ViewPagerAdapter;
import com.peepeep.transport.fragments.LoginFragment;
import com.peepeep.transport.fragments.RegisterFragment;
import com.peepeep.transport.uicomponents.SuperTabActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends SuperTabActivity {


    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.tabs)
    TabLayout tabLayout;
    ArrayList<Fragment> mFragment;
    ArrayList<String> mFragment_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mFragment=new ArrayList<>();
        mFragment_name=new ArrayList<>();
        mFragment.add(new LoginFragment());
        mFragment.add(new RegisterFragment());
        mFragment_name.add("Log In");
        mFragment_name.add("Register");

        setupViewPager(viewPager,mFragment,mFragment_name);

        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean getTitleVisible() {
        return false;
    }


    @Override
    public boolean getDisplayShowTitleEnabled() {
        return false;
    }

    @Override
    public boolean getDisplayHomeAsUpEnabled() {
        return false;
    }


}
