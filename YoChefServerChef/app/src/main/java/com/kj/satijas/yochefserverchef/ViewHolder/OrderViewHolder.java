package com.kj.satijas.yochefserverchef.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.kj.satijas.yochefserverchef.ItemClickListener.ItemClickListener;
import com.kj.satijas.yochefserverchef.R;

public class OrderViewHolder extends RecyclerView.ViewHolder {

    public Button btnEdit,btnRemove,btnDetails;

    public TextView txtOrderId,txtOrderStatus,txtTableNo;

    public OrderViewHolder(View itemView) {
        super(itemView);
        txtOrderId=(TextView)itemView.findViewById(R.id.order_id);
        txtOrderStatus=(TextView)itemView.findViewById(R.id.order_status);
        txtTableNo=(TextView)itemView.findViewById(R.id.table_no);

        btnEdit=(Button) itemView.findViewById(R.id.btnEdit);
        btnRemove=(Button) itemView.findViewById(R.id.btnRemove);
        btnDetails=(Button) itemView.findViewById(R.id.btnDetails);


    }


}
