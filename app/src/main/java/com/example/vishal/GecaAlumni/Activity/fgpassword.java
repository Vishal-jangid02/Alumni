package com.example.vishal.GecaAlumni.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vishal.GecaAlumni.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class fgpassword extends AppCompatActivity {
    private EditText passwordemail;
    private Button resetpassword;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fgpassword);
        passwordemail = (EditText) findViewById(R.id.passemail);
        resetpassword = (Button) findViewById(R.id.resetpass);
        mAuth = FirebaseAuth.getInstance();
        resetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String useremail = passwordemail.getText().toString().trim();
                if (useremail.equals("")) {
                    Toast.makeText(fgpassword.this, "Please enter your registered email", Toast.LENGTH_SHORT).show();
                } else {
                    mAuth.sendPasswordResetEmail(useremail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful()) {
                                Toast.makeText(fgpassword.this, "Please check your email account", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(fgpassword.this, Login.class));
                            } else {
                                String message = task.getException().getMessage();
                                Toast.makeText(fgpassword.this, "Something Error" + message, Toast.LENGTH_LONG).show();
                            }


                        }
                    });
                }
            }
        });
    }
}
