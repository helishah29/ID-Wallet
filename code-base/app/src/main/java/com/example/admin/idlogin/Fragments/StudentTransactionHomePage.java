package com.example.admin.idlogin.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.idlogin.R;

import static com.example.admin.idlogin.JAVAClass.SessionManager.KEY_ID;

public class StudentTransactionHomePage extends Fragment
{
    RadioGroup rg;
    RadioButton checkBalance,summary,stopService,recharge;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View root= inflater.inflate(R.layout.fragment_student_transaction_home_page, container, false);
        rg=(RadioGroup)root.findViewById(R.id.radioGroup);
        checkBalance=(RadioButton)root.findViewById(R.id.balanceCheck);
        summary=(RadioButton)root.findViewById(R.id.summary);
        stopService=(RadioButton)root.findViewById(R.id.stopService);
        recharge=(RadioButton)root.findViewById(R.id.recharge);

        rg.clearCheck();

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int checkedId)
            {
                if(checkedId==R.id.balanceCheck)
                {
                    StudentCheckBalance scb=new StudentCheckBalance();
                    getFragmentManager().beginTransaction().replace(R.id.FragmentTransaction,scb).commit();
                }
                else if(checkedId==R.id.summary)
                {
                    StudentTransactionSummary sts=new StudentTransactionSummary();
                    getFragmentManager().beginTransaction().replace(R.id.FragmentTransaction,sts).commit();
                }
                else if(checkedId==R.id.recharge)
                {
                    Toast.makeText(getActivity(),"TO THE PAYMENT GATEWAY",Toast.LENGTH_SHORT).show();
                }
                else if(checkedId==R.id.stopService)
                {
                    StudentStopService sss=new StudentStopService();
                    getFragmentManager().beginTransaction().replace(R.id.FragmentTransaction,sss).commit();
                }
            }
        });
        return root;
    }
}
