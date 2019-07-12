package com.example.vishal.GecaAlumni.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.vishal.GecaAlumni.Pojo.RegPojo;
import com.example.vishal.GecaAlumni.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.file.Files;

import cz.msebera.android.httpclient.Header;

public class Navigation extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Animation animation,txtone,txttwo;

   //Button profile,findothers,give,events,walloffame,news,btnfindothers;
    RequestParams requestParams;

    AsyncHttpClient client;

    SharedPreferences sp;

    NavigationView navigationView;

    TextView headerName;
    View hview;
    ImageView imgfind;
    Button btn_HomeChat;


    String url="http://192.168.43.215:8084/RESTDEMO/NewMessageCheck.do";

    CardView card_Profile,card_FindOthers,card_Events,card_WallOfFame,card_JobPosting,card_Donates,card_News,card_JobAvailable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //btnfindothers = (Button) findViewById(R.id.btnfindothers);
        //imgfind = (ImageView) findViewById(R.id.imgfind);
        navigationView = findViewById(R.id.nav_view);
        btn_HomeChat = findViewById(R.id.btn_HomeChat);
       /* profile = (Button) findViewById(R.id.profile);
        give = (Button)findViewById(R.id.give) ;
        events = (Button)findViewById(R.id.events);
        walloffame = (Button)findViewById(R.id.walloffame);
        news = (Button) findViewById(R.id.news);*/


       card_Profile = (CardView) findViewById(R.id.card_Profile);
       card_FindOthers = (CardView) findViewById(R.id.card_FindOthers);
       card_Events = (CardView) findViewById(R.id.card_Events);
       card_WallOfFame = (CardView) findViewById(R.id.card_WallOfFame);
       card_JobPosting = (CardView) findViewById(R.id.card_JobPosting);
       card_JobAvailable = (CardView) findViewById(R.id.card_JobAvailable);
       card_Donates = (CardView) findViewById(R.id.card_Donates);
       card_News = (CardView) findViewById(R.id.card_News);

        //load animation
        animation = AnimationUtils.loadAnimation(this, R.anim.splash);
        txtone = AnimationUtils.loadAnimation(this, R.anim.txtone);
        txttwo = AnimationUtils.loadAnimation(this, R.anim.txttwo);
        //passing animation
       /* imgfind.startAnimation(txtone);
        btnfindothers.startAnimation(txttwo);
*/

        card_Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Navigation.this,Profile.class));
            }
        });
        card_FindOthers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Navigation.this,AlumniCategory.class));
            }
        });
        card_JobPosting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Navigation.this,JobPosting.class));
            }
        }); card_Events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Navigation.this,Events.class));
            }
        });
        card_WallOfFame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Navigation.this,Walloffame.class));
            }
        });
        card_JobAvailable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Navigation.this,JobAvailable.class));
            }
        });
        card_Donates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Navigation.this,checksum.class));
            }
        });
        card_News.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Navigation.this,News.class));
            }
        });

        sp=getSharedPreferences("user",0);
        String email = sp.getString("email","");

        hview=navigationView.getHeaderView(0);
        headerName=hview.findViewById(R.id.headerName);
        headerName.setText(sp.getString("fullname","user"));
        //ImageView imagePro=hview.findViewById(R.id.imagePro);

        //Picasso.get().load(sp.getString("imageUrl",url)).into(imagePro);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

         navigationView = (NavigationView) findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(this);

        requestParams = new RequestParams();
        requestParams.put("email",email);

        client = new AsyncHttpClient();
        client.get(url, requestParams, new JsonHttpResponseHandler(){

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);

                Log.d("GotResponseSuccess", response.toString());

                //Toast.makeText(Navigation.this, response.toString(), Toast.LENGTH_LONG).show();
                btn_HomeChat.setText("New Message Available");

                try {
                    if (response.getString("response").toString().equals("0")){
                        btn_HomeChat.setText("Chat");
                    }
                    else {
                        btn_HomeChat.setText("New Message Available");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);

                Log.d("GotResponseFailure", "Failed");
                Toast.makeText(Navigation.this, "Failed", Toast.LENGTH_LONG).show();
            }


        });
        btn_HomeChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Navigation.this,ChatList.class));
            }
        });


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            new AlertDialog.Builder(this)
                    .setMessage("Do you want to close this app?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Navigation.this.finish();
                            System.exit(0);
                        }
                    })
                    .setNegativeButton("No", null)
                    .show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
       /* if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.editProfile) {

            Intent intent = new Intent(Navigation.this, EditProfile.class);
            startActivity(intent);

            // Handle the camera action
        } /*else if (id == R.id.changePassword) {

            Intent intent = new Intent(Navigation.this, fgpassword.class);
            startActivity(intent);

        }*/ /*else if (id == R.id.aboutus) {

            startActivity(new Intent(Navigation.this,AboutUs.class));
        }*/ else if (id == R.id.contactus) {

            Intent intent = new Intent(Navigation.this, Contact_Us.class);
            startActivity(intent);


        } else if (id == R.id.nav_share) {

            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            String sharebody = "http://play.google.com";
            String sharesub = "GecaAlumni";
            intent.putExtra(Intent.EXTRA_SUBJECT, sharesub);
            intent.putExtra(Intent.EXTRA_TEXT, sharebody);
            startActivity(Intent.createChooser(intent, "Share"));

        } else if (id == R.id.logout) {

            sp = getSharedPreferences("user", 0);

            Intent intent = new Intent(Navigation.this, Login.class);
            SharedPreferences.Editor edt = sp.edit();
            edt.clear();

            edt.commit();
            startActivity(intent);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    };


