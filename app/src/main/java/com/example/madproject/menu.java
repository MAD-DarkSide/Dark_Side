package com.example.madproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class menu extends AppCompatActivity implements View.OnClickListener{

    private ImageView goHome;
    private ImageView option;
    private TextView profile;
    private TextView support;
    private TextView privacy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        goHome = (ImageView)findViewById(R.id.back_icon);
        goHome.setOnClickListener(this);

        option = (ImageView)findViewById(R.id.option_icon);
        option.setOnClickListener(this);

        profile = (TextView) findViewById(R.id.profile);
        profile.setOnClickListener(this);

        support = (TextView) findViewById(R.id.support);
        support.setOnClickListener(this);

        privacy = (TextView) findViewById(R.id.privacy);
        privacy.setOnClickListener(this);

    } // end of on create

    public void onClick(View v){
        switch (v.getId()){
            case R.id.back_icon:
                startActivity(new Intent(this, homeActivity.class));
                break;

            case R.id.option_icon:
                startActivity(new Intent(this, menu.class));
                break;

            case R.id.profile:
                startActivity(new Intent(this, userprofile.class));
                break;

            case R.id.support:
                startActivity(new Intent(this, support.class));
                break;

            case R.id.privacy:
                startActivity(new Intent(this, privacy.class));
                break;
        }
    }


}