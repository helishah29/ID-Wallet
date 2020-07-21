package com.example.admin.idlogin.Fragments;

import android.content.Context;
import android.net.Uri;
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
import com.example.admin.idlogin.R;

import java.util.HashMap;
import java.util.Map;

public class FacultyRegister extends Fragment
{
    TextView tv;
    EditText idno,name,department,password,pin;
    Button register,frscan;
    String sfidno,sfname,sfdepartment,sfpassword,sfpin;
    RequestQueue queue;
    String url="https://tonetic-lights.000webhostapp.com/IDWallet/";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View root=inflater.inflate(R.layout.fragment_faculty_register, container, false);
        tv=(TextView)root.findViewById(R.id.tvhead);
        idno=(EditText)root.findViewById(R.id.idnumber);
        name=(EditText)root.findViewById(R.id.name);
        department=(EditText)root.findViewById(R.id.department);
        password=(EditText)root.findViewById(R.id.password);
        pin=(EditText)root.findViewById(R.id.pin);
        register=(Button)root.findViewById(R.id.register);
        frscan=(Button)root.findViewById(R.id.frscan);
        queue= Volley.newRequestQueue(getActivity());

        register.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                facultyRegisterCall();
            }
        });
        return root;
    }

    private void facultyRegisterCall()
    {
        sfidno=idno.getText().toString();
        sfname=name.getText().toString();
        sfdepartment=department.getText().toString();
        sfpassword=password.getText().toString();
        sfpin=pin.getText().toString();
        //Intent in= Intent.getIntent();
        //getActivity();

        StringRequest sr=new StringRequest(Request.Method.POST, url + "registerFaculty.php", new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                try
                {
                    Toast.makeText(getActivity(),response,Toast.LENGTH_SHORT).show();
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
                params.put("idno",sfidno);
                params.put("name",sfname);
                params.put("department",sfdepartment);
                params.put("password",sfpassword);
                params.put("pin",sfpin);
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
        queue.add(sr);
    }
}
