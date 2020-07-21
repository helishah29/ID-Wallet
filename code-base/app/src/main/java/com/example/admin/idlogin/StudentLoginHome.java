package com.example.admin.idlogin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import com.example.admin.idlogin.Fragments.StudentAttendanceHomePage;
import com.example.admin.idlogin.Fragments.StudentTransactionHomePage;
import com.example.admin.idlogin.R;

public class StudentLoginHome extends AppCompatActivity
{
    TabLayout tabs;
    Toolbar toolbar;
    private ViewPager pager;
    String tab[] = {"Transaction", "Attendance"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login_home);

        Intent i=getIntent();
        Log.e("AA","AAA");
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tabs=(TabLayout)findViewById(R.id.tabs);
        //tabs.addTab(tabs.newTab().setText("Tab 1"));

        tabs.addTab(tabs.newTab().setText("Transaction"));
        tabs.addTab(tabs.newTab().setText("Attendance"));
        tabs.setTabGravity(TabLayout.GRAVITY_FILL);

        pager = (ViewPager) findViewById(R.id.container);

        FragmentPagerAdapter fpa = new FragmentPagerAdapter(getSupportFragmentManager())  //this method is only supported by AppCompatActivity and not by Activity
        {
            @Override
            public int getCount()
            {
                return tab.length;
            }

            @Override
            public Fragment getItem(int position)
            {
                switch (position)
                {
                    case 0:
                        return new StudentTransactionHomePage();
                    case 1:
                        return new StudentAttendanceHomePage();
                }
                return null;
            }
        };
        pager.setAdapter(fpa);
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));

        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}