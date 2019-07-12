package com.example.vishal.GecaAlumni.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.vishal.GecaAlumni.Adapter.JobAvailableAdapter;
import com.example.vishal.GecaAlumni.Pojo.JobAvailablePojo;
import com.example.vishal.GecaAlumni.R;
import com.example.vishal.GecaAlumni.ViewHolder.ViewAdapter;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class JobAvailable extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    JobAvailableAdapter jobAvailableAdapter;

    JobAvailablePojo jobAvailablePojo;
    ArrayList<JobAvailablePojo> arrlist;

    RequestParams params;
    AsyncHttpClient asyncHttpClient;
    JSONObject jsonObject;

    String url = "http://192.168.43.215:8084/RESTDEMO/JobAvailable.do";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_available);


        recyclerView = (RecyclerView) findViewById(R.id.recycle_jobs);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        arrlist = new ArrayList<>();
        jobAvailableAdapter = new JobAvailableAdapter(JobAvailable.this,arrlist);
        recyclerView.setAdapter(jobAvailableAdapter);
        asyncHttpClient = new AsyncHttpClient();

        asyncHttpClient.get(url,new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {

                super.onSuccess(statusCode, headers, response);
               /* jobAvailableAdapter = new JobAvailableAdapter(JobAvailable.this,arrlist);
                recyclerView.setAdapter(jobAvailableAdapter);
*/
                if(response != null){

                    for(int i=0;i < response.length();i++){

                        try {

                            jsonObject = response.getJSONObject(i);
                            jobAvailablePojo = new JobAvailablePojo();

                            jobAvailablePojo.setPostedby(jsonObject.getString("postedby").toString());
                            jobAvailablePojo.setJobtype(jsonObject.getString("jobtype").toString());
                            jobAvailablePojo.setExperience(jsonObject.getString("experience").toString());
                            jobAvailablePojo.setLocation(jsonObject.getString("location").toString());
                            jobAvailablePojo.setSkills(jsonObject.getString("skills").toString());
                            jobAvailablePojo.setCtc(jsonObject.getString("ctc").toString());
                            jobAvailablePojo.setDateofpost(jsonObject.getString("dateofpost").toString());
                            jobAvailablePojo.setJd(jsonObject.getString("jd").toString());
                            jobAvailablePojo.setIndustrytype(jsonObject.getString("industrytype").toString());
                            jobAvailablePojo.setRole(jsonObject.getString("role").toString());
                            jobAvailablePojo.setEmptype(jsonObject.getString("emptype").toString());
                            jobAvailablePojo.setCandidateprofile(jsonObject.getString("candidateprofile").toString());
                            jobAvailablePojo.setCompanyname(jsonObject.getString("companyname").toString());
                            jobAvailablePojo.setCompanywebsite(jsonObject.getString("companywebsite").toString());

                            Log.d("0909", "onSuccess: yep"+jsonObject.toString());
                            arrlist.add(jobAvailablePojo);

                            jobAvailableAdapter.notifyDataSetChanged();

                            Log.d("0101", "onSuccess: yuuu");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
                else{
                    Toast.makeText(JobAvailable.this,"Please Wait",Toast.LENGTH_LONG);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);

                Toast.makeText(JobAvailable.this,"Failed",Toast.LENGTH_LONG);
            }
        });


    }
}
