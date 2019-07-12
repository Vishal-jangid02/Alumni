package com.example.vishal.GecaAlumni.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.vishal.GecaAlumni.Activity.ChatBubble;
import com.example.vishal.GecaAlumni.R;
import com.example.vishal.GecaAlumni.ViewHolder.ViewAdapter;

import java.util.List;

public class MessageAdapter extends ArrayAdapter<ChatBubble> {


    Activity activity;
    List<ChatBubble> message;
    public MessageAdapter(Activity  context, int resource, List<ChatBubble> object) {
        super(context, resource, object);

        this.activity = context;
        this.message = object;
    }

    @Override
    public View getView(int position,  View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        int layoutResource=0;
        ChatBubble chatBubble = getItem(position);
        Log.e("7788", "getView: "+chatBubble.getContent()+chatBubble.myMessage());
        //int viewType =getItemViewType(position);
//0x7f0b0045
        //59 right
        if(chatBubble.myMessage()){

            layoutResource = R.layout.right_chat;
        }else {

            layoutResource = R.layout.left_chat;
        }

        if(convertView !=null){
            viewHolder = (ViewHolder) convertView.getTag();
        }else{

            convertView = inflater.inflate(layoutResource,parent,false);
           viewHolder = new ViewHolder(convertView);
           convertView.setTag(viewHolder);
        }

        viewHolder.msg.setText(chatBubble.getContent());
        return convertView;
    }

    @Override
    public int getViewTypeCount() {
        // return the total number of view types. this value should never change
        // at runtime. Value 2 is returned because of left and right views.
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        // return a value between 0 and (getViewTypeCount - 1)
        return position % 2;
    }


    class ViewHolder{

        TextView msg;

        public ViewHolder(View v){
            msg = (TextView) v.findViewById(R.id.txt_msg);
        }
    }
}
