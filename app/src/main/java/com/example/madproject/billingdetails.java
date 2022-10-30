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

public class billingdetails extends AppCompatActivity {

    EditText edtbillnum, edtitem,edtqty;
    Button savebtn, viewbtn;
    FirebaseFirestore db;
    private String ubillnum, uitem, uqty,uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billingdetails);

        edtbillnum = findViewById(R.id.billnum_edit_txt);
        edtitem = findViewById(R.id.item_edit_txt);
        edtqty = findViewById(R.id.qty_edit_txt);

        savebtn = findViewById(R.id.save_btn);
        viewbtn = findViewById(R.id.view_btn);

        db = FirebaseFirestore.getInstance();

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            savebtn.setText("Update");
            uid = bundle.getString("uid");
            ubillnum = bundle.getString("ubillnum");
            uitem = bundle.getString("uitem");
            uqty = bundle.getString("uqty");

            edtbillnum.setText(ubillnum);
            edtitem.setText(uitem);
            edtitem.setText(uqty);
        }else{
            savebtn.setText("Save");
        }

        viewbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(billingdetails.this,ViewCustomer.class));
            }
        });

        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String billnum = edtbillnum.getText().toString();
                String item = edtitem.getText().toString();
                String qty = edtqty.getText().toString();

                Bundle bundle1 = getIntent().getExtras();
                if(bundle1 != null){
                    String Id = uid;
                    updateToFirestore(Id, billnum, item,qty);
                }else{
                    String Id = UUID.randomUUID().toString();
                    savetoFirestore(Id,billnum,item,qty);
                }
            }
        });
    }

    private void updateToFirestore(String Id, String name, String email, String phone){
        db.collection("billing").document(Id).update("billnum",name,"item",email,"qty",phone)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(billingdetails.this, "Data updated", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(billingdetails.this, "Error :"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(billingdetails.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void savetoFirestore(String Id, String billnum, String item, String qty){
        if(!billnum.isEmpty() && !item.isEmpty()){
            HashMap<String,Object> map = new HashMap<>();
            map.put("id",Id);
            map.put("billnum",billnum);
            map.put("item",item);
            map.put("qty",qty);

            db.collection("billing").document(billnum).set(map)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful())
                                Toast.makeText(billingdetails.this, "Data saved", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(billingdetails.this, "Failed", Toast.LENGTH_SHORT).show();
                        }
                    });

        }else
            Toast.makeText(this, "Empty fields are not allowed", Toast.LENGTH_SHORT).show();
    }
}