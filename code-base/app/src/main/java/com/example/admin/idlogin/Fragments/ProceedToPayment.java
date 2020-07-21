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

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.admin.idlogin.FragmentsList;
import com.example.admin.idlogin.MainActivity;
import com.example.admin.idlogin.R;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.net.Authenticator;
import java.util.HashMap;
import java.util.Map;

public class ProceedToPayment extends Fragment
{
    TextView tv;
    EditText totalAmount,CustomerID,CustomerPin;
    Button pay,scanCustomerID;
    String StudentID,StudentPIN,payableAmount;
    RequestQueue queue1;
    String url="https://tonetic-lights.000webhostapp.com/IDWallet/";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View root= inflater.inflate(R.layout.fragment_proceed_to_payment, container, false);
        tv=(TextView)root.findViewById(R.id.tv);
        totalAmount=(EditText)root.findViewById(R.id.toalPayableamount);
        CustomerID=(EditText)root.findViewById(R.id.scanCustomersID);
        CustomerPin=(EditText)root.findViewById(R.id.CustomerPin);
        pay=(Button)root.findViewById(R.id.Pay);
        scanCustomerID=(Button)root.findViewById(R.id.buttonScanCustomerID);

        queue1= Volley.newRequestQueue(getActivity());

        scanCustomerID.setOnClickListener(new View.OnClickListener()
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
                IntentIntegrator.forSupportFragment(ProceedToPayment.this).initiateScan();
            }
        });

        payableAmount=totalAmount.getText().toString();
        StudentID=CustomerID.getText().toString();
        StudentPIN=CustomerPin.getText().toString();


        pay.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                payableAmount=totalAmount.getText().toString();
                StudentID=CustomerID.getText().toString();
                StudentPIN=CustomerPin.getText().toString();
                Toast.makeText(getActivity(),""+StudentID+"  "+StudentPIN+"  "+payableAmount,Toast.LENGTH_SHORT).show();
                StringRequest request=new StringRequest(Request.Method.POST, url + "itemPurchase.php", new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        Log.e("AAA","RRR");
                        Toast.makeText(getActivity(),""+response,Toast.LENGTH_SHORT).show();
                    }
                },
                        new Response.ErrorListener()
                        {
                            @Override
                            public void onErrorResponse(VolleyError error)
                            {
                                Toast.makeText(getActivity(),error.toString(),Toast.LENGTH_LONG).show();
                            }
                        })
                {
                    @Override
                    protected Map<String,String> getParams() throws AuthFailureError
                    {
                        Map<String, String>  params = new HashMap<String, String>();
                        params.put("ID", StudentID);
                        params.put("amount", payableAmount);
                        params.put("PIN", StudentPIN);

                        Log.e("BB","BBB");
                        return params;

                    }
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError
                    {
                        Map<String,String> params = new HashMap<String, String>();
                        params.put("Content-Type","application/x-www-form-urlencoded");
                        return params;
                    }
                };
                request.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                queue1.add(request);
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
            CustomerID.setText(scanContent);

        }else{
            Toast toast = Toast.makeText(getActivity(),"No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
