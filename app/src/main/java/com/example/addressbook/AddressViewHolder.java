package com.example.addressbook;

import android.content.ClipData;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class AddressViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener{

    TextView txtUserName;
    TextView txtPhone;
    TextView txtAddress;
    private ItemClickListener itemClickListener;


    AddressViewHolder(View itemView){
        super(itemView);
        txtUserName = (TextView) itemView.findViewById(R.id.txtName);
        txtPhone = (TextView) itemView.findViewById(R.id.txtPhone);
        txtAddress = (TextView) itemView.findViewById(R.id.txtAddress);
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }

    public void setItemClickListener(ItemClickListener ic){
        this.itemClickListener = ic;
    }
    @Override
    public void onClick(View v) {
        this.itemClickListener.onItemClick(v,getLayoutPosition());
    }

    @Override
    public boolean onLongClick(View v) {
        int layoutPos = getLayoutPosition();
        this.itemClickListener.onItemLongClick(v,getLayoutPosition());
        return true;
    }
}
