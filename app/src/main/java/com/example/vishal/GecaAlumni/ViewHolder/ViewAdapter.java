package com.example.vishal.GecaAlumni.ViewHolder;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vishal.GecaAlumni.Activity.MemberPro;
import com.example.vishal.GecaAlumni.Pojo.RegPojo;
import com.example.vishal.GecaAlumni.R;

import java.util.ArrayList;
import java.util.List;

public class ViewAdapter extends RecyclerView.Adapter<ViewAdapter.DisplayViewHolder> implements Filterable{

    RegPojo regPojo;
    Context context;
    List<RegPojo> list;
    List<RegPojo> listfull;

    public ViewAdapter(Context context, List<RegPojo> list) {
        this.context=context;
        this.list=list;
        listfull = new ArrayList<>(list);
    }

    @NonNull
    @Override
    public DisplayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.member_card,null);
        DisplayViewHolder displayViewHolder = new DisplayViewHolder(view);

        return displayViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DisplayViewHolder holder, final int position) {

        regPojo = list.get(position);

        holder.username.setText(regPojo.getName());
        holder.branchname.setText(regPojo.getBranch());
        holder.coursename.setText(regPojo.getCourse());

        holder.Card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                regPojo = new RegPojo();
                regPojo = list.get(position);
                Intent intent = new Intent(context,MemberPro.class);
                intent.putExtra("fullname",regPojo.getName());
                intent.putExtra("email",regPojo.getEmail());
                intent.putExtra("mobno",regPojo.getMobilenumber());
                intent.putExtra("course",regPojo.getCourse());
                intent.putExtra("branch",regPojo.getBranch());
                intent.putExtra("startyear",regPojo.getYear());
                intent.putExtra("endyear",regPojo.getEndyear());
                intent.putExtra("prescomp",regPojo.getPrescomp());
                intent.putExtra("prespos",regPojo.getPosition());
                intent.putExtra("address",regPojo.getAddress());
                context.startActivity(intent);


               /* regPojo.setName(js.get("fullname").toString());
                regPojo.setEmail(js.get("email").toString());
                regPojo.setMobilenumber(js.get("mobno").toString()) ;
                regPojo.setCourse(js.get("course").toString());
                regPojo.setBranch(js.get("branch").toString());
                regPojo.setPrescomp(js.get("prescomp").toString());
                regPojo.setPosition(js.get("prespos").toString());
                regPojo.setYear(js.get("startyear").toString());
                regPojo.setEndyear(js.get("endyear").toString());
                regPojo.setAddress(js.get("address").toString());*/

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    @Override
    public Filter getFilter() {
        return exampleFilter;
    }
    Filter exampleFilter = new Filter() {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            List<RegPojo> filterList = new ArrayList<>();

            if(constraint == null || constraint.length() == 0){
                filterList.addAll(listfull);
            }
            else {

                String filterPattern = constraint.toString().toLowerCase().trim();

                for(RegPojo item : listfull){

                    if(item.getName().toLowerCase().contains(filterPattern)){

                        filterList.add(item);
                    }
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = filterList;
            return  filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults filtereresults) {

            list.clear();
            list.addAll((List) filtereresults.values);
            notifyDataSetChanged();
        }
    };

    public class DisplayViewHolder extends RecyclerView.ViewHolder {

        CardView Card;
        public TextView username, coursename, branchname;
        public ImageView MemberImage;


        public DisplayViewHolder(View itemView) {
            super(itemView);

            Card = itemView.findViewById(R.id.card);
            //MemberImage = (ImageView) itemView.findViewById(R.id.MemberImage);
            username = (TextView) itemView.findViewById(R.id.username);
            coursename = (TextView) itemView.findViewById(R.id.coursename);
            branchname = (TextView) itemView.findViewById(R.id.branchname);


        }
    }


}