package com.example.admin.idlogin.JAVAClass;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentTransaction;

import com.example.admin.idlogin.R;

import static android.R.attr.fragment;

public class TabListener implements ActionBar.TabListener
{
    Fragment fragment;

    public TabListener(Fragment fragment)
    {
        // TODO Auto-generated constructor stub
        this.fragment = fragment;
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft)
    {
        ft.replace(R.id.fragment_container, fragment);
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft)
    {
        ft.remove(fragment);
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction)
    {

    }
}
