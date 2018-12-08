package com.kj.satijas.yochefserverchef;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.kj.satijas.yochefserverchef.Common.Common;
import com.kj.satijas.yochefserverchef.ViewHolder.OrderDetailAdapter;

public class OrderDetail extends AppCompatActivity {

    TextView orderId,tableNo,orderTotal;
    String order_id_value="";
    RecyclerView lstFoods;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_detail_layout);

        orderId=(TextView)findViewById(R.id.order_id);
        tableNo=(TextView)findViewById(R.id.table_no);
        orderTotal=(TextView)findViewById(R.id.order_total);

        lstFoods=(RecyclerView)findViewById(R.id.listOrders);
        lstFoods.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        lstFoods.setLayoutManager(layoutManager);

        if(getIntent()!=null)
        {
            order_id_value=getIntent().getStringExtra("OrderId");

        }

        orderId.setText(order_id_value);
        tableNo.setText(Common.currentRequest.getTable_no());
        orderTotal.setText(Common.currentRequest.getTotal());

        OrderDetailAdapter adapter=new OrderDetailAdapter(Common.currentRequest.getFoods());
        adapter.notifyDataSetChanged();
        lstFoods.setAdapter(adapter);

    }
}
