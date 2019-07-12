package com.example.vishal.GecaAlumni.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.vishal.GecaAlumni.R;

public class JobDescription extends AppCompatActivity {

    TextView txt_desJobType,txt_desExperience,txt_desLocation,txt_desSkills,txt_desCtc,txt_desDateOfPost,txt_desJobDescription,txt_desIndustryType,
             txt_desRole,txt_desEmploymentType,txt_desCandidatePro,txt_desCompanyName,txt_desCompanyWeb,txt_desPostedBy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_description);


        txt_desJobType = (TextView) findViewById(R.id.txt_desJobType);
        txt_desExperience = (TextView) findViewById(R.id.txt_desExperience);
        txt_desLocation = (TextView) findViewById(R.id.txt_desLocation);
        txt_desSkills = (TextView) findViewById(R.id.txt_desSkills);
        txt_desCtc = (TextView) findViewById(R.id.txt_desCtc);
        txt_desDateOfPost  = (TextView) findViewById(R.id.txt_desDateOfPost);
        txt_desJobDescription = (TextView) findViewById(R.id.txt_desJobDescription);
        txt_desIndustryType = (TextView) findViewById(R.id.txt_desIndustryType);
        txt_desRole = (TextView) findViewById(R.id.txt_desRole);
        txt_desEmploymentType = (TextView) findViewById(R.id.txt_desEmploymentType);
        txt_desCandidatePro = (TextView) findViewById(R.id.txt_desCandidatePro);
        txt_desCompanyName = (TextView) findViewById(R.id.txt_desCompanyName);
        txt_desCompanyWeb = (TextView) findViewById(R.id.txt_desCompanyWeb);
        txt_desPostedBy = (TextView) findViewById(R.id.txt_desPostedBy);

        Intent intent = getIntent();

        String postedby = intent.getStringExtra("postedby");
        String jobtype = intent.getStringExtra("jobtype");
        String experience = intent.getStringExtra("experience");
        String location = intent.getStringExtra("location");
        String skills = intent.getStringExtra("skills");
        String ctc = intent.getStringExtra("ctc");
        String dateofpost = intent.getStringExtra("dateofpost");
        String jd = intent.getStringExtra("jd");
        String industrytype = intent.getStringExtra("industrytype");
        String role = intent.getStringExtra("role");
        String emptype = intent.getStringExtra("emptype");
        String candidateprofile = intent.getStringExtra("candidateprofile");
        String companyname = intent.getStringExtra("companyname");
        String companywebsite = intent.getStringExtra("companywebsite");


        txt_desJobType.setText(jobtype);
        txt_desExperience.setText(experience);
        txt_desLocation.setText(location);
        txt_desSkills.setText(skills);
        txt_desCtc.setText(ctc);
        txt_desDateOfPost.setText(dateofpost);
        txt_desJobDescription.setText(jd);
        txt_desIndustryType.setText(industrytype);
        txt_desRole.setText(role);
        txt_desEmploymentType.setText(emptype);
        txt_desCandidatePro.setText(candidateprofile);
        txt_desCompanyName.setText(companyname);
        txt_desCompanyWeb.setText(companywebsite);
        txt_desPostedBy.setText(postedby);
    }
}
