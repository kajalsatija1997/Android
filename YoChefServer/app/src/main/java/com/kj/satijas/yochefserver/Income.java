 package com.kj.satijas.yochefserver;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kj.satijas.yochefserver.Model.Request;
import com.kj.satijas.yochefserver.ViewHolder.IncomeViewholder;

import java.text.NumberFormat;
import java.util.Locale;


 public class Income extends AppCompatActivity {

     public RecyclerView recyclerView;
     public RecyclerView.LayoutManager layoutManager;

     FirebaseRecyclerAdapter<Request,IncomeViewholder> adapter;

     FirebaseDatabase database;
     DatabaseReference requests;
     int total=0;

     TextView TVtotal;
     Button btnIncome;

     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);

         //firebase
         database=FirebaseDatabase.getInstance();
         requests=database.getReference("Requests");

         recyclerView=(RecyclerView)findViewById(R.id.Total);
         TVtotal=findViewById(R.id.total);
         recyclerView.setHasFixedSize(true);
         layoutManager=new LinearLayoutManager(this);
         recyclerView.setLayoutManager(layoutManager);

         loadOrders();

         requests.addListenerForSingleValueEvent(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                 if (dataSnapshot.getValue() != null) {
                     for (DataSnapshot ds : dataSnapshot.getChildren()) {
                         String str=ds.child("total").getValue(String.class);
                         String kept=str.substring(1 , str.indexOf("."));
                         int prices=Integer.parseInt(kept);
                         total += prices;
                     }
                     Locale locale=new Locale("hi","IN");
                     NumberFormat fmt=NumberFormat.getCurrencyInstance(locale);
                     TVtotal.setText(String.valueOf(fmt.format(total)));
                 }
             }

             @Override
             public void onCancelled(@NonNull DatabaseError databaseError) {

             }
         });

         btnIncome=findViewById(R.id.btnTodayIncome);
         btnIncome.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Toast.makeText(Income.this, "Your data has been saved in the database", Toast.LENGTH_SHORT).show();
             }
         });

     }

     private void loadOrders() {
         adapter=new FirebaseRecyclerAdapter<Request, IncomeViewholder>(
                 Request.class,
                 R.layout.income_layout,
                 IncomeViewholder.class,
                 requests
         ) {
             @Override
             protected void populateViewHolder(IncomeViewholder viewHolder, final Request model, final int position) {
                 viewHolder.txtOrderId.setText(adapter.getRef(position).getKey());
                 viewHolder.txtTotal.setText(model.getTotal());

             }

         };
         adapter.notifyDataSetChanged();
         recyclerView.setAdapter(adapter);
    }


}
