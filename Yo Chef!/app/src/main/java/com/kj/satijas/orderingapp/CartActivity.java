package com.kj.satijas.orderingapp;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.kj.satijas.orderingapp.Common.Common;
import com.kj.satijas.orderingapp.DatabaseClasses.Database;
import com.kj.satijas.orderingapp.Model.CartAdapter;
import com.kj.satijas.orderingapp.Model.Order;
import com.kj.satijas.orderingapp.Model.Request;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import info.hoang8f.widget.FButton;

public class CartActivity extends AppCompatActivity {
    private static final String TAG ="heyyyyyyyyyyy" ;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference requests;

    TextView txtTotalPrice;
    FButton btnPlace;

    List<Order> cart=new ArrayList<>();

    CartAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        //firebase
        database=FirebaseDatabase.getInstance();
        requests=database.getReference("Requests");

        //init
        recyclerView=findViewById(R.id.listCart);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        txtTotalPrice=findViewById(R.id.total);
        btnPlace=findViewById(R.id.btnPlaceOrder);

        btnPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //create new request
                Request request=new Request(
                        Common.CurrentCustomer.getName(),
                        txtTotalPrice.getText().toString(),
                        Common.CurrentCustomer.getBooktable(),
                        cart
                );

                //submit to firebase
                requests.child(String.valueOf(System.currentTimeMillis()))
                        .setValue(request);
                //Delete Cart
                new Database(getBaseContext()).CleanCart();
                Toast.makeText(CartActivity.this, "ThankYou Order Placed", Toast.LENGTH_SHORT).show();
                Intent i=new Intent(CartActivity.this,OrderStatus.class);
                startActivity(i);
                finish();
            }
        });
        loadListFood();


    }

    private void loadListFood() {
     cart=new Database(this).getCarts();
     adapter=new CartAdapter(cart,this);
     recyclerView.setAdapter(adapter);

     //calculate total
        int total=0;
        for(Order order:cart) {
            total += (Integer.parseInt(order.getPrice())) * (Integer.parseInt(order.getQuantity()));
            Log.d(TAG, "loadListFood: " + order.getPrice()+ "-->"+order.getQuantity());
        }
        Locale locale=new Locale("hi","IN");
        NumberFormat fmt=NumberFormat.getCurrencyInstance(locale);

        txtTotalPrice.setText(fmt.format(total));


    }
}
