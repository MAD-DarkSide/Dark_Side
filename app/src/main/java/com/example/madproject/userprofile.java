package com.example.madproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class userprofile extends AppCompatActivity implements View.OnClickListener{

    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;
    private Button logout;
    private ImageView option;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userprofile);

        logout = (Button) findViewById(R.id.logoutButton);

        option = (ImageView)findViewById(R.id.option_icon1);
        option.setOnClickListener(this);

        back = (ImageView)findViewById(R.id.back_icon1);
        back.setOnClickListener(this);


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                switch (view.getId()){
                    case R.id.logoutButton:
                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent( userprofile.this,login.class));

                }




            }
        });

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");

        userID = user.getUid();

        final TextView campanynametext = (TextView) findViewById(R.id.textView7);
        final TextView emailtext = (TextView) findViewById(R.id.textView8);
        final TextView phonetext = (TextView) findViewById(R.id.textView9);

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if(userProfile != null){
                    String email = userProfile.userEmail;
                    String companyname = userProfile.companyName;
                    String phone = userProfile.userPhone;

                    campanynametext.setText(companyname);
                    emailtext.setText(email);
                    phonetext.setText(phone);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(userprofile.this,"Something went wrong", Toast.LENGTH_LONG).show();

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.option_icon1:
                startActivity(new Intent(userprofile.this, menu.class));

            case R.id.back_icon1:
                startActivity(new Intent(userprofile.this, homeActivity.class));
    }
}
}




















