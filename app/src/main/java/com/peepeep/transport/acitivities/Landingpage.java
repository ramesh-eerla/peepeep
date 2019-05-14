package com.peepeep.transport.acitivities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.peepeep.transport.R;
import com.peepeep.transport.adapters.ViewPagerAdapter;
import com.peepeep.transport.fragments.HeavyTrucksFragment;
import com.peepeep.transport.fragments.LightTrucksFragment;
import com.peepeep.transport.fragments.LoginFragment;
import com.peepeep.transport.fragments.RegisterFragment;
import com.peepeep.transport.servicerequest.responsemodels.LoginDataset;
import com.peepeep.transport.utils.CommonHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Landingpage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    /*@BindView(R.id.pick_layout)
    LinearLayout mPicklayout;
    @BindView(R.id.drop_layout)
    LinearLayout mDroplayout;
*/
    @BindView(R.id.tabs)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.nav_view)
    NavigationView navigationView;

    Context mContext;
    CircularImageView mProfile_pic;
    AppCompatTextView mProfile_email;
    AppCompatTextView mProfile_name;
    LoginDataset mLoginDataset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landingpage);
        mContext = this;
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mLoginDataset =CommonHelper.getLoginDataset(mContext);
        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.setDrawerIndicatorEnabled(false); //disable "hamburger to arrow" drawable
        toggle.setHomeAsUpIndicator(R.drawable.ic_home_burger); //set
        toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(GravityCompat.START);
            }
        });

        drawer.addDrawerListener(toggle);

        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        mProfile_pic=navigationView.getHeaderView(0).findViewById(R.id.profile_pic);
        mProfile_email=navigationView.getHeaderView(0).findViewById(R.id.profile_email);
        mProfile_name=navigationView.getHeaderView(0).findViewById(R.id.profile_name);
                setupViewPager(viewPager);
        mProfile_email.setText(mLoginDataset.getProfile().getEmail());
        mProfile_name.setText(mLoginDataset.getProfile().getFirstName() + " " + mLoginDataset.getProfile().getLastName());
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new LightTrucksFragment(), "Light Trucks");
        adapter.addFragment(new HeavyTrucksFragment(), "Heavy Trucks");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_mytrips) {

            startActivity(new Intent(mContext, MyTrips_Activity.class));
        } else if (id == R.id.nav_notificatoins) {

        } else if (id == R.id.nav_coupans) {
            startActivity(new Intent(mContext, MyCoupansAcitivity.class));
        } else if (id == R.id.nav_logout) {
finish();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
