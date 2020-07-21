package com.example.admin.idlogin.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.idlogin.FragmentsList;
import com.example.admin.idlogin.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import static android.text.TextUtils.indexOf;


public class RegisterType extends Fragment
{
    TextView tv;
    Spinner type;
    Button select;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View root=inflater.inflate(R.layout.fragment_register_type, container, false);
        tv=(TextView)root.findViewById(R.id.textView2);
        type=(Spinner)root.findViewById(R.id.spinner);
        select=(Button)root.findViewById(R.id.select);

        Toast.makeText(getActivity(),"Hello",Toast.LENGTH_SHORT).show();

        final String size[]={"Student","Faculty"};

        ArrayAdapter<String> ad=new ArrayAdapter<String>(getActivity(),R.layout.support_simple_spinner_dropdown_item,size);
        type.setAdapter(ad);


        select.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
                {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
                    {
                        Toast.makeText(getActivity(),""+type.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView)
                    {

                    }
                });
                Intent newActivity=new Intent(getActivity(), FragmentsList.class);
                newActivity.putExtra("actionid",type.getSelectedItem().toString());
                startActivity(newActivity);
            }
        });
        return root;
    }
}