package com.example.addressbook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.io.Console;
import java.util.HashMap;
import java.util.Map;

public class WriteAddress extends AppCompatActivity {

    EditText txtPersonName;
    EditText txtPhoneNumber;
    EditText txtAddress;

    Button btnSaveAddress;
    Button btnClearValues;

    ProgressBar progressBar;

    private DatabaseReference mDatabase;
    private String name,phone,address,id;
    private Boolean isUpdate = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_address);
        btnSaveAddress = (Button) findViewById(R.id.btnAddAddress);
        btnClearValues = (Button) findViewById(R.id.btnCancel);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        btnSaveAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(isUpdate){
                    updateUserValues();
                }else{
                    addUserValue();
                }
            }
        });

        btnClearValues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearTextValues();
            }
        });

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            Intent updateIntent = getIntent();
            name = updateIntent.getExtras().getString("name");
            phone = updateIntent.getExtras().getString("phone");
            address = updateIntent.getExtras().getString("address");
            id = updateIntent.getExtras().getString("id");
            setAddressValues();
            isUpdate = true;
        }else{
            isUpdate = false;
        }
    }

    private void registerTextfieldsById(){
        txtPersonName = (EditText) findViewById(R.id.txtPersonName);
        txtPhoneNumber = (EditText) findViewById(R.id.txtPhoneNumber);
        txtAddress = (EditText) findViewById(R.id.txtAddress);
    }

    private void setAddressValues(){
        registerTextfieldsById();
        txtPersonName.setText(name);
        txtPhoneNumber.setText(phone);
        txtAddress.setText(address);
    }

    private void addUserValue(){
        progressBar.setVisibility(View.VISIBLE);
        registerTextfieldsById();
        String personName = txtPersonName.getText().toString();
        String phoneNumber = txtPhoneNumber.getText().toString();
        String address = txtAddress.getText().toString();
        Address addr = new Address(personName,phoneNumber,address);

        if(!(addr.getAddress().isEmpty()) && !(addr.getName().isEmpty()) && !(addr.getPhone().isEmpty())){
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference ref = database.getReference().child("Address");
            ref.push().setValue(addr).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    progressBar.setVisibility(View.GONE);

                    Toast.makeText(WriteAddress.this,"Inserted data successfully",Toast.LENGTH_LONG).show();
                    clearTextValues();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(WriteAddress.this,"Insert data failure",Toast.LENGTH_LONG).show();
                }
            });
        }else{
            progressBar.setVisibility(View.GONE);
            Toast.makeText(WriteAddress.this,"Fill all data",Toast.LENGTH_LONG).show();
        }


      //Store values if you would like to save with custom id
      /*  DatabaseReference ref = database.getReference().child("Address").child("0");
        ref.child("Name").setValue(addr.getName());
        ref.child("Phone").setValue(addr.getPhone());
        ref.child("Address").setValue(addr.getAddress());*/


    }

    private void clearTextValues(){
        registerTextfieldsById();
        txtPersonName.setText("");
        txtPhoneNumber.setText("");
        txtAddress.setText("");
    }

    private void updateUserValues(){
        registerTextfieldsById();
        progressBar.setVisibility(View.VISIBLE);
        String name = txtPersonName.getText().toString();
        String address = txtAddress.getText().toString();
        String phone = txtPhoneNumber.getText().toString();
        Address addr1 = new Address(name,phone,address);
        Map<String,Object> addressHashMap = new HashMap<>();

        if(!(addr1.getName().isEmpty()) && !(addr1.getPhone().isEmpty()) && !(addr1.getAddress().isEmpty())){
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference ref = database.getReference().child("Address");
            ref.child(id).setValue(addr1).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(WriteAddress.this,"Update successfully",Toast.LENGTH_LONG).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(WriteAddress.this,"Update fails",Toast.LENGTH_LONG).show();
                }
            });
        }else{
            progressBar.setVisibility(View.GONE);
            Toast.makeText(WriteAddress.this,"Fill all data",Toast.LENGTH_LONG).show();
        }
    }
}
