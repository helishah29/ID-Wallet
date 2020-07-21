package com.example.admin.idlogin.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.admin.idlogin.MainActivity;
import com.example.admin.idlogin.R;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.HashMap;
import java.util.Map;

public class StudentRegister extends Fragment
{
    TextView srtvheader;
    EditText srenrollment,sridno,srname,srbalance,srpassword,srpin;
    String srsenrollment,srsidno,srsname,srsbalance,srspassword,srspin;
    Button srregister,srscan;
    RequestQueue srqueue;
    private View root;
    String url="https://tonetic-lights.000webhostapp.com/IDWallet/";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                            Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        root=inflater.inflate(R.layout.fragment_student_register, container, false);

        srtvheader=(TextView)root.findViewById(R.id.srtvheader);
        srenrollment= (EditText)root.findViewById(R.id.srenrollmentnumber);
        sridno=(EditText)root.findViewById(R.id.srID);
        srname=(EditText)root.findViewById(R.id.srname);
        srbalance=(EditText)root.findViewById(R.id.srbalance);
        srpassword=(EditText)root.findViewById(R.id.srPassword);
        srpin=(EditText)root.findViewById(R.id.srpin);
        srregister=(Button)root.findViewById(R.id.srregister);
        srscan=(Button)root.findViewById(R.id.srscan);
        srqueue= Volley.newRequestQueue(getActivity());
        sridno.setText("HELLO");

        srregister.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                studentRegisterCall();
            }
        });
        srscan.setOnClickListener(new View.OnClickListener()
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
                IntentIntegrator.forSupportFragment(StudentRegister.this).initiateScan();
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
            sridno.setText(scanContent);

        }else{
            Toast toast = Toast.makeText(getActivity(),"No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    private void studentRegisterCall()
    {
        srsenrollment=srenrollment.getText().toString();
        srsidno=sridno.getText().toString();
        srsname=srname.getText().toString();
        srsbalance=srbalance.getText().toString();
        srspassword=srpassword.getText().toString();
        srspin=srpin.getText().toString();

        StringRequest sr=new StringRequest(Request.Method.POST, url + "registerStudent.php", new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                try
                {
                    Toast.makeText(getActivity(),response,Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(getActivity(),MainActivity.class);
                    startActivity(i);
                }
                catch (Exception e)
                {
                    Toast.makeText(getActivity(),"Error", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener()
        {

            @Override
            public void onErrorResponse(VolleyError error)
            {
                Toast.makeText(getActivity(),"ERRORRESPONSE",Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            protected Map<String,String> getParams() throws AuthFailureError
            {
                Map<String,String> params=new HashMap<String, String>();
                params.put("enrollment",srsenrollment);
                params.put("idno",srsidno);
                params.put("name",srsname);
                params.put("balance",srsbalance);
                params.put("password",srspassword);
                params.put("pin",srspin);
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
        srqueue.add(sr);
    }
}