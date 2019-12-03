package com.example.addressbook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ReadAddress extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private ArrayList<Address> addressList = new ArrayList<>();
    RecyclerView recyclerAddressView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_address);
        getAddressValues();
    }

    private void getAddressValues(){
        addressList.clear();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Address");
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    String name = ds.child("name").getValue(String.class);
                    String phone = ds.child("phone").getValue(String.class);
                    String address = ds.child("address").getValue(String.class);
                    String id = ds.getKey();
                    Address addr = new Address(name,phone,address,id);
                    addressList.add(addr);
                }
                showAddressValues();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        mDatabase.addValueEventListener(valueEventListener);
    }

    private void showAddressValues(){
        recyclerAddressView = (RecyclerView) findViewById(R.id.recyclerAddressView);
        AddressPagerAdapter addressPagerAdapter = new AddressPagerAdapter(addressList,getApplicationContext());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ReadAddress.this);
        recyclerAddressView.setLayoutManager(layoutManager);
        recyclerAddressView.setAdapter(addressPagerAdapter);
    }
}
