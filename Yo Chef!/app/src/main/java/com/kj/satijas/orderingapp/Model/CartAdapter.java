package com.kj.satijas.orderingapp.Model;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.kj.satijas.orderingapp.ItemClickListener.ItemClickListener;
import com.kj.satijas.orderingapp.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{

    public TextView txt_Cart_name,txt_price;
    public ImageView img_Cart_count;

    private ItemClickListener itemClickListener;

    public void setTxt_Cart_name(TextView txt_Cart_name) {
        this.txt_Cart_name = txt_Cart_name;
    }

    public CartViewHolder(View itemView) {
        super(itemView);
        txt_Cart_name=itemView.findViewById(R.id.cart_item_name);
        txt_price=itemView.findViewById(R.id.cart_item_price);
        img_Cart_count=itemView.findViewById(R.id.item_cart_count);
    }

    @Override
    public void onClick(View v) {

    }
}
public class CartAdapter extends RecyclerView.Adapter<CartViewHolder> {
    private List<Order> listData=new ArrayList<>();
    private Context context;

    public CartAdapter(List<Order> listData, Context context) {
        this.listData = listData;
        this.context = context;
    }

    @Override
    public CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View itemView=layoutInflater.inflate(R.layout.cart_layout,parent,false);
        return new CartViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CartViewHolder holder, int position) {
        TextDrawable textDrawable=TextDrawable.builder().buildRound(""+listData.get(position).getQuantity(),Color.RED);
        holder.img_Cart_count.setImageDrawable(textDrawable);

        Locale locale=new Locale("hi","IN");
        NumberFormat fmt=NumberFormat.getCurrencyInstance(locale);
        int price=(Integer.parseInt(listData.get(position).getPrice()))*(Integer.parseInt(listData.get(position).getQuantity()));
        holder.txt_price.setText(fmt.format(price));
        holder.txt_Cart_name.setText(listData.get(position).getProductName());
    }

    @Override
    public int getItemCount()
    {
        return listData.size();
    }
}
