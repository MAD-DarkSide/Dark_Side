package com.example.madproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {

    TextView textview;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        mAuth = FirebaseAuth.getInstance();

//        textview = (TextView)findViewById(R.id.textView2);
//        textview.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(login.this,register.class);
//                startActivity(intent);
//
//                Toast.makeText(login.this,"Transfer to register page",Toast.LENGTH_LONG).show();
//            }
//        });

        TextView userEmail = (TextView) findViewById(R.id.userEmail);
        TextView password = (TextView) findViewById(R.id.password);

        password = (EditText) findViewById(R.id.password);
        userEmail = (EditText) findViewById(R.id.userEmail);


        Button loginbtn = (Button) findViewById(R.id.loginButton);
        loginbtn.setOnClickListener(this::onClick);

//        loginbtn.setOnClickListener(new View.OnClickListener() {
//
//        };

    }
    public void onClick(View v){

        switch (v.getId()){
            case R.id.textView2:
                startActivity(new Intent(this, register.class));
                Toast.makeText(login.this, "Navigating to Registration page", Toast.LENGTH_LONG).show();

                break;
            case R.id.registerButton:
                //login();
                break;
        }

    }

//    public void login(){
//
//        String useremail = userEmail.
//        String companyname = editTextTextCompanyName.getText().toString().trim();
//        String userphone = editTextPhone.getText().toString().trim();
//        String password = pass.getText().toString().trim();
//    }
}
















