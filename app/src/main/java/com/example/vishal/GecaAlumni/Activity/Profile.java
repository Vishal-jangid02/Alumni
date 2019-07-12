package com.example.vishal.GecaAlumni.Activity;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vishal.GecaAlumni.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class Profile extends AppCompatActivity {

    CircleImageView userImage;
    TextView txtName,txtMobile,txtEmail,txtOrganization,txtAddress,txtClgId,txtCourse,txtBranch;

    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //userImage = (CircleImageView) findViewById(R.id.userImage);
        txtName = (TextView) findViewById(R.id.txtName);
        txtMobile = (TextView) findViewById(R.id.txtMobile);
        txtEmail = (TextView) findViewById(R.id.txtEmail);
        txtOrganization = (TextView) findViewById(R.id.txtOrganization);
        txtAddress = (TextView)  findViewById(R.id.txtAddress);
        txtCourse = (TextView) findViewById(R.id.txtCourse);
        txtBranch = (TextView) findViewById(R.id.txtBranch);


        sp=getSharedPreferences("user",0);

        //Picasso.get().load(sp.getString("imageUrl","https://firebasestorage.googleapis.com/v0/b/gecaalumni-b9384.appspot.com/o/imae.jpg?alt=media&token=76d3d794-25be-4925-9dc6-e5832d07f399")).into(userImage);
        txtName.setText(sp.getString("fullname"," "));
        txtMobile.setText(sp.getString("mobno"," "));
        txtEmail.setText(sp.getString("email"," "));
        txtOrganization.setText(sp.getString("prescomp"," "));
        txtAddress.setText(sp.getString("address"," "));
        txtCourse.setText(sp.getString("course"," "));
        txtBranch.setText(sp.getString("branch"," "));

    }
}
