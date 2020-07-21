package com.example.admin.idlogin.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.idlogin.FragmentsList;
import com.example.admin.idlogin.R;
import com.example.admin.idlogin.StudentLoginHome;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class KeeperReceivePayment extends Fragment
{
    TextView tv,totalAmount;
    EditText itemCode,quantity,amount;
    Button scanItem,itemPlus,proceedPayment;
    int total;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View root= inflater.inflate(R.layout.fragment_keeper_receive_payment, container, false);

        tv=(TextView)root.findViewById(R.id.tv);
        totalAmount=(TextView)root.findViewById(R.id.totalAmount);
        itemCode=(EditText)root.findViewById(R.id.itemCode);
        quantity=(EditText)root.findViewById(R.id.quantity);
        amount=(EditText)root.findViewById(R.id.amount);
        scanItem=(Button)root.findViewById(R.id.scanButton);
        itemPlus=(Button)root.findViewById(R.id.moreItem);
        proceedPayment=(Button)root.findViewById(R.id.doPayment);

        //int q= Integer.parseInt(quantity.getText().toString());
        //int a=Integer.parseInt(amount.getText().toString());

//       total=q*a;

        scanItem.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                IntentIntegrator integrator = new IntentIntegrator(getActivity());
                integrator.setDesiredBarcodeFormats(IntentIntegrator.ONE_D_CODE_TYPES);
                integrator.setPrompt("Scan a barcode");
                integrator.setResultDisplayDuration(0);
                integrator.setWide();  // Wide scanning rectangle, may work better for 1D barcodes
                integrator.setCameraId(0);  // Use a specific camera of the device
                //integrator.initiateScan();
                IntentIntegrator.forSupportFragment(KeeperReceivePayment.this).initiateScan();
            }
        });



        proceedPayment.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)

            {
                Log.e("AA","ZZZ");
                Intent i=new Intent(getActivity(),FragmentsList.class);
                i.putExtra("actionid","ProceedForPayment");
                i.putExtra("totalAmount",""+total);
                startActivity(i);
            }
        });

        return root;
    }

    @Override
    public void onActivityResult(int requestCode,int resultCode, Intent intent)
    {
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null)
        {
            String scanContent = scanningResult.getContents();
            itemCode.setText(scanContent);
        }
        else
        {
            Toast toast = Toast.makeText(getActivity(),"No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}