package com.example.vishal.GecaAlumni.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.vishal.GecaAlumni.R;

public class Give extends AppCompatActivity {


    Button btn_posting;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_give);


     /*   btn_posting = (Button) findViewById(R.id.btn_posting);

        btn_posting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Give.this,JobPosting.class);
                startActivity(intent);
            }
        });*/
    }
}
