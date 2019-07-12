package com.example.vishal.GecaAlumni.Activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.vishal.GecaAlumni.R;

public class Contact_Us extends AppCompatActivity {

    TextView ClgNum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact__us);

        ClgNum=(TextView) findViewById(R.id.ClgNum);

        ClgNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent1 = new Intent(Intent.ACTION_DIAL);
                intent1.setData(Uri.parse("tel:+911452971024 "));
                startActivity(intent1);
            }
        });
    }
}
