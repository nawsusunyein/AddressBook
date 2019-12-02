package com.example.addressbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnReadAddress;
    Button btnWriteAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnReadAddress = (Button) findViewById(R.id.btnReadAddress);
        btnWriteAddress = (Button) findViewById(R.id.btnWriteAddress);

        btnReadAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent readIntent = new Intent(MainActivity.this,ReadAddress.class);
                startActivity(readIntent);
            }
        });

        btnWriteAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent writeIntent = new Intent(MainActivity.this,WriteAddress.class);
                startActivity(writeIntent);
            }
        });

    }
}
