package com.kj.satijas.yochefserver.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.kj.satijas.yochefserver.R;

public class IncomeViewholder extends RecyclerView.ViewHolder {


    public TextView txtOrderId,txtTotal;

    public IncomeViewholder(View itemView) {
        super(itemView);
        txtOrderId=(TextView)itemView.findViewById(R.id.ID);
        txtTotal=(TextView)itemView.findViewById(R.id.income);


    }

}
