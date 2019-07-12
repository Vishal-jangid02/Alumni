package com.example.vishal.GecaAlumni.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.vishal.GecaAlumni.R;

public class NewJobViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView txt_newJob,txt_newPostedBy;

    public NewJobViewHolder(View itemView) {
        super(itemView);

        txt_newJob = (TextView) itemView.findViewById(R.id.txt_newJob);
        txt_newPostedBy = (TextView) itemView.findViewById(R.id.txt_newPostedBy);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }
}
