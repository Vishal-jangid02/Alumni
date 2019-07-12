package com.example.vishal.GecaAlumni.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.vishal.GecaAlumni.Adapter.MessageAdapter;
import com.example.vishal.GecaAlumni.Pojo.MessagePojo;
import com.example.vishal.GecaAlumni.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class ChatWindow extends AppCompatActivity {


    ListView list_msg;
    //EditText msg_type;
    Button btn_sendmsg;
    boolean myMessage=true;
    List<ChatBubble> ChatBubbles;
    ArrayAdapter<ChatBubble> arrayAdapter;
    RequestParams requestParams;
    SimpleDateFormat sdf;
    Date d;
    SharedPreferences sharedPreferences;
    String sender="",email="";

    AsyncHttpClient client ;
    String url= "http://192.168.43.215:8084/RESTDEMO/EmailVerification.do";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);

        ChatBubbles = new ArrayList<>();

        list_msg = (ListView) findViewById(R.id.list_msg);
        //msg_type = (EditText) findViewById(R.id.msg_type);
        btn_sendmsg = (Button) findViewById(R.id.btn_sendmsg);

        //set listview adapter
        arrayAdapter = new MessageAdapter(this,R.layout.left_chat,ChatBubbles);
        list_msg.setAdapter(arrayAdapter);
        arrayAdapter.notifyDataSetChanged();

        sharedPreferences = getSharedPreferences("user",0);
        sender = sharedPreferences.getString("email","");

        Intent intent=getIntent();
       email =  intent.getStringExtra("email");
       ArrayList<MessagePojo> arrayList = ChatList.hashMap.get(email);

       for(MessagePojo m : arrayList) {
           String message = m.getMessage();
           Long time = m.getTimestamp();
           sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm");
           d = new Date(time);
           message = message + "\n" + sdf.format(d);
           if(m.getSender().equals(sender)){
               myMessage = true;
           }else
               myMessage = false;
           Log.e("123456789", "onCreate: " + message + " flag :: " + myMessage );
           message = m.getSender() + " : " +message;
           ChatBubble chatBubble = new ChatBubble(message, myMessage);
           ChatBubbles.add(chatBubble);

       }


        btn_sendmsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent1 = new Intent(ChatWindow.this,FirstSendMsg.class);
                intent1.putExtra("receiver",email);
                startActivity(intent1);
                finish();



                /*if(msg_type.getText().toString().trim().equals("")){

                    Toast.makeText(ChatWindow.this,"Please input some text",Toast.LENGTH_LONG).show();
                }else {

                    requestParams = new RequestParams();
                    requestParams.put("sender",sender);
                    requestParams.put("receiver",receiver);
                    requestParams.put("message",msg_type);

                    ChatBubble chatBubble = new ChatBubble(msg_type.getText().toString(),myMessage);
                    ChatBubbles.add(chatBubble);
                    arrayAdapter.notifyDataSetChanged();
                    msg_type.setText("");
                }*/
            }
        });
    }
}
