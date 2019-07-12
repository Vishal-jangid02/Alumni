package com.example.vishal.GecaAlumni.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vishal.GecaAlumni.Pojo.JobAvailablePojo;
import com.example.vishal.GecaAlumni.R;
import com.example.vishal.GecaAlumni.ViewHolder.JobViewHolder;

import java.util.List;

public class NewJobAdapter extends RecyclerView.Adapter<NewJobAdapter.JobViewHolder> {

    JobAvailablePojo jobAvailablePojo;
    Context context;
    List<JobAvailablePojo> list;

    public NewJobAdapter(Context context,List<JobAvailablePojo> list) {

        this.context=context;
        this.list=list;
    }

    @NonNull
    @Override
    public JobViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.newjob_card,null);
        JobViewHolder jobViewHolder=new JobViewHolder(view);
        return jobViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull JobViewHolder holder, int position) {

        jobAvailablePojo=list.get(position);
        holder.txt_newJob.setText(jobAvailablePojo.getJobtype());
        holder.txt_newPostedBy.setText(jobAvailablePojo.getPostedby());

        holder.card_newJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"Check in Available Jobs",Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class JobViewHolder extends RecyclerView.ViewHolder {

        CardView card_newJob;
        TextView txt_newJob,txt_newPostedBy;
        public JobViewHolder(View itemView) {
            super(itemView);

            card_newJob = (CardView) itemView.findViewById(R.id.card_newJob);
            txt_newJob = (TextView) itemView.findViewById(R.id.txt_newJob);
            txt_newPostedBy = (TextView) itemView.findViewById(R.id.txt_newPostedBy);
        }
    }
}
