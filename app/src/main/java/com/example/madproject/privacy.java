package com.example.madproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class privacy extends AppCompatActivity implements View.OnClickListener {

    private ImageView option;
    private ImageView back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy);

        option = (ImageView)findViewById(R.id.option_icon);
        option.setOnClickListener(this);

        back = (ImageView)findViewById(R.id.back_icon);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.option_icon:
                startActivity(new Intent(privacy.this, menu.class));
                break;

            case R.id.back_icon:
                startActivity(new Intent(privacy.this, homeActivity.class));
                break;
        }

    }
}