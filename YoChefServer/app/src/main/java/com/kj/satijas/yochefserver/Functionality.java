package com.kj.satijas.yochefserver;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Functionality extends AppCompatActivity {

    Button btnAddChef,btnIncome,btnOrders;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_functionality);

         btnAddChef= findViewById(R.id.btnAddChef);
         btnIncome=findViewById(R.id.btnIncome);
         btnOrders=findViewById(R.id.btnGetOrders);

         btnOrders.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent i=new Intent(Functionality.this,Order_Management.class);
                 startActivity(i);
             }
         });

         btnIncome.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent i=new Intent(Functionality.this,Income.class);
                 startActivity(i);
             }
         });

         btnAddChef.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent i=new Intent(Functionality.this,AddNewChef.class);
                 startActivity(i);

             }
         });

    }
}
