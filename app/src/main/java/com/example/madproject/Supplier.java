package com.example.madproject;

import java.sql.Date;
import java.sql.Timestamp;

public class Supplier {
    String name;
    String phone;
    String email;
    String description;
    //Date regdate;
    //Timestamp regtime;

    public Supplier() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    public Date getRegtime() {
//        return regtime;
//    }
//
//    public void setRegtime( Date regtime) {
//        this.regtime = regtime;
//    }
}
