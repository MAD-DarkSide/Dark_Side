package com.example.madproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;

public class homeActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView option;
    private ImageView customer;
    private ImageView billing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        option = (ImageView)findViewById(R.id.option_icon);
        option.setOnClickListener(this);

        customer = (ImageView) findViewById(R.id.imageView7);
        customer.setOnClickListener(this);

        billing = (ImageView) findViewById(R.id.imageView6);
        billing.setOnClickListener(this);

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.option_icon:
                startActivity(new Intent(this, menu.class));
                break;
            case R.id.imageView7:
                startActivity(new Intent(this, CustomerDetails.class));
                break;
            case R.id.imageView6:
                startActivity(new Intent(this, billingdetails.class));
                break;
        }
    }
}


