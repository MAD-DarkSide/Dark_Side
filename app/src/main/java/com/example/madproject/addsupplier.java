package com.example.madproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;

import org.w3c.dom.Document;

import java.sql.Timestamp;

public class addsupplier extends AppCompatActivity {

    EditText email,name,phone,des;
    Button savebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addsupplier);

        email = findViewById(R.id.supplierEmail);
        name = findViewById(R.id.supplierName);
        phone = findViewById(R.id.supplierPhone);
        des = findViewById(R.id.supplierDes);

        savebtn.setOnClickListener((v)-> saveSupplier());
    }

    private void saveSupplier() {
        String supName = email.getText().toString();
        String supEmail = name.getText().toString();
        String supPhone = phone.getText().toString();
        String supDes = des.getText().toString();




        if(supName == null || supName.isEmpty()){
            name.setError("Supplier Name is required....");
            return;
        }
        if(supEmail == null || supEmail.isEmpty()){
            name.setError("Supplier email is required....");
            return;
        }
        if(supPhone == null || supPhone.isEmpty()){
            name.setError("Supplier phone is required....");
            return;
        }

        Supplier supplier = new Supplier();
        supplier.setName(supName);
        supplier.setEmail(supEmail);
        supplier.setPhone(supPhone);
        supplier.setDescription(supDes);
        //supplier.setRegtime(Timestamp.);

        saveSupplierToFirebase(supplier);
    }

    void saveSupplierToFirebase(Supplier supplier){

        DocumentReference documentReference;
        documentReference = Utility.getCollectionReferenceForSupplier().document();

        documentReference.set(supplier).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(addsupplier.this,"Supplier added....",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(addsupplier.this, supplierview.class));
                    finish();
                }
                else{
                    Toast.makeText(addsupplier.this,"Adding failed....",Toast.LENGTH_LONG).show();
                }
            }
        });


    }
}







