package com.example.madproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class supplierview extends AppCompatActivity {

    FloatingActionButton addbutton;
    RecyclerView recyclerV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplierview);

        addbutton = findViewById(R.id.addButton);
//      addbutton.setOnClickListener(this::onClick);
        recyclerV = findViewById(R.id.recyler_view);

        addbutton.setOnClickListener((v)-> startActivity(new Intent(supplierview.this, addsupplier.class)));
        setupRecyclerView();
    }

    void setupRecyclerView() {


    }
//    public void onClick(View v){
//        switch(v.getId()){
//            case R.id.addButton:
//                startActivity(new Intent(supplierview.this, addsupplier.class));
//                break;
//
//        }
    }





























