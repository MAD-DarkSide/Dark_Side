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

public class ViewInventory extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FirebaseFirestore db;

    private InventoryAdapter adapter1;
    private List<Inventory> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_inventory);

        recyclerView = findViewById(R.id.inventory_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        db = FirebaseFirestore.getInstance();
        list = new ArrayList<>();
        adapter1 = new InventoryAdapter(this,list);
        recyclerView.setAdapter(adapter1);

        ItemTouchHelper touchHelper = new ItemTouchHelper(new TouchHelper_inventory(adapter1));
        touchHelper.attachToRecyclerView(recyclerView);

        showData();
    }

    private void filterList(String text) {
        List<Inventory> filteredList = new ArrayList<>();
        for(Inventory inventory:list){
            if(inventory.getName().toLowerCase().contains(text.toLowerCase()));
            filteredList.add(inventory);
        }
        if(filteredList.isEmpty()){
            Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show();
        }else{
            adapter1.setFilteredList(filteredList);
        }
    }

    public  void showData(){
        db.collection("Customer").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        list.clear();
                        for (DocumentSnapshot snapshot:task.getResult()){
                            Inventory inventory = new Inventory(snapshot.getString("id"),snapshot.getString("name"),snapshot.getString("supplier"),snapshot.getString("qty"),snapshot.getString("price"),snapshot.getString("descrip"));
                            list.add(inventory);
                        }
                        adapter1.notifyDataSetChanged();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ViewInventory.this, "Oops.. Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}