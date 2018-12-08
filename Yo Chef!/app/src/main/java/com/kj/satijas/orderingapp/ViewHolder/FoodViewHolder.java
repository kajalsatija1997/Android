package com.kj.satijas.orderingapp.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kj.satijas.orderingapp.ItemClickListener.ItemClickListener;
import com.kj.satijas.orderingapp.R;

public class FoodViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView txtFood_name;
    public  TextView txtPrice;
    private ItemClickListener itemClickListner;

    public void setItemClickListner(ItemClickListener itemClickListner) {
        this.itemClickListner = itemClickListner;
    }

    public FoodViewHolder(View itemView) {
        super(itemView);
        txtFood_name=itemView.findViewById(R.id.txtFoodName);
        txtPrice=itemView.findViewById(R.id.txtPrice);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
    itemClickListner.onClick(v,getAdapterPosition(),false);
    }
}
