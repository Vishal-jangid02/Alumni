package com.example.vishal.GecaAlumni.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vishal.GecaAlumni.R;

public class MemViewHolder extends RecyclerView.ViewHolder implements  OnClickListener {

    public TextView username,coursename,branchname;
    public ImageView MemberImage;


    public MemViewHolder(View itemView) {
        super(itemView);

        //MemberImage=(ImageView)itemView.findViewById(R.id.MemberImage);
        username=(TextView)itemView.findViewById(R.id.username);
        coursename=(TextView)itemView.findViewById(R.id.coursename);
        branchname=(TextView)itemView.findViewById(R.id.branchname);


        itemView.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {


    }
}
