package com.example.addressbook;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class AddressViewHolder extends RecyclerView.ViewHolder {

    TextView txtUserName;
    TextView txtPhone;
    TextView txtAddress;

    AddressViewHolder(View itemView){
        super(itemView);
        txtUserName = (TextView) itemView.findViewById(R.id.txtName);
        txtPhone = (TextView) itemView.findViewById(R.id.txtPhone);
        txtAddress = (TextView) itemView.findViewById(R.id.txtAddress);
    }
}
