package com.example.admin.idlogin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.admin.idlogin.Fragments.KeeperLoginHome;
import com.example.admin.idlogin.Fragments.RegisterType;
import com.example.admin.idlogin.JAVAClass.SessionManager;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends FragmentActivity implements View.OnClickListener
{
    EditText enrollment,password1;
    Button studentlogin,facultylogin,keeperlogin,signup,adminlogin;
    String enrollmentnumber,password;
    RequestQueue queue;
    SessionManager session;
    AlertDialog alert;
    private int buttonid;
    String url="https://tonetic-lights.000webhostapp.com/IDWallet/";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        session = new SessionManager(getApplicationContext());

        enrollment=(EditText)findViewById(R.id.UserName);
        password1=(EditText)findViewById(R.id.Password);
        studentlogin=(Button)findViewById(R.id.LoginStudent);
        studentlogin.setOnClickListener(this);
        facultylogin=(Button)findViewById(R.id.LoginFaculty);
        facultylogin.setOnClickListener(this);
        keeperlogin=(Button)findViewById(R.id.LoginKeeper);
        keeperlogin.setOnClickListener(this);
        adminlogin=(Button)findViewById(R.id.LoginAdmin);
        adminlogin.setOnClickListener(this);
        signup=(Button)findViewById(R.id.SignUp);
        signup.setOnClickListener(this);

        queue=Volley.newRequestQueue(getApplicationContext());

        Toast.makeText(getApplicationContext(), "User Login Status: " + session.isLoggedIn(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.LoginStudent:
                buttonid = 1;
                studentLogin();
                break;
            case R.id.LoginFaculty:
                buttonid = 2;
                facultyLogin();
                break;
            case R.id.LoginKeeper:
                buttonid = 3;
                keeperLogin();
                break;
            case R.id.LoginAdmin:
                buttonid = 4;
                 adminLogin();
                break;
            case R.id.SignUp:
                buttonid = 5;
                RegisterType rt=new RegisterType();
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,rt).commit();
                break;
            default:
                break;
        }
    }

    private void studentLogin()
    {
        enrollmentnumber=enrollment.getText().toString();
        password=password1.getText().toString();

        StringRequest request=new StringRequest(Method.POST, url + "verifyStudent.php", new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                if(response.equalsIgnoreCase("Login"))
                {
                    Toast.makeText(getApplicationContext(),"Successful Login",Toast.LENGTH_SHORT).show();
                    session.createLoginSession(enrollmentnumber);
                    Log.e("CC","CCC");
                    Intent i=new Intent(getApplicationContext(),FragmentsList.class);
                    i.putExtra("actionid","studentLoginHomePage");
                    startActivity(i);
                }
                else
                {
                    //alert.show("Login failed..", "Enrollment Number/Password is incorrect");
                    Toast.makeText(getApplicationContext(),"Enrollment Number/Password is incorrect",Toast.LENGTH_SHORT).show();
                }
            }
        },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();
                    }
                })
        {
            @Override
            protected Map<String,String> getParams() throws AuthFailureError
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("EnrollmentNumber", enrollmentnumber);
                params.put("password", password);

                return params;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(request);
    }

    private void facultyLogin()
    {

    }
    private void keeperLogin()
    {
        enrollmentnumber=enrollment.getText().toString();
        password=password1.getText().toString();

        StringRequest request=new StringRequest(Method.POST, url + "verifyKeeper.php", new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                if(response.equalsIgnoreCase("Login"))
                {
                    Toast.makeText(getApplicationContext(),"Successful Login",Toast.LENGTH_SHORT).show();
                    session.createLoginSession(enrollmentnumber);
                    Intent i=new Intent(getApplicationContext(),FragmentsList.class);
                    i.putExtra("actionid","keeperLoginHomePage");
                    startActivity(i);
                }
                else
                {
                    //alert.show("Login failed..", "Enrollment Number/Password is incorrect");
                    Toast.makeText(getApplicationContext(),"Enrollment Number/Password is incorrect",Toast.LENGTH_SHORT).show();
                }
            }
        },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();
                    }
                })
        {
            @Override
            protected Map<String,String> getParams() throws AuthFailureError
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("EnrollmentNumber", enrollmentnumber);
                params.put("password", password);

                return params;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(request);
    }
    private void adminLogin()
    {

    }
 }