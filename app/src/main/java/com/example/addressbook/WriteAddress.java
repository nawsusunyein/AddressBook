package com.example.addressbook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

import java.io.Console;

public class WriteAddress extends AppCompatActivity {

    EditText txtPersonName;
    EditText txtPhoneNumber;
    EditText txtAddress;

    Button btnSaveAddress;
    Button btnClearValues;

    ProgressBar progressBar;

    private DatabaseReference mDatabase;

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
                addUserValue();
            }
        });

        btnClearValues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearTextValues();
            }
        });
    }

    private void registerTextfieldsById(){
        txtPersonName = (EditText) findViewById(R.id.txtPersonName);
        txtPhoneNumber = (EditText) findViewById(R.id.txtPhoneNumber);
        txtAddress = (EditText) findViewById(R.id.txtAddress);
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
}
