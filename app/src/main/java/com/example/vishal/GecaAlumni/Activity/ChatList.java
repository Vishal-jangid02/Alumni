package com.example.vishal.GecaAlumni.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.vishal.GecaAlumni.Pojo.MessagePojo;
import com.example.vishal.GecaAlumni.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.client.cache.Resource;

public class ChatList extends AppCompatActivity {

    ListView List_Chat;
    Button btn_FindPeople;

    RequestParams requestParams;
    AsyncHttpClient client;
    SharedPreferences sp;
    MessagePojo messagePojo;

    ArrayList<MessagePojo> arrayList;
    ArrayList<String> keys;

    public static HashMap<String,ArrayList<MessagePojo>> hashMap =new HashMap<>();
    JSONObject jsonObject;

    String url= "http://192.168.43.215:8084/RESTDEMO/Message.do";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_list);

        final ListView List_Chat = (ListView) findViewById(R.id.List_Chat);
        Button btn_FindPeople = (Button) findViewById(R.id.btn_FindPeople);

        arrayList = new ArrayList<>();
        keys = new ArrayList<>();

        sp= getSharedPreferences("user",0);
        final String email = sp.getString("email","");

        btn_FindPeople.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               startActivity(new Intent(ChatList.this,AlumniCategory.class));

            }
        });

        requestParams = new RequestParams();
        requestParams.put("email",email);

        client = new AsyncHttpClient();
        Log.e("123", "send: " );
        client.get(url, requestParams, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);

                Log.d("GotResponseSuccess", response.toString());


                Toast.makeText(ChatList.this, "Got Json Array", Toast.LENGTH_LONG).show();

                if (response !=null)
                {
                    hashMap = new HashMap<>();
                   for(int i=0;i<response.length();i++){

                       try {
                           jsonObject=response.getJSONObject(i);

                           messagePojo = new MessagePojo();
                           messagePojo.setMessage(jsonObject.getString("message"));
                           messagePojo.setSender(jsonObject.getString("sender"));
                           messagePojo.setTimestamp(jsonObject.getLong("timestamp"));

                           String sender = jsonObject.getString("sender");
                           String recevier = jsonObject.getString("receiver");

                           if(sender.equals(email)){
                               if(hashMap.containsKey(recevier)){

                                   arrayList = hashMap.get(recevier);
                                   arrayList.add(messagePojo);

                                   hashMap.remove(recevier);
                                   hashMap.put(recevier,arrayList);

                               }else {
                                   arrayList = new ArrayList<>();
                                   arrayList.add(messagePojo);
                                   hashMap.put(recevier,arrayList);
                               }
                           }else{
                               if(hashMap.containsKey(sender)){

                                   arrayList = hashMap.get(sender);
                                   arrayList.add(messagePojo);

                                   hashMap.remove(sender);
                                   hashMap.put(sender,arrayList);

                               }else {
                                   arrayList = new ArrayList<>();
                                   arrayList.add(messagePojo);
                                   hashMap.put(sender,arrayList);
                               }
                           }

                       } catch (JSONException e) {
                           e.printStackTrace();
                       }
                   }

                    Log.e("12345678", "hash map :: " + hashMap.toString());

                   for(String s:hashMap.keySet()){
                       keys.add(s);
                   }
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(ChatList.this,android.R.layout.simple_list_item_1,keys);
                   List_Chat.setAdapter(arrayAdapter);
                   List_Chat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                       @Override
                       public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                           String email = (String) parent.getAdapter().getItem(position);
                           Intent i = new Intent(ChatList.this,ChatWindow.class);
                           i.putExtra("email",email);
                           startActivity(i);

                       }
                   });
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);

                Log.d("GotResponseFailure", "Failed");
                Toast.makeText(ChatList.this, "Failed", Toast.LENGTH_LONG).show();
            }


        });

    }
}
