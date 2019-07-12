package com.example.vishal.GecaAlumni.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.vishal.GecaAlumni.Activity.Login;
import com.example.vishal.GecaAlumni.R;

public class SplashScreen extends AppCompatActivity {

    ImageView logoImage;
    /*TextView txtClgName,txtalumni;
    Button btnstart;*/

    ProgressBar progressBar;

    Boolean flag;

    Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
/*
        Logo = (ImageView) findViewById(R.id.Logo);
        txtClgName = (TextView) findViewById(R.id.txtClgName);
        txtalumni = (TextView) findViewById(R.id.txtalumni);
        btnstart = (Button) findViewById(R.id.btnstart);*/

        animation = AnimationUtils.loadAnimation(this,R.anim.login);

        logoImage = (ImageView) findViewById(R.id.logoImage);
        //passing animation
        logoImage.startAnimation(animation);

        //load animation
        /*animation = AnimationUtils.loadAnimation(this, R.anim.splash);
        txtone = AnimationUtils.loadAnimation(this, R.anim.txtone);
        txttwo = AnimationUtils.loadAnimation(this, R.anim.txttwo);*/

        //passing animation
        /*Logo.startAnimation(animation);
        txtClgName.startAnimation(txtone);
        txtalumni.startAnimation(txtone);
        btnstart.startAnimation(txttwo);*/

        SharedPreferences sp=getSharedPreferences("user",0);

        flag = sp.getBoolean("flag",false);

     /*   btnstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(flag){
                    Intent intent1 = new Intent(SplashScreen.this,Navigation.class);
                    startActivity(intent1);

                }
                else {
                    Intent intent = new Intent(SplashScreen.this,Login.class);
                    startActivity(intent);
                }
                finish();
            }
        });*/

     new Handler().postDelayed(new Runnable() {
         @Override
         public void run() {
             if(flag){
                 Intent intent = new Intent(SplashScreen.this,Navigation.class);
                 startActivity(intent);
                 finish();
             }
             else{
               Intent intent1 =  new Intent(SplashScreen.this,Login.class);
                 startActivity(intent1);
                 finish();
             }

         }
     },3000);

    }
}
