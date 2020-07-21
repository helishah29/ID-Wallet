package com.example.admin.idlogin;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.admin.idlogin.Fragments.FacultyRegister;
import com.example.admin.idlogin.Fragments.KeeperLoginHome;
import com.example.admin.idlogin.Fragments.ProceedToPayment;
import com.example.admin.idlogin.Fragments.StudentRegister;

public class FragmentsList extends FragmentActivity
{
    String actionid,totalAmount=null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragments_list);

        Log.e("AA","EE");
        Intent main=getIntent();
        actionid=main.getStringExtra("actionid");
        Toast.makeText(getApplicationContext(),""+actionid,Toast.LENGTH_SHORT).show();

        Log.e("AA","QQ");
        switch (actionid)
        {
            case "Student":
                StudentRegister sr=new StudentRegister();
                getSupportFragmentManager().beginTransaction().replace(R.id.Fragment,sr).commit();
                break;
            case "Faculty":
                FacultyRegister fr=new FacultyRegister();
                getSupportFragmentManager().beginTransaction().replace(R.id.Fragment,fr).commit();
                break;
            case "studentLoginHomePage":
                Log.e("BB","BBB");
               Intent i=new Intent(getApplicationContext(),StudentLoginHome.class);
               startActivity(i);
                break;
            case "keeperLoginHomePage":
                KeeperLoginHome klh=new KeeperLoginHome();
                getSupportFragmentManager().beginTransaction().replace(R.id.Fragment,klh).commit();
                break;
            case "ProceedForPayment":
                totalAmount=main.getStringExtra("totalAmount");
                Toast.makeText(getApplicationContext(),totalAmount,Toast.LENGTH_SHORT);
            ProceedToPayment ptpp=new ProceedToPayment();
            getSupportFragmentManager().beginTransaction().replace(R.id.Fragment,ptpp).commit();

            default:
                Toast.makeText(getApplicationContext(),"0",Toast.LENGTH_SHORT).show();
                break;
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        // Override this method in the activity that hosts the Fragment and call super
        // in order to receive the result inside onActivityResult from the fragment.
        super.onActivityResult(requestCode, resultCode, intent);
    }
}