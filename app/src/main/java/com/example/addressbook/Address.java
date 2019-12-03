package com.example.addressbook;
import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Address {
    String name;
    String phone;
    String address;

    public Address(){}
    public Address(String name,String phone,String address){
        this.name = name;
        this.phone = phone;
        this.address = address;
    }

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }
    public String getPhone(){
        return phone;
    }

    public void setAddress(String address){
        this.address = address;
    }
    public String getAddress(){
        return address;
    }
}
