package com.example.vishal.GecaAlumni.Adapter;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vishal.GecaAlumni.Activity.JobDescription;
import com.example.vishal.GecaAlumni.Pojo.JobAvailablePojo;
import com.example.vishal.GecaAlumni.R;
import com.example.vishal.GecaAlumni.ViewHolder.ViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class JobAvailableAdapter extends RecyclerView.Adapter<JobAvailableAdapter.DisViewHolder> {

    JobAvailablePojo jobAvailablePojo;
    Context context;
    List<JobAvailablePojo> list;



    public JobAvailableAdapter(Context context, List<JobAvailablePojo> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public DisViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.jobavailable_card,null);
        DisViewHolder disViewHolder = new DisViewHolder(view);
        return disViewHolder;
    }


    @NonNull

    @Override
    public void onBindViewHolder(@NonNull DisViewHolder holder, final int position) {

        jobAvailablePojo = list.get(position);
        Log.d("33", "onBindViewHolder: set");

        holder.txt_jobType.setText(jobAvailablePojo.getJobtype());
        holder.txt_jobExperience.setText(jobAvailablePojo.getExperience());
        holder.txt_jobLocation.setText(jobAvailablePojo.getLocation());
        holder.txt_jobSkills.setText(jobAvailablePojo.getSkills());

        holder.card_jobAvailable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                jobAvailablePojo = new JobAvailablePojo();
                jobAvailablePojo = list.get(position);

                Intent intent = new Intent(context, JobDescription.class);
                intent.putExtra("postedby",jobAvailablePojo.getPostedby());
                intent.putExtra("jobtype",jobAvailablePojo.getJobtype());
                intent.putExtra("experience",jobAvailablePojo.getExperience());
                intent.putExtra("location",jobAvailablePojo.getLocation());
                intent.putExtra("skills",jobAvailablePojo.getSkills());
                intent.putExtra("ctc",jobAvailablePojo.getCtc());
                intent.putExtra("dateofpost",jobAvailablePojo.getDateofpost());
                intent.putExtra("jd",jobAvailablePojo.getJd());
                intent.putExtra("industrytype",jobAvailablePojo.getIndustrytype());
                intent.putExtra("role",jobAvailablePojo.getRole());
                intent.putExtra("emptype",jobAvailablePojo.getEmptype());
                intent.putExtra("candidateprofile",jobAvailablePojo.getCandidateprofile());
                intent.putExtra("companyname",jobAvailablePojo.getCompanyname());
                intent.putExtra("companywebsite",jobAvailablePojo.getCompanywebsite());
                context.startActivity(intent);
            }
        });
        
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class DisViewHolder extends RecyclerView.ViewHolder {

        CardView card_jobAvailable;
        TextView txt_jobType,txt_jobExperience,txt_jobLocation,txt_jobSkills;

        public DisViewHolder(View itemView) {
            super(itemView);

            card_jobAvailable = (CardView) itemView.findViewById(R.id.card_jobAvailable);
            txt_jobType = (TextView) itemView.findViewById(R.id.txt_jobType);
            txt_jobLocation =(TextView) itemView.findViewById(R.id.txt_jobLocation);
            txt_jobExperience = (TextView) itemView.findViewById(R.id.txt_jobExperience);
            txt_jobSkills = (TextView) itemView.findViewById(R.id.txt_jobSkills);
        }
    }
}
