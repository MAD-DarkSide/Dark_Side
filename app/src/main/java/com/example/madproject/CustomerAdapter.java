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

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.MyViewHolder>{
    private ViewCustomer activity;
    private List<Customer> customerList;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    public CustomerAdapter (ViewCustomer activity,List<Customer> customerList){
        this.activity = activity;
        this.customerList = customerList;
    }

    public void UpdateData(int position){
        Customer item = customerList.get(position);
        Bundle bundle = new Bundle();
        bundle.putString("uId",item.getId());
        bundle.putString("uName",item.getName());
        bundle.putString("uEmail",item.getEmail());
        bundle.putString("uPhone",item.getPhone());
        Intent intent = new Intent(activity,CustomerDetails.class);
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }

    public void DeleteData (int position){
        Customer item = customerList.get(position);
        db.collection("Customer").document(item.getId()).delete()
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
        customerList.remove(position);
        notifyItemRemoved(position);
        activity.showData();
    }

    public void setFilteredList(List<Customer> filteredList){
        this.customerList = filteredList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(activity).inflate(R.layout.recycler_customer_item,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(customerList.get(position).getName());
        holder.email.setText(customerList.get(position).getEmail());
        holder.phone.setText(customerList.get(position).getPhone());
    }

    @Override
    public int getItemCount() {
        return customerList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name,email,phone;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.title_view);
            email = itemView.findViewById(R.id.email_view);
            phone = itemView.findViewById(R.id.phone_view);

        }
    }
}
