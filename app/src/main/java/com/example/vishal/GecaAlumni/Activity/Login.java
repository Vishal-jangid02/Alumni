package com.example.vishal.GecaAlumni.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.vishal.GecaAlumni.Pojo.RegPojo;
import com.example.vishal.GecaAlumni.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class Login extends AppCompatActivity {

    Button btnNewAcc,btnLogin,btnForgot;
    EditText editEmail,editPassword;

    ImageView loginImg;

    String Email=" ",Password=" ";
    ArrayList<String> error;

    ProgressDialog progressDialog;

    Animation login;

    RequestParams params,params1;
    AsyncHttpClient client,client1 ;
    String url1= "http://192.168.43.215:8084/RESTDEMO/EmailVerification.do";
    Boolean flag = false;
    String email;
    String url = "http://192.168.43.215:8084/RESTDEMO/ForgotPassword.do";
    String url2 = "http://192.168.43.215:8084/RESTDEMO/login.do";
    RegPojo regPojo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editEmail=(EditText) findViewById(R.id.editEmail);
        editPassword=(EditText) findViewById(R.id.editPassword);
        btnLogin=(Button) findViewById(R.id.btnLogin);
        btnForgot=(Button) findViewById(R.id.btnForgot);
        btnNewAcc=(Button) findViewById(R.id.btnNewAcc);
        loginImg = (ImageView) findViewById(R.id.loginImg);
        error=new ArrayList<>();
        regPojo=new RegPojo();

        //load animation
        login = AnimationUtils.loadAnimation(this,R.anim.login);

        //passing animation
        loginImg.startAnimation(login);


      /*  auth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("Registration");
*/

        btnNewAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(Login.this,Registration.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Email=editEmail.getText().toString();
                Password=editPassword.getText().toString();
                /*Email Validation*/
                if(Email.length()==0){
                    error.add("Email");
                    editEmail.requestFocus();
                    editEmail.setError("FIELD CANNOT BE EMPTY");
                }
                else if(!Email.matches("[0-9a-zA-Z@.]+")){
                    error.add("Email");
                    editEmail.requestFocus();
                    editEmail.setError("ENTER CORRECT EMAIL");
                }

                /*Password Validation*/
                else if(Password.length()==0){
                    error.add("Password");
                    editPassword.requestFocus();
                    editPassword.setError("FIELD CANNOT BE EMPTY");
                }
                else if(!Password.matches("[0-9a-zA-Z@.]+")){
                    error.add("Password");
                    editPassword.requestFocus();
                    editPassword.setError("ENTER CORRECT PASSWORD");
                }
                else if(Password.length()<8){
                    error.add("Password");
                    editPassword.requestFocus();
                    editPassword.setError("ENTER MiN 8 CHARACTER");
                }
                Log.d("inside onclick", "Aa gya huu onclick mee");
                if(error.isEmpty()){
                    ///Toast.makeText(Login.this,"...",Toast.LENGTH_LONG).show();
                    login();
                }
            }
        });
        btnForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Email=editEmail.getText().toString();

                if(Email.length()==0){
                    error.add("Email");
                    editEmail.requestFocus();
                    editEmail.setError("FIELD CANNOT BE EMPTY");
                }
                else if(!Email.matches("[0-9a-zA-Z.]+")){
                    error.add("Email");
                    editEmail.requestFocus();
                    editEmail.setError("ENTER CORRECT EMAIL");
                }
               forgot();

            }
        });
    }

    private void forgot() {

        Log.d("Inside login", "aa gya huuu ");
        params = new RequestParams();
        params.put("email",Email);
        client = new AsyncHttpClient();
        Log.d("Inside login 2", "aa gya huuu ");
        url = "http://192.168.43.215:8084/RESTDEMO/ForgotPassword.do";
        client.get(url, params, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.d("GotResponseSuccess", response.toString());


                //Toast.makeText(Login.this, response.toString(), Toast.LENGTH_LONG);

                if (response.toString().contains("\"No\""))
                {
                    Toast.makeText(Login.this, "Email not Registered", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    try {

                        if(response.get("emailVerified").toString().equals("YES")) {
                            Log.d("12345678", "going to client 1");
                            flag = true;
                            email =  response.get("email").toString();
                        }
                        else{
                            Log.d("notvery", "onSuccess: 00");
                            Toast.makeText(Login.this, "Email Not Verified",Toast.LENGTH_SHORT).show();
                        }
                    }
                    catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);

                Log.d("GotResponseFailure", "Failed");
                Toast.makeText(Login.this, "Failed", Toast.LENGTH_LONG).show();
            }

        });

        if(flag){
            params1 = new RequestParams();
            params1.put("recipient",email);
            Log.d("123456",email);
            params1.put("type","forgot");

            client1 = new AsyncHttpClient();
            url1= "http://192.168.43.215:8084/RESTDEMO/EmailVerification.do";
            client1.get(url1, params1, new JsonHttpResponseHandler(){
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    super.onSuccess(statusCode, headers, response);
                    Log.d("GotResponseSuccess", response.toString());
                    Toast.makeText(Login.this,"password is send on your email", Toast.LENGTH_LONG).show();

      }

                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    super.onFailure(statusCode, headers, responseString, throwable);

                    Log.d("GotResponseFailure12345", "Failed");
                    Toast.makeText(Login.this, "Slow Network", Toast.LENGTH_LONG).show();
                }
            });
        }

    }

    public void login(){

        Log.d("Inside login", "aa gya huuu ");
        params = new RequestParams();
        params.put("email",Email);
        params.put("password",Password);
        client = new AsyncHttpClient();
        Log.d("Inside login 2", "aa gya huuu ");
        url2 = "http://192.168.43.215:8084/RESTDEMO/login.do";
        client.get(url2, params, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.d("GotResponseSuccess", response.toString());


                //Toast.makeText(Login.this, response.toString(), Toast.LENGTH_LONG);

                if (response.toString().contains("\"No\""))
                {
                    Toast.makeText(Login.this, "Wrong Login Credentials", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    try {

                        if(response.get("emailVerified").toString().equals("YES")) {

                            progressDialog = new ProgressDialog(Login.this);
                            progressDialog.setMessage("Please wait..!");
                            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                            progressDialog.setTitle("Login");
                            progressDialog.show();

                            Log.d("Response me Yes Hai", "Yes aaya hai");
                            String name = response.get("fullname").toString();
                            SharedPreferences sp = getSharedPreferences("user", 0);
                            SharedPreferences.Editor editor = sp.edit();
                            editor.putString("email", response.get("email").toString());
                            editor.putString("fullname", response.get("fullname").toString());
                            editor.putString("mobno", response.get("mobno").toString());
                            // editor.putString("clgid",response.get("clgid").toString());
                            editor.putString("branch", response.get("branch").toString());
                            editor.putString("course", response.get("course").toString());
                            editor.putString("prescomp", response.get("prescomp").toString());
                            editor.putString("prespos", response.get("prespos").toString());
                            editor.putString("address", response.get("address").toString());
                            editor.putString("startyear", response.get("startyear").toString());
                            editor.putString("endyear", response.get("endyear").toString());
                        /*editor.putString("imageUrl",regPojo.getImageUrl());
                        editor.putString("infoText",regPojo.getInfotext());*/
                            editor.putBoolean("flag", true);
                            Log.d("122name", "onSuccess:" + name + response.toString());

                            editor.commit();
                            Intent intent = new Intent(Login.this, Navigation.class);
                            startActivity(intent);
                            finish();
                        }
                        else{
                            Log.d("notvery", "onSuccess: 00");
                            Toast.makeText(Login.this, "Email Not Verified",Toast.LENGTH_SHORT).show();
                        }
                    }
                    catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);

                Log.d("GotResponseFailure", "Failed");
                Toast.makeText(Login.this, "Failed", Toast.LENGTH_LONG);
            }

        });

        Log.d("Inside login 3", "aa gya huuu ");


    }
}
