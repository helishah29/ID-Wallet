package com.example.myinstamojo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

/**
 * Created by 43L23 on 20-Jan-2018.
 */

public class Success extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.success);

        TextView r = (TextView) findViewById(R.id.textView3);
        Bundle extras = getIntent().getExtras();

        r.setText(extras.getString("URL"));
    }
}
