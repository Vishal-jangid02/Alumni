package com.example.vishal.GecaAlumni.Activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.net.http.RequestQueue;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.vishal.GecaAlumni.Pojo.RegPojo;
import com.example.vishal.GecaAlumni.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.squareup.picasso.Picasso;

import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.NameValuePair;

public class EditProfile extends AppCompatActivity {

    ImageView editProImage;
    EditText editProNumber, editProAddress, editProOrganization, editProPosition;
    Button editBtnSubmit;
    Uri uri;
    Bitmap bitmap;
    SharedPreferences sp;
  /*  FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;*/
    String key;
    String MobileNumber=" ",Img=" ",Organization=" ",Position=" ",Address=" ",Name=" ",Clgid=" ",Course=" ",Branch=" ",Email=" ",InfoText=" ";

    RequestParams params ;
    AsyncHttpClient client ;

    ProgressDialog progressDialog;

    String url ="http://192.168.43.215:8084/RESTDEMO/UploadImg.do";

    RegPojo regPojo;
   /* String imageUrl = "https://firebasestorage.googleapis.com/v0/b/gecaalumni-b9384.appspot.com/o/imae.jpg?alt=media&token=76d3d794-25be-4925-9dc6-e5832d07f399";*/

    String number = " ", address = " ", organization = " ", position = " ";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        editProImage = (ImageView) findViewById(R.id.editProImage);
        editProNumber = (EditText) findViewById(R.id.editProNumber);
        editProAddress = (EditText) findViewById(R.id.editProAddress);
        editProOrganization = (EditText) findViewById(R.id.editProOrganization);
        editProPosition = (EditText) findViewById(R.id.editProPosition);
        editBtnSubmit = (Button) findViewById(R.id.editBtnSubmit);
      /*  firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Registration");*/


       /* editProImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, 0);
            }
        });//uploadImage() close...........*/

        sp = getSharedPreferences("user", 0);
        Boolean flag = sp.getBoolean("flag", false);

        Name = sp.getString("name", "");
        Email = sp.getString("email", "");
        MobileNumber = sp.getString("mobno", "");
      //  Clgid = sp.getString("clgid", "");
        Branch = sp.getString("branch", "");
        Course = sp.getString("course", "");
        Img = sp.getString("imageUrl", "");
        Organization = sp.getString("prescomp", "");
        Position = sp.getString("prespos", "");
        Address = sp.getString("address", "");
         InfoText = sp.getString("infoText", "");

        Log.e("123456", "onCreate: "+Organization+"pos :"+Position +"Address : "+Address );


        if (flag) {

            //Picasso.get().load(Img).into(editProImage);
            editProNumber.setText(MobileNumber);
            editProAddress.setText(Address);
            editProPosition.setText(Position);
            editProOrganization.setText(Organization);

            editProImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(EditProfile.this);
                    builder.setTitle("Select Image : ");
                    builder.setPositiveButton("Take Camera", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(intent, 1);
                        }
                    });

                    builder.setNegativeButton("Gallery", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent();
                            intent.setType("image/*");
                            intent.setAction(Intent.ACTION_GET_CONTENT);
                            startActivityForResult(intent, 1);
                        }
                    });

                    builder.show();
                }
            });

            editBtnSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Bitmap bitmapImage = ((BitmapDrawable) editProImage.getDrawable()).getBitmap();
                    saveImage(bitmapImage);

                }

            });
        }

        else {
            //Picasso.get().load(Img).into(editProImage);
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            uri = data.getData();
            try {
                /*//b2 = MediaStore.Images.Media.getBitmap(getContentResolver(),uriuploadimage);
                editProImage.setImageURI(uri);*/
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                editProImage.setImageBitmap(bitmap);
            }//end of try
            catch (Exception e) {
                e.printStackTrace();
            }//end of catch()...

        }//end of if....

    }

/*public void saveData(Bitmap image)
    {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 70, byteArrayOutputStream);
        String encodedImage = Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);
        Log.e("doin back", "12345");

        params = new RequestParams();
        params.put("uploadImage", encodedImage);
        params.put("mobile", "7877414332");
        client = new AsyncHttpClient();
        client.post(url, params, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                Log.e("success", "12345");

                if (response.toString().contains("Yes")) {
                    Toast.makeText(EditProfile.this, "Image Upload", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Log.e("failure", "12345");

                Toast.makeText(EditProfile.this, "Error", Toast.LENGTH_LONG).show();
            }

        });


    }*/

   //Function for save image in PC
    public void saveImage(Bitmap image){


        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        final String encodedImage = Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);

        Log.e("12345", encodedImage);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        Log.e("1122", "getresponse:"+s);
                        //Disimissing the progress dialog
                        //Showing toast message of the response
                        //editProImage.setImageBitmap(decodedByte);

                        Toast.makeText(EditProfile.this, s.toString() , Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //Dismissing the progress dialog
                        Log.e("11278", "geterror:"+encodedImage);

                        //Showing toast
                        Toast.makeText(EditProfile.this, ""+volleyError, Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //Converting Bitmap to Stri
                //Getting Image Name
                //String name = editTextName.getText().toString().trim();
                //Creating parameters
                Map<String,String> params = new Hashtable<String, String>();

                params.put("mobno",editProNumber.getText().toString());
                params.put("address",editProAddress.getText().toString());
                params.put("prescomp",editProOrganization.getText().toString());
                params.put("prespos",editProPosition.getText().toString());

                params.put("image", encodedImage );
                params.put("email", sp.getString("email",""));
                Log.e("1122", "getParams:"+encodedImage);
                return params;
            }
        };

        //Creating a Request Queue
        com.android.volley.RequestQueue requestQueue = Volley.newRequestQueue(this);

        //Adding request to the queue
        requestQueue.add(stringRequest);

    }
/*
    byte[] decodedString = Base64.decode(encodedImage, Base64.DEFAULT);
    Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);*/

}
