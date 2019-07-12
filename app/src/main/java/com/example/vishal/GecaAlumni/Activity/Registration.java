package com.example.vishal.GecaAlumni.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.vishal.GecaAlumni.Pojo.RegPojo;
import com.example.vishal.GecaAlumni.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import cz.msebera.android.httpclient.Header;

public class Registration extends AppCompatActivity {

     Spinner spinnerCourse,spinnerBranch,spinnerStart,spinnerEnd;
     EditText Name,Email,Pass,ConfirmPass,MobileNumber,Position,Address,Organization;
     Button btnReg,btnRegLogin;
     ArrayList<String> errors,branchList;
     ArrayList<String> yearList;

    ArrayList<String> abc=new ArrayList<String>();

    ProgressDialog progressDialog;
     RequestParams params,params1;
     AsyncHttpClient client, client1 ;
     String url = "http://192.168.43.camera215:8084/RESTDEMO/Register.do";
     String url1= "http://192.168.43.215:8084/RESTDEMO/EmailVerification.do";
     HashMap<String,ArrayList<String>> branchMap=new HashMap<>();
     String name=" ",email=" ",pass=" ",confirmpass=" ",mobilenumber=" ",position=" ",address=" ",organization=" ";

     RegPojo regPojo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        Name=(EditText) findViewById(R.id.Name);
        Email=(EditText) findViewById(R.id.Email);
        Pass=(EditText) findViewById(R.id.Pass);
        ConfirmPass=(EditText) findViewById(R.id.ConfirmPass);
        Address=(EditText) findViewById(R.id.Address);
        //ClgId=(EditText) findViewById(R.id.ClgId);
        MobileNumber=(EditText) findViewById(R.id.MobileNumber);
        Organization = (EditText) findViewById(R.id.Organization);
        Position=(EditText) findViewById(R.id.Position);
        spinnerCourse=(Spinner) findViewById(R.id.spinnerCourse);
        spinnerBranch =(Spinner) findViewById(R.id.spinnerBranch);
        spinnerStart =(Spinner) findViewById(R.id.spinnerStart);
        spinnerEnd=(Spinner) findViewById(R.id.spinnerEnd);

        errors=new ArrayList<>();
        regPojo=new RegPojo();

        btnReg=(Button) findViewById(R.id.btnReg);
        btnRegLogin=(Button) findViewById(R.id.btnRegLogin);

        //spinner select course
        branchList=new ArrayList<>();
        branchList.add("First Select Course");
        branchMap.put("Select Course",branchList);

        //select branch
        branchList=new ArrayList<>();
        branchList.add("Select Branch");
        branchList.add("Computer Science");

        branchList.add("Information Technology");
        branchList.add("Electrical");
        branchList.add("Electronics");
        branchList.add("Mechanical");
        branchList.add("Electrical Instrumentation ControlSystem");
        branchList.add("Civil");

        branchMap.put("B.Tech",branchList);

        //select mba
        branchList=new ArrayList<>();
        branchList.add("Select Branch");
        branchList.add("Finance");
        branchList.add("HRM");
        branchList.add("Marketing");
        branchList.add("ITM");

        branchMap.put("MBA",branchList);

        //select m.tech
        branchList=new ArrayList<>();
        branchList.add("Select Branch");
        branchList.add("CSE");
        branchList.add("DC");
        branchList.add("SE");
        branchList.add("Production Engineering");
        branchList.add("IT");

        branchMap.put("M.Tech",branchList);

        //select mca
        branchList=new ArrayList<>();
        branchList.add("N/A");
        branchMap.put("MCA",branchList);

        //select year period

        abc.add("End Year");
        yearList=new ArrayList<>();
        yearList.add("Start Year");
        for(int i=1997;i<=2015;i++){
            yearList.add(i+"");
        }

