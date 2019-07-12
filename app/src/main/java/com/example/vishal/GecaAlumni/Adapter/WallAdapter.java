package com.example.vishal.GecaAlumni.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vishal.GecaAlumni.Activity.Walloffame;
import com.example.vishal.GecaAlumni.Pojo.WallPojo;
import com.example.vishal.GecaAlumni.R;


import java.util.List;

public class WallAdapter extends RecyclerView.Adapter<WallAdapter.WallDisplayHolder> {

    WallPojo wallPojo;
    Context context;
    List<WallPojo> wallPojoList;

    public WallAdapter(Context context, List<WallPojo> wallPojoList) {
        this.context = context;
        this.wallPojoList = wallPojoList;
    }

    @NonNull
    @Override
    public WallDisplayHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.wall_card,null);
        WallDisplayHolder wallDisplayHolder = new WallDisplayHolder(view);
        return wallDisplayHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull WallDisplayHolder holder, int position) {

    }


    @Override
    public int getItemCount() {
        return 0;
    }

    public class WallDisplayHolder extends RecyclerView.ViewHolder{

        CardView wallCard;
        TextView wallName,companyName,donateMoney;
        public WallDisplayHolder(View itemView) {
            super(itemView);

            wallCard = (CardView) itemView.findViewById(R.id.wallCard);
            wallName = (TextView) itemView.findViewById(R.id.wallName);
            companyName = (TextView) itemView.findViewById(R.id.companyName);
            donateMoney = (TextView) itemView.findViewById(R.id.donateMoney);
        }
    }
}
