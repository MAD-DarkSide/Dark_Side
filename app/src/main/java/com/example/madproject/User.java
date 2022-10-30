package com.example.madproject;

import java.nio.file.attribute.UserPrincipal;

public class User {

    public String companyName, userPhone, userEmail;

    public User(){

    }

    public User(String userEmail, String companyName, String userPhone ){
        this.userEmail = userEmail;
        this.companyName = companyName;
        this.userPhone = userPhone;
    }
}
