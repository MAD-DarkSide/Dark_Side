package com.example.madproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class viewbilling extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FirebaseFirestore db;

    private billingadapter adapter2;
    private List<billing> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewbilling);

        recyclerView = findViewById(R.id.billing_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        db = FirebaseFirestore.getInstance();
        list = new ArrayList<>();
        adapter2 = new billingadapter(this,list);
        recyclerView.setAdapter(adapter2);

        ItemTouchHelper touchHelper = new ItemTouchHelper(new TouchHelper_billing(adapter2));
        touchHelper.attachToRecyclerView(recyclerView);

        showData();

    }

    private void filterList(String text) {
        List<billing> filteredList = new ArrayList<>();
        for(billing billing:list){
            if(billing.getBillnum().toLowerCase().contains(text.toLowerCase()));
            filteredList.add(billing);
        }
        if(filteredList.isEmpty()){
            Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show();
        }else{
            adapter2.setFilteredList(filteredList);
        }
    }

    public void showData(){
        db.collection("billing").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        list.clear();
                        for (DocumentSnapshot snapshot:task.getResult()){
                            billing billing = new billing(snapshot.getString("Id"),snapshot.getString("billnum"),snapshot.getString("item"),snapshot.getString("qty"));
                            list.add(billing);
                        }
                        adapter2.notifyDataSetChanged();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(viewbilling.this, "Oops.. Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}