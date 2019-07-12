package com.example.vishal.GecaAlumni.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vishal.GecaAlumni.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Member;

import cz.msebera.android.httpclient.Header;

public class MemberPro extends AppCompatActivity {

    //CollapsingToolbarLayout collapsTool;
    TextView memName,memCourse,memBranch,memCompany,memPosition,memStartYear,memEndYear,memEmail,memAddress,memMobile;
    Button btnSendMsg;
    ImageView memImg;

    String url ="http://192.168.43.215:8084/RESTDEMO/DownImg.do";

    RequestParams params;
    AsyncHttpClient client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_pro);

        /*collapsTool = (CollapsingToolbarLayout) findViewById(R.id.collapsTool);
        collapsTool.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);
        collapsTool.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
*/
        memName = (TextView) findViewById(R.id.memName);
        memCourse = (TextView) findViewById(R.id.memCourse);
        memBranch = (TextView) findViewById(R.id.memBranch);
        memCompany = (TextView) findViewById(R.id.memCompany);
        memPosition = (TextView) findViewById(R.id.memPosition);
        memStartYear = (TextView) findViewById(R.id.memStartYear);
        memEndYear = (TextView) findViewById(R.id.memEndYear);
        memEmail = (TextView) findViewById(R.id.memEmail);
        memAddress = (TextView) findViewById(R.id.memAddress);
        memMobile = (TextView) findViewById(R.id.memMobile);
        btnSendMsg = (Button) findViewById(R.id.btnsendMsg);
        //memImg = (ImageView) findViewById(R.id.memImg);


        final Intent intent = getIntent();
        String fullname = intent.getStringExtra("fullname");
        final String email = intent.getStringExtra("email");
        String mobno = intent.getStringExtra("mobno");
        String course = intent.getStringExtra("course");
        String branch = intent.getStringExtra("branch");
        String startyear = intent.getStringExtra("startyear");
        String endyear = intent.getStringExtra("endyear");
        String prescomp = intent.getStringExtra("prescomp");
        String prespos = intent.getStringExtra("prespos");
        String address = intent.getStringExtra("address");

        memName.setText(fullname);
        memCourse.setText(course);
        memBranch.setText(branch);
        memCompany.setText(prescomp);
        memPosition.setText(prespos);
        memStartYear.setText(startyear);
        memEndYear.setText(endyear);
        memEmail.setText(email);
        memAddress.setText(address);
        memMobile.setText(mobno);

        btnSendMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent1 = new Intent(MemberPro.this,FirstSendMsg.class);
                intent1.putExtra("receiver",email);
                startActivity(intent1);
            }
        });

        client = new AsyncHttpClient();
        params = new RequestParams();
        params.put("email",email);

        client.get(url, params, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.d("GotResponseSuccess", response.toString());
                Toast.makeText(MemberPro.this, response.toString(), Toast.LENGTH_LONG);

              /*  progressDialog = new ProgressDialog(Registration.this);
                progressDialog.setMessage("Please wait..!");
                progressDialog.setTitle("Registration");
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.show();*/

                String encodedImage = null;
                /*try {
                    Log.e("1234567", "onSuccess: "+ response.get("response").toString());
                    byte[] bytes = (byte[]) response.get("response");

                    byte[] decodedString = Base64.decode(encodedImage,Base64.NO_PADDING);
                    Bitmap decodedByte = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

                    memImg.setImageBitmap(decodedByte);
                } catch (JSONException e) {
                    e.printStackTrace();
                }*/


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);

                Log.d("GotResponseFailure", "Failed");
                Toast.makeText(MemberPro.this, "Failed", Toast.LENGTH_LONG);
            }
        });
    }
}
