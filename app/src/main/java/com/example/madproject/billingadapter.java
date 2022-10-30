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

public class billingadapter extends RecyclerView.Adapter<billingadapter.MyViewHolder> {
    private viewbilling activity;
    private List<billing> billinglist;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    public billingadapter (viewbilling activity,List<billing> billinglist){
        this.activity = activity;
        this.billinglist = billinglist;
    }

    public void UpdateData(int position){
        billing item = billinglist.get(position);
        Bundle bundle = new Bundle();
        bundle.putString("uId",item.getId());
        bundle.putString("ubillnum",item.getBillnum());
        bundle.putString("uitem",item.getItem());
        bundle.putString("uqty",item.getQty());
        bundle.putString("uprice",item.getPrice());
        Intent intent = new Intent(activity,CustomerDetails.class);
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }

    public void DeleteData (int position){
        billing item = billinglist.get(position);
        db.collection("billing").document(item.getId()).delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            notifyRemoved(position);
                            Toast.makeText(activity, "Data deleted", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(activity, "Error :"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void notifyRemoved(int position){
        billinglist.remove(position);
        notifyItemRemoved(position);
        activity.showData();
    }

    public void setFilteredList(List<billing> filteredList){
        this.billinglist = filteredList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public billingadapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(activity).inflate(R.layout.recycler_customer_item,parent,false);
        return new billingadapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull billingadapter.MyViewHolder holder, int position) {
        holder.billnum.setText(billinglist.get(position).getBillnum());
        holder.item.setText(billinglist.get(position).getItem());
        holder.qty.setText(billinglist.get(position).getQty());
    }

    @Override
    public int getItemCount() {
        return billinglist.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView billnum,item,qty;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            billnum = itemView.findViewById(R.id.billnum_view);
            item = itemView.findViewById(R.id.item_view);
            qty = itemView.findViewById(R.id.qty_view);

        }
    }
}
