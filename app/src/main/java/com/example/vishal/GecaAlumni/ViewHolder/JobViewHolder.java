package com.example.vishal.GecaAlumni.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.vishal.GecaAlumni.R;

public class JobViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    TextView txt_jobType,txt_jobExperience,txt_jobLocation,txt_jobSkills;


    public JobViewHolder(View itemView) {
        super(itemView);

        txt_jobType = (TextView) itemView.findViewById(R.id.txt_jobType);
        txt_jobExperience = (TextView) itemView.findViewById(R.id.txt_jobExperience);
        txt_jobLocation = (TextView) itemView.findViewById(R.id.txt_jobLocation);
        txt_jobSkills = (TextView) itemView.findViewById(R.id.txt_jobSkills);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }
}
