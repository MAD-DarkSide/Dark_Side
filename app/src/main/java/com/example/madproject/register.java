package com.example.madproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class register extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth mAuth;
    private EditText editTextTextEmailAddress2,editTextTextCompanyName,editTextPhone,editTextTextPassword;
    private TextView banner;
    private TextView textView, registerButton;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);

        mAuth = FirebaseAuth.getInstance();
        textView = (TextView) findViewById(R.id.textView);
        textView.setOnClickListener(this);

        registerButton = (Button) findViewById(R.id.registerButton);

        registerButton.setOnClickListener(this);


        editTextTextEmailAddress2 = (EditText) findViewById(R.id.userEmail);
        editTextTextCompanyName = (EditText) findViewById(R.id.companyName);
        editTextPhone = (EditText) findViewById(R.id.userPhone);
        editTextTextPassword = (EditText) findViewById(R.id.password);

        //progressBar = (ProgressBar) findViewById(R.id.progressBar);




//        textView = (TextView)findViewById(R.id.textView);
//        textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(register.this,login.class);
//                startActivity(intent);
//
//                Toast.makeText(register.this,"Transfer to the login page",Toast.LENGTH_LONG).show();
//            }
//        });


    }

    @Override
    public void onClick(View v){

        switch (v.getId()){
            case R.id.textView:
                startActivity(new Intent(this, login.class));
                break;
            case R.id.registerButton:
                register();
                break;
        }

    }

    private void register(){
        String useremail = editTextTextEmailAddress2.getText().toString().trim();
        String companyname = editTextTextCompanyName.getText().toString().trim();
        String userphone = editTextPhone.getText().toString().trim();
        String password = editTextTextPassword.getText().toString().trim();

        if (useremail.isEmpty()){
            editTextTextEmailAddress2.setError("Email is Required");
            editTextTextEmailAddress2.requestFocus();
            return;

        }
        if (!Patterns.EMAIL_ADDRESS.matcher(useremail).matches()){
            editTextTextEmailAddress2.setError("Invalid Email!");
            editTextTextEmailAddress2.requestFocus();
            return;
        }
        if (companyname.isEmpty()){
            editTextTextCompanyName.setError("Company Name is Required");
            editTextTextCompanyName.requestFocus();
            return;

        }
        if (userphone.isEmpty()){
            editTextPhone.setError("Phone is Required");
            editTextPhone.requestFocus();
            return;

        }
        if (password.isEmpty()){
            editTextTextPassword.setError("Password is Required");
            editTextTextPassword.requestFocus();
            return;

        }
        if(password.length() < 6){
            editTextTextPassword.setError("Minimum password length is 6 characters!");
            editTextTextPassword.requestFocus();
            return;
        }
        //progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(useremail, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            User user = new User(useremail, companyname, userphone);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()){
                                                Toast.makeText(register.this, "User has been Registered", Toast.LENGTH_LONG).show();
                                                //progressBar.setVisibility(View.GONE);

                                            }
                                            else {
                                                Toast.makeText(register.this, "Registration failed... Re-enter details", Toast.LENGTH_LONG).show();
                                                //progressBar.setVisibility(View.GONE);
                                            }
                                        }
                                    });


                        }
                        else {
                            Toast.makeText(register.this, "Registration failed", Toast.LENGTH_LONG).show();
                            //progressBar.setVisibility(View.GONE);
                        }
                    }
                });

    }
}