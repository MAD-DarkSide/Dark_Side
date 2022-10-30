package com.example.madproject;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

public class TouchHelper_billing extends ItemTouchHelper.SimpleCallback {
    private billingadapter adapter2;

    public TouchHelper_billing(billingadapter adapter) {
        super(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        this.adapter2 = adapter;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        final int position = viewHolder.getAdapterPosition();
        if(direction == ItemTouchHelper.LEFT){
            adapter2.UpdateData(position);
            adapter2.notifyDataSetChanged();
        }else{
            adapter2.DeleteData(position);
        }
    }
}
