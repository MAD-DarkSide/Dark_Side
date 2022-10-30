package com.example.madproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;

public class homeActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView option;
    private ImageView supplier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        option = (ImageView)findViewById(R.id.option_icon);
        option.setOnClickListener(this);

        supplier = (ImageView) findViewById(R.id.imageView5);
        supplier.setOnClickListener(this);

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.option_icon:
                startActivity(new Intent(this, menu.class));
                break;
            case R.id.imageView5:
                startActivity(new Intent(homeActivity.this, supplierview.class));
                break;
        }
    }
}


