package com.example.madproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class supplierview extends AppCompatActivity {

    FloatingActionButton addbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplierview);

        addbutton = findViewById(R.id.addButton);

        //addbutton.setOnClickListener((v)-> startActivity(new Intent(supplierview.this, addsupplier.class)));
    }
    public void onClick(View v){
        switch(v.getId()){
            case R.id.addButton:
                startActivity(new Intent(supplierview.this, addsupplier.class));
                break;

        }
    }
}




























