package com.example.vishal.GecaAlumni.Activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.vishal.GecaAlumni.R;

public class Events extends AppCompatActivity {

    TextView creativeart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        creativeart=(TextView) findViewById(R.id.creativeart);

        creativeart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                creativeart.setMovementMethod(LinkMovementMethod.getInstance());
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://ecajmer.ac.in/creativeartsociety")));
            }
        });

    }
}
