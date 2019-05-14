package com.peepeep.transport.acitivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.peepeep.transport.R;
import com.peepeep.transport.fragments.LoginFragment;
import com.peepeep.transport.fragments.RegisterFragment;
import com.peepeep.transport.fragments.TrackFragment;
import com.peepeep.transport.uicomponents.SuperTabActivity;
import com.peepeep.transport.utils.Constants;

import java.util.ArrayList;

import butterknife.BindView;

public class MyTrips_Activity extends SuperTabActivity implements TrackFragment.OnFragmentInteractionListener {
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
        mFragment.add(TrackFragment.newInstance("Current", Constants.CURRENT));
        mFragment.add(TrackFragment.newInstance("Past",Constants.PAST));
        mFragment_name.add("Curent");
        mFragment_name.add("Past");

        setupViewPager(viewPager,mFragment,mFragment_name);

        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
    @Override
    public boolean getTitleVisible() {
        return true;
    }

    @Override
    public boolean getTextTitleVisible() {
        return true;
    }

    @Override
    public String getTitleText() {
        return "My Trips";
    }


    @Override
    public boolean getDisplayShowTitleEnabled() {
        return false;
    }

    @Override
    public boolean getDisplayHomeAsUpEnabled() {
        return true;
    }
}
