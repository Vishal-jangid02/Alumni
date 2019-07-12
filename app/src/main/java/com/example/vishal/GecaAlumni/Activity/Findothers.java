package com.example.vishal.GecaAlumni.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.vishal.GecaAlumni.Pojo.RegPojo;
import com.example.vishal.GecaAlumni.R;
import com.example.vishal.GecaAlumni.ViewHolder.MemViewHolder;
import com.example.vishal.GecaAlumni.ViewHolder.ViewAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.zip.Inflater;

import cz.msebera.android.httpclient.Header;

public class Findothers extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    ViewAdapter viewAdapter;

    RequestParams params ;
    RegPojo regPojo;
    ArrayList<RegPojo> arrayList;

    AsyncHttpClient client ;
    String url = "http://192.168.43.215:8084/RESTDEMO/AllUsers.do";

    JSONObject js ;

    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findothers);

       /* database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Registration");
*/
        recyclerView = (RecyclerView) findViewById(R.id.recycleFind);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        // adapter =new FirebaseRecyclerAdapter<RegPojo, MemViewHolder>();

        arrayList = new ArrayList<>();
        viewAdapter = new ViewAdapter(Findothers.this, arrayList);
        recyclerView.setAdapter(viewAdapter);
        client = new AsyncHttpClient();

        Intent intent=getIntent();
        String course=intent.getStringExtra("Course");
        String branch= intent.getStringExtra("Branch");
        String name=intent.getStringExtra("name");
        //Toast.makeText(this, "nameUser"+name.equals(""), Toast.LENGTH_SHORT).show();
        //Log.d("name12",name);
        String passingyear = intent.getStringExtra("PassingYear");

        if(course.equals("All Courses")){
            course="%";
            branch="%";
        }
        if(branch.equals("All Branches")){
            branch="%";
        }
        if(passingyear.equals("All Batches")){
            passingyear="%";
        }
        if(name.equals("")){
            name="%";
        }
        else {
            name="%"+name+"%";
        }
        params = new RequestParams();
        params.put("Course",course);
        params.put("Branch",branch);
        params.put("PassingYear",passingyear);
        params.put("name",name);

       /* params = new RequestParams();
        params.put("fullname",name);
        params.put("email",email);
        params.put("mobno",mobilenumber);
        //params.put("clgid",clgid);
        params.put("course",spinnerCourse.getSelectedItem().toString());
        params.put("branch",spinnerBranch.getSelectedItem().toString());
        params.put("startyear",spinnerStart.getSelectedItem().toString());
        params.put("endyear",spinnerEnd.getSelectedItem().toString());
        params.put("prescomp",organization);
        params.put("prespos",position);
        params.put("address",address);
        params.put("password",pass);
        client = new AsyncHttpClient();
*/

        client.get(url,params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                Log.d("12345", "onSuccess: 1");
                super.onSuccess(statusCode, headers, response);
                Log.d("GotResponseSuccess", response.toString());
                //Toast.makeText(Findothers.this, response.toString(), Toast.LENGTH_LONG);

                if (response != null) {
                    for (int i = 0; i < response.length(); i++) {
                        try {

                            js = response.getJSONObject(i);
                            regPojo = new RegPojo();

                            regPojo.setName(js.get("fullname").toString());
                            regPojo.setEmail(js.get("email").toString());
                            regPojo.setMobilenumber(js.get("mobno").toString()) ;
                            regPojo.setCourse(js.get("course").toString());
                            regPojo.setBranch(js.get("branch").toString());
                            regPojo.setPrescomp(js.get("prescomp").toString());
                            regPojo.setPosition(js.get("prespos").toString());
                            regPojo.setYear(js.get("startyear").toString());
                            regPojo.setEndyear(js.get("endyear").toString());
                            regPojo.setAddress(js.get("address").toString());
                            Log.d("12345", "Inside on Success ");
                            arrayList.add(regPojo);

                            viewAdapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    Toast.makeText(Findothers.this, "Please Wait", Toast.LENGTH_LONG);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);

                Log.d("GotResponseFailure", "Failed");
                Toast.makeText(Findothers.this, "Failed", Toast.LENGTH_LONG);
            }
        });




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

       getMenuInflater().inflate(R.menu.search_menu,menu);
        MenuItem item = menu.findItem(R.id.search_Member);
        SearchView searchView = (SearchView) item.getActionView();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                viewAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }
}
