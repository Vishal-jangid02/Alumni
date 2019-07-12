package com.example.vishal.GecaAlumni.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vishal.GecaAlumni.R;


public class WallViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


    public TextView wallName,companyName,donateMoney;
    public ImageView wallImage;


    public WallViewHolder(View itemView) {
        super(itemView);

        wallName = (TextView) itemView.findViewById(R.id.wallName);
        companyName = (TextView) itemView.findViewById(R.id.companyName);
        donateMoney = (TextView) itemView.findViewById(R.id.donateMoney);
        wallImage = (ImageView) itemView.findViewById(R.id.wallImage);
    }

    @Override
    public void onClick(View v) {

    }
}
