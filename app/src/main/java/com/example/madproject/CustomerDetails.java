package com.example.madproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.UUID;

public class CustomerDetails extends AppCompatActivity {
    EditText edtName, edtEmail,edtPhone;
    Button savebtn, viewbtn;
    FirebaseFirestore db;
    private String uName, uEmail, uPhone,uId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_details);

        edtName = findViewById(R.id.name_edit_txt);
        edtEmail = findViewById(R.id.email_edit_txt);
        edtPhone = findViewById(R.id.phone_edit_txt);

        savebtn = findViewById(R.id.save_btn);
        viewbtn = findViewById(R.id.view_btn);

        db = FirebaseFirestore.getInstance();

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            savebtn.setText("Update");
            uId = bundle.getString("uId");
            uName = bundle.getString("uName");
            uEmail = bundle.getString("uEmail");
            uPhone = bundle.getString("uPhone");

            edtName.setText(uName);
            edtEmail.setText(uEmail);
            edtPhone.setText(uPhone);
        }else{
            savebtn.setText("Save");
        }

        viewbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CustomerDetails.this,ViewCustomer.class));
            }
        });

        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtName.getText().toString();
                String email = edtEmail.getText().toString();
                String phone = edtPhone.getText().toString();

                Bundle bundle1 = getIntent().getExtras();
                if(bundle1 != null){
                    String id = uId;
                    updateToFirestore(id, name, email,phone);
                }else{
                    String id = UUID.randomUUID().toString();
                    savetoFirestore(id,name,email,phone);
                }
            }
        });
    }

    private void updateToFirestore(String id, String name, String email, String phone){
        db.collection("Customer").document(id).update("name",name,"email",email,"phone",phone)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(CustomerDetails.this, "Data updated", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(CustomerDetails.this, "Error :"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(CustomerDetails.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void savetoFirestore(String id, String name, String email, String phone){
        if(!name.isEmpty() && !email.isEmpty()){
            HashMap<String,Object> map = new HashMap<>();
            map.put("id",id);
            map.put("name",name);
            map.put("email",email);
            map.put("phone",phone);

            db.collection("Customer").document(id).set(map)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful())
                                Toast.makeText(CustomerDetails.this, "Data saved", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(CustomerDetails.this, "Failed", Toast.LENGTH_SHORT).show();
                        }
                    });

        }else
            Toast.makeText(this, "Empty fields are not allowed", Toast.LENGTH_SHORT).show();
    }
}