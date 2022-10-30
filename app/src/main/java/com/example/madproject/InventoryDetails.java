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

public class InventoryDetails extends AppCompatActivity {

    EditText edtName, edtSupplier,edtQuantity,edtPrice,edtDescription;
    Button savebtn, viewbtn;
    FirebaseFirestore db;
    private String uName, uSupplier, uQuantity,uPrice,uDescription,uId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_details);

        edtName = findViewById(R.id.name_edit_ID);
        edtSupplier = findViewById(R.id.supplier_edit_txt);
        edtQuantity = findViewById(R.id.qty_edit_txt);
        edtPrice = findViewById(R.id.price);
        edtDescription = findViewById(R.id.des_edit_txt);

        savebtn = findViewById(R.id.save_btn);
        viewbtn = findViewById(R.id.view_btn);

        db = FirebaseFirestore.getInstance();

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            savebtn.setText("Update");
            uId = bundle.getString("uId");
            uName = bundle.getString("uName");
            uSupplier = bundle.getString("uSupplier");
            uQuantity = bundle.getString("uQuantity");
            uPrice = bundle.getString("uPrice");
            uDescription = bundle.getString("uDescription");

            edtName.setText(uName);
            edtSupplier.setText(uSupplier);
            edtQuantity.setText(uQuantity);
            edtPrice.setText(uPrice);
            edtDescription.setText(uDescription);
        }else{
            savebtn.setText("Save");
        }

        viewbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(InventoryDetails.this,ViewInventory.class));
            }
        });

        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtName.getText().toString();
                String supplier = edtSupplier.getText().toString();
                String qty = edtQuantity.getText().toString();
                String price = edtPrice.getText().toString();
                String descrip = edtDescription.getText().toString();

                Bundle bundle1 = getIntent().getExtras();
                if(bundle1 != null){
                    String id = uId;
                    updateToFirestore(id, name, supplier, qty, price, descrip);
                }else{
                    String id = UUID.randomUUID().toString();
                    savetoFirestore(id, name, supplier, qty, price, descrip);
                }
            }
        });
    }

    private void updateToFirestore(String id, String name, String Supplier, String qty, String price, String descrip){
        db.collection("Customer").document(id).update("name",name,"supplier",Supplier,"qty",qty,"Price",price,"Description",descrip)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(InventoryDetails.this, "Entry updated", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(InventoryDetails.this, "Not Updated :"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(InventoryDetails.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void savetoFirestore(String id, String name, String supplier, String qty, String price, String descrip){
        if(!name.isEmpty() && !supplier.isEmpty()){
            HashMap<String,Object> map = new HashMap<>();
            map.put("id",id);
            map.put("name",name);
            map.put("supplier",supplier);
            map.put("Quantity",qty);
            map.put("Price",price);
            map.put("Description",descrip);

            db.collection("Inventory").document(id).set(map)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful())
                                Toast.makeText(InventoryDetails.this, "Entry saved", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(InventoryDetails.this, "Failed", Toast.LENGTH_SHORT).show();
                        }
                    });

        }else
            Toast.makeText(this, "Empty fields are not allowed", Toast.LENGTH_SHORT).show();
    }
}