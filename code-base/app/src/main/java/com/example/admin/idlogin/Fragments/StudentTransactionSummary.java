package com.example.admin.idlogin.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.idlogin.R;

public class StudentTransactionSummary extends Fragment
{
    TextView tv;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View root= inflater.inflate(R.layout.fragment_student_transaction_summary, container, false);
        tv=(TextView)root.findViewById(R.id.textView);
        return root;
    }

}
