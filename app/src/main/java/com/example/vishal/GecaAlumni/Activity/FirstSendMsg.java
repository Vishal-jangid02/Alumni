package com.example.vishal.GecaAlumni.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vishal.GecaAlumni.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class FirstSendMsg extends AppCompatActivity {

    EditText txt_firstMsg;
    Button btn_firstSend;

    SharedPreferences sharedPreferences;
    String sender="";

    RequestParams requestParams;
     String receiver="";
    AsyncHttpClient asyncHttpClient ;
    String url1= "http://192.168.43.215:8084/RESTDEMO/MessageUpload.do";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_firstMsg = (EditText) findViewById(R.id.txt_firstMsg);
        btn_firstSend  =(Button) findViewById(R.id.btn_firstSend);



        sharedPreferences = getSharedPreferences("user",0);
        sender = sharedPreferences.getString("email","");

        Intent intent=getIntent();
         receiver =  intent.getStringExtra("receiver");

        btn_firstSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(txt_firstMsg.getText().toString().trim().equals("")){

                    Toast.makeText(FirstSendMsg.this,"Please input some text",Toast.LENGTH_LONG).show();
                }
                else {

                    send();
                }
            }
        });


    }
    public void send(){

        requestParams = new RequestParams();
        requestParams.put("sender",sender);
        requestParams.put("receiver",receiver);
        requestParams.put("message",txt_firstMsg.getText().toString());


        asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.get(url1, requestParams, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);

                Log.d("Success", response.toString());


                Toast.makeText(FirstSendMsg.this, response.toString(), Toast.LENGTH_LONG).show();

                if (response.toString().contains("\"No\""))
                {
                    Toast.makeText(FirstSendMsg.this, "Connection Failure", Toast.LENGTH_LONG).show();
                }
                else
                {
                    startActivity(new Intent(FirstSendMsg.this,ChatList.class));
                    finish();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);

                Log.d("GotResponseFailure", "Failed");
                Toast.makeText(FirstSendMsg.this, "Failed", Toast.LENGTH_LONG).show();
            }


        });

    }
}
