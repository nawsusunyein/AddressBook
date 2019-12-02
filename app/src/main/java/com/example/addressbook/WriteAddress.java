package com.example.addressbook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class WriteAddress extends AppCompatActivity {

    EditText txtPersonName;
    EditText txtPhoneNumber;
    EditText txtAddress;

    Button btnSaveAddress;
    Button btnClearValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_address);
        btnSaveAddress = (Button) findViewById(R.id.btnAddAddress);
        btnClearValues = (Button) findViewById(R.id.btnCancel);

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
        registerTextfieldsById();
    }

    private void clearTextValues(){
        registerTextfieldsById();
        txtPersonName.setText("");
        txtPhoneNumber.setText("");
        txtAddress.setText("");
    }
}
