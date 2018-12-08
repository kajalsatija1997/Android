package com.kj.satijas.yochefserver;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.kj.satijas.yochefserver.Common.Common;
import com.kj.satijas.yochefserver.ItemClickListener.ItemClickListener;
import com.kj.satijas.yochefserver.Model.Request;
import com.kj.satijas.yochefserver.Service.ListenOrders;
import com.kj.satijas.yochefserver.ViewHolder.OrderViewHolder;

import java.util.ArrayList;

public class Order_Management extends AppCompatActivity {
    public RecyclerView recyclerView;
    public RecyclerView.LayoutManager layoutManager;

    FirebaseRecyclerAdapter<Request,OrderViewHolder> adapter;

    FirebaseDatabase database;
    DatabaseReference requests;
    MaterialSpinner spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order__management);

        //firebase
        database=FirebaseDatabase.getInstance();
        requests=database.getReference("Requests");

        recyclerView=(RecyclerView)findViewById(R.id.listOrders);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        loadOrders();

        Intent service=new Intent(Order_Management.this,ListenOrders.class);
        startService(service);

    }

    private void loadOrders() {
        adapter=new FirebaseRecyclerAdapter<Request, OrderViewHolder>(
                Request.class,
                R.layout.order_layout,
                OrderViewHolder.class,
                requests
        ) {
            @Override
            protected void populateViewHolder(OrderViewHolder viewHolder, final Request model, final int position) {
                viewHolder.txtOrderId.setText(adapter.getRef(position).getKey());
                viewHolder.txtOrderStatus.setText(Common.convertCodeToStatus(model.getStatus()));
                viewHolder.txtTableNo.setText(model.getTable_no());

                //New event button
                viewHolder.btnEdit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showUpdateDialog(adapter.getRef(position).getKey(),
                                adapter.getItem(position));
                    }
                });
                viewHolder.btnRemove.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(model.getTable_no().equals("TABLE 1")) {
                            DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                            Query table1Query = ref.child("Tables").orderByChild("bookedTables").equalTo("TABLE 1");

                            table1Query.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    for (DataSnapshot tableSnapshot : dataSnapshot.getChildren()) {
                                        tableSnapshot.getRef().removeValue();
                                    }
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });
                        }
                        if(model.getTable_no().equals("TABLE 2")) {
                            DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                            Query table1Query = ref.child("Tables").orderByChild("bookedTables").equalTo("TABLE 2");

                            table1Query.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    for (DataSnapshot tableSnapshot : dataSnapshot.getChildren()) {
                                        tableSnapshot.getRef().removeValue();
                                    }
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });
                        }
                        if(model.getTable_no().equals("TABLE 3")) {
                            DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                            Query table1Query = ref.child("Tables").orderByChild("bookedTables").equalTo("TABLE 3");

                            table1Query.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    for (DataSnapshot tableSnapshot : dataSnapshot.getChildren()) {
                                        tableSnapshot.getRef().removeValue();
                                    }
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });
                        }
                        if(model.getTable_no().equals("TABLE 4")) {
                            DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                            Query table1Query = ref.child("Tables").orderByChild("bookedTables").equalTo("TABLE 4");

                            table1Query.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    for (DataSnapshot tableSnapshot : dataSnapshot.getChildren()) {
                                        tableSnapshot.getRef().removeValue();
                                    }
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });
                        }
                        if(model.getTable_no().equals("TABLE 5")) {
                            DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                            Query table1Query = ref.child("Tables").orderByChild("bookedTables").equalTo("TABLE 5");

                            table1Query.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    for (DataSnapshot tableSnapshot : dataSnapshot.getChildren()) {
                                        tableSnapshot.getRef().removeValue();
                                    }
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });
                        }
                        if(model.getTable_no().equals("TABLE 6")) {
                            DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                            Query table1Query = ref.child("Tables").orderByChild("bookedTables").equalTo("TABLE 6");

                            table1Query.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    for (DataSnapshot tableSnapshot : dataSnapshot.getChildren()) {
                                        tableSnapshot.getRef().removeValue();
                                    }
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });
                        }
                        deleteOrder(adapter.getRef(position).getKey());

                    }
                });

                viewHolder.btnDetails.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i=new Intent(Order_Management.this,OrderDetail.class);
                        Common.currentRequest=model;
                        i.putExtra("OrderId",adapter.getRef(position).getKey());
                        startActivity(i);
                    }
                });
            }
        };
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
    }



    private void deleteOrder(String key) {
        requests.child(key).removeValue();
        adapter.notifyDataSetChanged();
    }

    private void showUpdateDialog(String key, final Request item) {
    final AlertDialog.Builder alertDialog=new AlertDialog.Builder(Order_Management.this);
    alertDialog.setTitle("Update Order");
    alertDialog.setMessage("Please choose status");

        LayoutInflater inflater=this.getLayoutInflater();
        final View view=inflater.inflate(R.layout.update_order_layout,null);

        spinner=(MaterialSpinner)view.findViewById(R.id.statusSpinner);
        spinner.setItems("Placed","Preparing Order","Delivered");

        alertDialog.setView(view);

        final String localKey=key;
        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
               dialogInterface.dismiss();
               item.setStatus(String.valueOf(spinner.getSelectedIndex()));

               requests.child(localKey).setValue(item);
               adapter.notifyDataSetChanged();

            }
        });
        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        alertDialog.show();

    }
}
