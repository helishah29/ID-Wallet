package com.example.admin.idlogin.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.idlogin.R;

public class StudentCheckBalance extends Fragment
{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View root= inflater.inflate(R.layout.fragment_student_check_balance, container, false);
        
        return  root;
    }
}
