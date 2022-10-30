//package com.example.madproject;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
//import com.firebase.ui.firestore.FirestoreRecyclerOptions;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//public class supplierAdapter extends FirestoreRecyclerAdapter<Supplier, supplierAdapter.supplierViewHolder>{
//
//
//    /**
//     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
//     * FirestoreRecyclerOptions} for configuration options.
//     *
//     * @param options
//     */
//    public supplierAdapter(@NonNull FirestoreRecyclerOptions<Supplier> options) {
//        super(options);
//    }
//
//    @Override
//    protected void onBindViewHolder(@NonNull supplierViewHolder holder, int position, @NonNull Supplier model) {
//        holder.name.setText(Sup);
//    }
//
//    @NonNull
//    @Override
//    public supplierViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_supplier,parent, false);
//        return new supplierViewHolder(view);
//    }
//
//    class supplierViewHolder extends RecyclerView.ViewHolder {
//
//        TextView name,email,phone,des;
//
//        public supplierViewHolder(@NonNull View itemView) {
//            super(itemView);
//
//            name = itemView.findViewById(R.id.supname);
//            email = itemView.findViewById(R.id.supemail);
//            phone = itemView.findViewById(R.id.supphone);
//            des = itemView.findViewById(R.id.supdes);
//        }
//    }
//}
