package com.example.vishal.GecaAlumni.Activity;

import android.app.ProgressDialog;
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

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class JobPosting extends AppCompatActivity {

    EditText txt_posJobType;
    EditText txt_posExperience;
    EditText txt_posLocation;
    EditText txt_posSkills;
    EditText txt_posDate;
    EditText txt_posCtc;
    EditText txt_posDescription;
    EditText txt_posIndustryType;
    EditText txt_posRole;
    EditText txt_posEmpType;
    EditText txt_posCandidatePro;
    EditText txt_posCompanyName;
    EditText txt_posCompanyWebsite;

    SharedPreferences sharedPreferences;

    ProgressDialog progressDialog;
    Button btn_posSubmit;

    String posPostedBy = " ",posJobType = " ",posExperience = " ",posLocation = " ",posSkills = " ",posDate = " ",posCtc = " ",posDescription = " ",
            posIndustryType = " ",posRole = " ",posEmpType = " ",posCandidatePro = " ",posCompanyName = " ",posCompanyWebsite = " ";

    RequestParams requestParams;
    AsyncHttpClient asyncHttpClient;
    String url = "http://192.168.43.215:8084/RESTDEMO/JobPosting.do";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_posting);

        txt_posJobType = (EditText) findViewById(R.id.txt_posJobType);
        txt_posExperience = (EditText) findViewById(R.id.txt_posExperience);
        txt_posLocation = (EditText) findViewById(R.id.txt_posLocation);
        txt_posSkills = (EditText) findViewById(R.id.txt_posSkills);
        txt_posDate = (EditText) findViewById(R.id.txt_posDate);
        txt_posCtc = (EditText) findViewById(R.id.txt_posCtc);
        txt_posDescription = (EditText) findViewById(R.id.txt_posDescription);
        txt_posIndustryType = (EditText) findViewById(R.id.txt_posIndustryType);
        txt_posRole = (EditText) findViewById(R.id.txt_posRole);
        txt_posEmpType = (EditText) findViewById(R.id.txt_posEmpType);
        txt_posCandidatePro = (EditText) findViewById(R.id.txt_posCandidatePro);
        txt_posCompanyName = (EditText) findViewById(R.id.txt_posCompanyName);
        txt_posCompanyWebsite = (EditText) findViewById(R.id.txt_posCompanyWebsite);

        btn_posSubmit = (Button) findViewById(R.id.btn_posSubmit);


        final ArrayList<String> error = new ArrayList<>();



        btn_posSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("7777", "onClick:");

                sharedPreferences = getSharedPreferences("user", 0);
                posPostedBy = sharedPreferences.getString("fullname", "");

                posJobType = txt_posJobType.getText().toString();
                posExperience = txt_posExperience.getText().toString();
                posLocation = txt_posLocation.getText().toString();
                posSkills = txt_posSkills.getText().toString();
                posDate = txt_posDate.getText().toString();
                posCtc = txt_posCtc.getText().toString();
                posDescription = txt_posDescription.getText().toString();
                posIndustryType = txt_posIndustryType.getText().toString();
                posRole = txt_posRole.getText().toString();
                posEmpType = txt_posEmpType.getText().toString();
                posCandidatePro = txt_posCandidatePro.getText().toString();
                posCompanyName = txt_posCompanyName.getText().toString();
                posCompanyWebsite = txt_posCompanyWebsite.getText().toString();

                if (posJobType.length() == 0) {
                    error.add("postedby");
                    txt_posJobType.requestFocus();
                    txt_posJobType.setError("FIELD CANNOT BE EMPTY");
                }
                else if(posExperience.length()==0){
                    error.add("experience");
                    txt_posExperience.requestFocus();
                    txt_posExperience.setError("FIELD CANNOT BE EMPTY");
                }
                else if(posLocation.length()==0){
                    error.add("location");
                    txt_posLocation.requestFocus();
                    txt_posLocation.setError("FIELD CANNOT BE EMPTY");
                }
                else if(posSkills.length()==0){
                    error.add("skills");
                    txt_posSkills.requestFocus();
                    txt_posSkills.setError("FIELD CANNOT BE EMPTY");
                }
                else if(posDate.length()==0){
                    error.add("date");
                    txt_posDate.requestFocus();
                    txt_posDate.setError("FIELD CANNOT BE EMPTY");
                }
                else if(posCtc.length()==0){
                    error.add("Ctc");
                    txt_posCtc.requestFocus();
                    txt_posCtc.setError("FIELD CANNOT BE EMPTY");
                }

                else if(posDescription.length()==0){
                    error.add("des");
                    txt_posDescription.requestFocus();
                    txt_posDescription.setError("FIELD CANNOT BE EMPTY");
                }
                else if(posIndustryType.length()==0){
                    error.add("industry");
                    txt_posIndustryType.requestFocus();
                    txt_posIndustryType.setError("FIELD CANNOT BE EMPTY");
                }
                else if(posRole.length()==0){
                    error.add("role");
                    txt_posRole.requestFocus();
                    txt_posRole.setError("FIELD CANNOT BE EMPTY");
                }
                else if(posEmpType.length()==0){
                    error.add("emp");
                    txt_posEmpType.requestFocus();
                    txt_posEmpType.setError("FIELD CANNOT BE EMPTY");
                }
                else if(posCandidatePro.length()==0){
                    error.add("candidate");
                    txt_posCandidatePro.requestFocus();
                    txt_posCandidatePro.setError("FIELD CANNOT BE EMPTY");
                }
                else if(posCompanyName.length()==0){
                    error.add("comp");
                    txt_posCompanyName.requestFocus();
                    txt_posCompanyName.setError("FIELD CANNOT BE EMPTY");
                }
                else if(posCompanyWebsite.length()==0){
                    error.add("company");
                    txt_posCompanyWebsite.requestFocus();
                    txt_posCompanyWebsite.setError("FIELD CANNOT BE EMPTY");
                }

                else {


                    Log.d("8888", "aftpos ");

                    requestParams = new RequestParams();
                    requestParams.put("postedby", posPostedBy);
                    requestParams.put("jobtype", posJobType);
                    requestParams.put("experience", posExperience);
                    requestParams.put("location", posLocation);
                    requestParams.put("skills", posSkills);
                    requestParams.put("ctc", posCtc);
                    requestParams.put("dateofpost", posDate);
                    requestParams.put("jd", posDescription);
                    requestParams.put("industrytype", posIndustryType);
                    requestParams.put("role", posRole);
                    requestParams.put("emptype", posEmpType);
                    requestParams.put("candidateprofile", posCandidatePro);
                    requestParams.put("companyname", posCompanyName);
                    requestParams.put("companywebsite", posCompanyWebsite);

                    Log.d("999", "aftparam "+requestParams);

                    asyncHttpClient = new AsyncHttpClient();

                    Log.d("100", "onClick: dd");

                    asyncHttpClient.get(url, requestParams, new JsonHttpResponseHandler() {

                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            //super.onSuccess(statusCode, headers, response);

                            Log.d("1", "onSuccess: ");
                            if (response.toString().contains("Yes")) {

                                Toast.makeText(JobPosting.this, "DataSubmit", Toast.LENGTH_LONG).show();

                                Log.d("2", "onSuccess: ");
                            }

                            txt_posJobType.setText("");
                            txt_posExperience.setText("");
                            txt_posLocation.setText("");
                            txt_posSkills.setText("");
                            txt_posDate.setText("");
                            txt_posCtc.setText("");
                            txt_posDescription.setText("");
                            txt_posIndustryType.setText("");
                            txt_posRole.setText("");
                            txt_posEmpType.setText("");
                            txt_posCandidatePro.setText("");
                            txt_posCompanyName.setText("");
                            txt_posCompanyWebsite.setText("");

                            Log.d("3", "onSuccess: ");
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                            // super.onFailure(statusCode, headers, responseString, throwable);

                            Toast.makeText(JobPosting.this, "Failed", Toast.LENGTH_LONG).show();

                        }

                    });

                   /*asyncHttpClient.get(url,requestParams,new JsonHttpResponseHandler(){
                       @Override
                       public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                           super.onSuccess(statusCode, headers, response);
                           Log.d("661", "onSuccess: in");

                           if(response.toString().contains("\"Yes\"")){
                               Toast.makeText(JobPosting.this,"Data Submit",Toast.LENGTH_LONG).show();
                           }
                       }

                       @Override
                       public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                           super.onFailure(statusCode, headers, responseString, throwable);

                           Toast.makeText(JobPosting.this,"Failed",Toast.LENGTH_LONG).show();
                       }
                   });*/
                    Log.d("0000", "aftsuccess");

                }
            }

        });


    }
}
