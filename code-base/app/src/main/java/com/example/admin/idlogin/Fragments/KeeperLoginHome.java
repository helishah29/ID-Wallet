package com.example.admin.idlogin.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.admin.idlogin.R;

public class KeeperLoginHome extends Fragment
{
    RadioGroup rg;
    RadioButton receivePayment,summaryKeeper,bankPayment;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View root = inflater.inflate(R.layout.fragment_keeper_login_home, container, false);
        rg = (RadioGroup) root.findViewById(R.id.radioGroup);
        receivePayment = (RadioButton) root.findViewById(R.id.receivePayment);
        summaryKeeper = (RadioButton) root.findViewById(R.id.summaryKeeper);
        bankPayment = (RadioButton) root.findViewById(R.id.bankPayment);

        Log.e("ZZZ","ZZZ");

        Toast.makeText(getActivity(),"hello",Toast.LENGTH_SHORT).show();

        //rg.clearCheck();

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int checkedId) {
                if (checkedId == R.id.receivePayment) {
                    KeeperReceivePayment scb = new KeeperReceivePayment();
                    getFragmentManager().beginTransaction().replace(R.id.KeeperFrame, scb).commit();
                } else if (checkedId == R.id.summaryKeeper) {
                    KeeperTransactionSummary kts = new KeeperTransactionSummary();
                    getFragmentManager().beginTransaction().replace(R.id.KeeperFrame, kts).commit();
                } else if (checkedId == R.id.bankPayment) {
                    Toast.makeText(getActivity(), "TO THE PAYMENT GATEWAY", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Log.e("AA","AA");
        return root;
    }
}