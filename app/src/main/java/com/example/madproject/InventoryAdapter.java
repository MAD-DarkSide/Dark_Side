package com.example.madproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class InventoryAdapter extends RecyclerView.Adapter<InventoryAdapter.MyViewHolder>{
    private ViewInventory activity;
    private List<Inventory> InventoryList;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    public InventoryAdapter (ViewInventory activity,List<Inventory> InventoryList){
        this.activity = activity;
        this.InventoryList = InventoryList;
    }

    public void UpdateData(int position){
        Inventory item = InventoryList.get(position);
        Bundle bundle = new Bundle();
        bundle.putString("uID",item.getID());
        bundle.putString("uName",item.getName());
        bundle.putString("uSupplier",item.getSupplier());
        bundle.putString("uQuantity",item.getQuantity());
        bundle.putString("uPrice",item.getU_price());
        bundle.putString("uDescription",item.getDescription());
        Intent intent = new Intent(activity,InventoryDetails.class);
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }

    public void DeleteData (int position){
        Inventory item = InventoryList.get(position);
        db.collection("Inventory").document(item.getID()).delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            notifyRemoved(position);
                            Toast.makeText(activity, "Entry deleted", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(activity, "Not Successful :"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void notifyRemoved(int position){
        InventoryList.remove(position);
        notifyItemRemoved(position);
        activity.showData();
    }

    public void setFilteredList(List<Inventory> filteredList){
        this.InventoryList = filteredList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public InventoryAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(activity).inflate(R.layout.recycler_inventory_item,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull InventoryAdapter.MyViewHolder holder, int position) {
        holder.name.setText(InventoryList.get(position).getName());
        holder.U_price.setText(InventoryList.get(position).getU_price());
        holder.quantity.setText(InventoryList.get(position).getQuantity());
    }

    @Override
    public int getItemCount() {
        return InventoryList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name,U_price,quantity;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name_view);
            U_price = itemView.findViewById(R.id.price_view);
            quantity = itemView.findViewById(R.id.qty_view);

        }
    }
}
