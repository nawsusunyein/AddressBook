package com.example.addressbook;

import android.content.Context;
import android.content.Intent;
import android.provider.Telephony;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.zip.Inflater;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AddressPagerAdapter extends RecyclerView.Adapter<AddressViewHolder> {

    private ArrayList<Address> addressList;
    Context context;

    public AddressPagerAdapter(ArrayList<Address> addressList,Context context){
        this.addressList = addressList;
        this.context = context;
    }

    @NonNull
    @Override
    public AddressViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View addressView = inflater.inflate(R.layout.item_address,parent,false);
        AddressViewHolder addressViewHolder = new AddressViewHolder(addressView);
        return addressViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AddressViewHolder holder, int position) {
        holder.txtUserName.setText(addressList.get(position).getName());
        holder.txtAddress.setText(addressList.get(position).getAddress());
        holder.txtPhone.setText(addressList.get(position).getPhone());
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                Intent writeIntent = new Intent(context,WriteAddress.class);
                writeIntent.putExtra("name",addressList.get(pos).getName());
                writeIntent.putExtra("address",addressList.get(pos).getAddress());
                writeIntent.putExtra("phone",addressList.get(pos).getPhone());
                writeIntent.putExtra("id",addressList.get(pos).getId());
                context.startActivity(writeIntent);
            }

            @Override
            public void onItemLongClick(View v, int pos) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return addressList.size();
    }
}