        /*auth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("Registration");
*/
        btnRegLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Registration.this,Login.class);
                startActivity(intent);
                finish();
            }
        });

        /*final Spinner spinnerBranch=(Spinner) findViewById(R.id.spinnerBranch);*/

        //course arrayAdapter
        final ArrayAdapter<CharSequence> arrayAdapter= ArrayAdapter.createFromResource(this,R.array.SelectCourse,R.layout.support_simple_spinner_dropdown_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCourse.setAdapter(arrayAdapter);

        spinnerCourse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

                String selecteditem=parent.getItemAtPosition(pos).toString();
                ArrayAdapter<String> arrayAdapter1=new ArrayAdapter<String>(Registration.this, R.layout.support_simple_spinner_dropdown_item,branchMap.get(selecteditem));
                spinnerBranch.setAdapter(arrayAdapter1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //select year
        ArrayAdapter<String> arrayAdapter2=new ArrayAdapter<>(Registration.this,R.layout.support_simple_spinner_dropdown_item,yearList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerStart.setAdapter(arrayAdapter2);

        spinnerStart.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String s=parent.getItemAtPosition(position).toString();
                abc = new ArrayList<>();

                if(s.equals("Start Year")){
                    abc.add("End Year");
                }
                else {
                    Integer selecteditem = Integer.parseInt(s);
                    abc.add("End Year");
                    abc.add((selecteditem + 1) + "");
                    abc.add((selecteditem + 2) + "");
                    abc.add((selecteditem + 3) + "");
                    abc.add((selecteditem + 4) + "");

                }
                    ArrayAdapter<String> arrayAdapter3 = new ArrayAdapter<String>(Registration.this, R.layout.support_simple_spinner_dropdown_item, abc);
                    spinnerEnd.setAdapter(arrayAdapter3);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Registration Button
        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("Entry in btnreg", name);
                name=Name.getText().toString();
                email=Email.getText().toString();
                pass=Pass.getText().toString();
                confirmpass=ConfirmPass.getText().toString();
                organization=Organization.getText().toString();
               // clgid=ClgId.getText().toString();
                position=Position.getText().toString();
                address=Address.getText().toString();
                mobilenumber=MobileNumber.getText().toString();

                Log.d("Entry in btnreg", name);

                /*Name Validation*/
                if(name.length()==0){
                    errors.add("name");
                    Name.requestFocus();
                    Name.setError("FIELD CANNOT BE EMPTY");
                }
                else if(!name.matches("[a-zA-Z ]+")){
                    errors.add("name");
                    Name.requestFocus();
                    Name.setError("ENTER ONLY ALPHABETICAL CHARACTER");
                }
                else if(name.length()<5 && name.length()<20){
                    errors.add("name");
                    Name.requestFocus();
                    Name.setError("ENTER MIN. 10 CHARACTER");
                }

                /*Email Validation*/
                else if(email.length()==0){
                    errors.add("email");
                    Email.requestFocus();
                    Email.setError("FIELD CANNOT BE EMPTY");
                }
               /* else if(!email.matches("[0-9a-zA-Z.]")){
                    errors.add("email");
                    Email.requestFocus();
                    Email.setError("ENTER CORRECT EMAIL");
                }*/

                //spinner course validation
                else if(spinnerCourse.getSelectedItem().toString().equals("Select Course")){
                    errors.add("course");
                    Toast.makeText(Registration.this,"Pls select course",Toast.LENGTH_LONG).show();
                }

                //spinner year validation
                else if(spinnerStart.getSelectedItem().toString().equals("Start Year")){
                    errors.add("spinner year");
                    Toast.makeText(Registration.this,"Pls select year",Toast.LENGTH_LONG).show();
                }

                //spinner endyear validation
                else if(spinnerEnd.getSelectedItem().toString().equals("End Year")){
                    errors.add("spinner endyear");
                    Toast.makeText(Registration.this,"Pls select endyear",Toast.LENGTH_LONG).show();
                }

                /*Branch Validation*/
                else if(spinnerBranch.getSelectedItem().toString().equals("Choose Branch")){
                    errors.add("spinner");
                   Toast.makeText(Registration.this,"Pls select branch",Toast.LENGTH_LONG).show();
                }

                /*MobileNumber validation*/
                else  if(mobilenumber.length()==0){
                    errors.add("mobilenumber");
                    MobileNumber.requestFocus();
                    MobileNumber.setError("FIELD CANNOT BE EMPTY");
                }

                else if(mobilenumber.length()<=13  && mobilenumber.length()>=15){
                    errors.add("mobilenumber");
                    MobileNumber.requestFocus();
                    MobileNumber.setError("ENTER VALID NUMBER");
                }

                /*CollegeId Validation*/
                /*else if(clgid.length()==0){
                    errors.add("clgid");
                    ClgId.requestFocus();
                    ClgId.setError("FIELD CANNOT BE EMPTY");
                }*/

                //Postion Validation
                else if(position.length()==0){
                    errors.add("position");
                    Position.requestFocus();
                    Position.setError("FIELD CANNOT BE EMPTY");
                }

                //Address Validation
                else if(address.length()==0){
                    errors.add("address");
                    Address.requestFocus();
                    Address.setError("FIELD CANNOT BE EMPTY");
                }


                /*Password Validation*/
                else if(pass.length()==0){
                    errors.add("pass");
                    Pass.requestFocus();
                    Pass.setError("FIELD CANNOT BE EMPTY");
                }
                else if(!pass.matches("[0-9a-zA-Z@.]+")){
                    errors.add("pass");
                    Pass.requestFocus();
                    Pass.setError("ENTER CORRECT PASSWORD");
                }
                else if(pass.length()<8){
                    errors.add("pass");
                    Pass.requestFocus();
                    Pass.setError("ENTER MiN 8 CHARACTER");
                }

                else if(!confirmpass.equals(pass)){
                    errors.add("confirmpass");
                    ConfirmPass.requestFocus();
                    ConfirmPass.setError("CHECK YOUR PASSWORD");
                }

                Log.d("Entry in btnreg", name);

                if(errors.isEmpty()){

                    Log.e("123456", "onClick: else" );
                    //Toast.makeText(Registration.this,"Successsful",Toast.LENGTH_LONG).show();
                    saveData();
                }
                else
                {
                    Log.d("Entry in btnreg", name);
                }
            }

        });
    }

    //Call saveData Function
    public void saveData(){
        //New Code

        Log.d("Entered SaveData", "Aa gya: ");
        params = new RequestParams();
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

        params1 = new RequestParams();
        params1.put("recipient", email);
        params1.put("type","reg");
        client = new AsyncHttpClient();
        client1 = new AsyncHttpClient();
        Log.d("Entered SaveData", "Aa gya: ");


        client.get(url, params, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.d("GotResponseSuccess", response.toString());
                Toast.makeText(Registration.this, response.toString(), Toast.LENGTH_LONG);

                progressDialog = new ProgressDialog(Registration.this);
                progressDialog.setMessage("Please wait..!");
                progressDialog.setTitle("Registration");
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.show();

                if(response.toString().contains("Yes")){
                    Intent intent=new Intent(Registration.this,Login.class);
                    startActivity(intent);
                    finish();

                }
                else
                {
                    Toast.makeText(Registration.this,"Allready Registered",Toast.LENGTH_LONG);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);

                Log.d("GotResponseFailure", "Failed");
                Toast.makeText(Registration.this, "Failed", Toast.LENGTH_LONG);
            }
        });

        Log.d("Entered SaveData", "Aa gya: ");


        client1.get(url1, params1, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.d("GotResponseSuccess", response.toString());
                Toast.makeText(Registration.this, response.toString(), Toast.LENGTH_LONG);

                progressDialog = new ProgressDialog(Registration.this);
                progressDialog.setMessage("Please wait..!");
                progressDialog.setTitle("Registration");
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.show();

                if(response.toString().contains("Success")){
                    Intent intent=new Intent(Registration.this,Login.class);
                    startActivity(intent);
                    finish();

                }
                else
                {
                    Toast.makeText(Registration.this,"Email Already Registered !!",Toast.LENGTH_LONG);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);

                Log.d("GotResponseFailure", "Failed");
                Toast.makeText(Registration.this, "Failed", Toast.LENGTH_LONG);
            }
        });
    }
}
