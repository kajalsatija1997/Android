package com.kj.satijas.orderingapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kj.satijas.orderingapp.Common.Common;
import com.kj.satijas.orderingapp.Model.CustomerClass;
import com.kj.satijas.orderingapp.Model.tables;
import com.kj.satijas.orderingapp.Service.ListenOrders;

import java.util.ArrayList;
import java.util.Map;

public class
BookTable extends AppCompatActivity {
    ToggleButton btnTable1,btnTable2,btnTable3,btnTable4,btnTable5,btnTable6;;
    TextView tvCustomerName;
    DatabaseReference Booked_tables;
    ArrayList<String > tabs;
    Button btnConfirmBooking;
    public static final String TAG="HELLOOOOOOOOOOOOOOOO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_table);

        btnTable1=findViewById(R.id.btnTable1);
        btnTable2=findViewById(R.id.btnTable2);
        btnTable3=findViewById(R.id.btnTable3);
        btnTable4=findViewById(R.id.btnTable4);
        btnTable5=findViewById(R.id.btnTable5);
        btnTable6=findViewById(R.id.btnTable6);
        btnConfirmBooking=findViewById(R.id.btnConfirmBooking);
        tvCustomerName=findViewById(R.id.tvCustomerName);

       //use dissss

        Intent intentThatStartThis=getIntent();
        final String name=intentThatStartThis.getStringExtra("NAME");
        final String mob=intentThatStartThis.getStringExtra("MOB");
        tvCustomerName.setText(name);

        //Firebase
        final FirebaseDatabase db= FirebaseDatabase.getInstance();
        final DatabaseReference postref=db.getReference("Customers");
        Booked_tables=db.getReference().child("Tables");

        if(!btnTable1.isChecked() && !btnTable2.isChecked() && !btnTable3.isChecked()&& !btnTable4.isChecked()&&!btnTable5.isChecked()&&!btnTable6.isChecked())
        {
            Toast.makeText(BookTable.this, "PLEASE BOOK ANY TABLE", Toast.LENGTH_SHORT).show();
        }

        Booked_tables.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                tabs = new ArrayList<>();

                collectTables((Map<String,Object>) dataSnapshot.getValue());

                if(tabs.contains("TABLE 1"))
                {
                    btnTable1.setBackgroundResource(R.drawable.booked_table);
                }
                if(tabs.contains("TABLE 2")) {
                    btnTable2.setBackgroundResource(R.drawable.booked_table);
                }
                if(tabs.contains("TABLE 3"))
                {
                    btnTable3.setBackgroundResource(R.drawable.booked_table);
                }
                if(tabs.contains("TABLE 4"))
                {
                    btnTable4.setBackgroundResource(R.drawable.booked_table);
                }
                if(tabs.contains("TABLE 5")) {
                    btnTable5.setBackgroundResource(R.drawable.booked_table);
                }
                if(tabs.contains("TABLE 6"))
                {
                    btnTable6.setBackgroundResource(R.drawable.booked_table);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btnTable1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnTable1.isChecked() && !tabs.contains("TABLE 1"))
                {
                   // btnTable1.setVisibility(View.INVISIBLE);
                    tables t = new tables("TABLE 1");
                    Booked_tables.push().setValue(t);
                    btnTable1.setEnabled(false);
                    btnTable2.setEnabled(false);
                    btnTable3.setEnabled(false);
                    btnTable4.setEnabled(false);
                    btnTable5.setEnabled(false);
                    btnTable6.setEnabled(false);
                }
            }
        });
        btnTable2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnTable2.isChecked())
                {
                    tables t = new tables("TABLE 2");
                    Booked_tables.push().setValue(t);
                    btnTable1.setEnabled(false);
                    btnTable2.setEnabled(false);
                    btnTable3.setEnabled(false);
                    btnTable4.setEnabled(false);
                    btnTable5.setEnabled(false);
                    btnTable6.setEnabled(false);
                }
            }
        });
        btnTable3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnTable3.isChecked())
                {
                    tables t = new tables("TABLE 3");
                    Booked_tables.push().setValue(t);
                    btnTable1.setEnabled(false);
                    btnTable2.setEnabled(false);
                    btnTable3.setEnabled(false);
                    btnTable4.setEnabled(false);
                    btnTable5.setEnabled(false);
                    btnTable6.setEnabled(false);
                }
            }
        });
        btnTable4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnTable4.isChecked())
                {
                    tables t = new tables("TABLE 4");
                    Booked_tables.push().setValue(t);
                    btnTable1.setEnabled(false);
                    btnTable2.setEnabled(false);
                    btnTable3.setEnabled(false);
                    btnTable4.setEnabled(false);
                    btnTable5.setEnabled(false);
                    btnTable6.setEnabled(false);
                }
            }
        });
        btnTable5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnTable5.isChecked())
                {
                    tables t = new tables("TABLE 5");
                    Booked_tables.push().setValue(t);
                    btnTable1.setEnabled(false);
                    btnTable2.setEnabled(false);
                    btnTable3.setEnabled(false);
                    btnTable4.setEnabled(false);
                    btnTable5.setEnabled(false);
                    btnTable6.setEnabled(false);
                }
            }
        });
        btnTable6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnTable6.isChecked())
                {
                    tables t = new tables("TABLE 6");
                    Booked_tables.push().setValue(t);
                    btnTable1.setEnabled(false);
                    btnTable2.setEnabled(false);
                    btnTable3.setEnabled(false);
                    btnTable4.setEnabled(false);
                    btnTable5.setEnabled(false);
                    btnTable6.setEnabled(false);
                }
            }
        });

        btnConfirmBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressDialog mDialog =new ProgressDialog(BookTable.this);
                mDialog.setMessage("Please Waiting...");
                mDialog.show();


                db.getReference().child("Customers").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    if(btnTable1.isChecked())
                    {

                        CustomerClass cust = new CustomerClass(name,"TABLE 1");
                        postref.child(mob).setValue(cust);
                        Common.CurrentCustomer=cust;
                        Intent i=new Intent(BookTable.this,Menu.class);
                        startActivity(i);
                        finish();
                    }
                     if(btnTable2.isChecked())
                        {
                            CustomerClass cust = new CustomerClass(name,"TABLE 2");
                            postref.child(mob).setValue(cust);
                            Common.CurrentCustomer=cust;
                            Intent i=new Intent(BookTable.this,Menu.class);
                            startActivity(i);
                            finish();
                        }
                        if(btnTable3.isChecked())
                        {
                            CustomerClass cust = new CustomerClass(name,"TABLE 3");
                            postref.child(mob).setValue(cust);
                            Common.CurrentCustomer=cust;
                            Intent i=new Intent(BookTable.this,Menu.class);
                            startActivity(i);
                            finish();
                        }
                        if(btnTable4.isChecked())
                        {
                            CustomerClass cust = new CustomerClass(name,"TABLE 4");
                            postref.child(mob).setValue(cust);
                            Common.CurrentCustomer=cust;
                            Intent i=new Intent(BookTable.this,Menu.class);
                            startActivity(i);
                            finish();
                        }
                        if(btnTable5.isChecked())
                        {
                            CustomerClass cust = new CustomerClass(name,"TABLE 5");
                            postref.child(mob).setValue(cust);
                            Intent i=new Intent(BookTable.this,Menu.class);
                            startActivity(i);
                            finish();
                        }
                        if(btnTable6.isChecked())
                        {
                            CustomerClass cust = new CustomerClass(name,"TABLE 6");
                            postref.child(mob).setValue(cust);
                            Common.CurrentCustomer=cust;
                            Intent i=new Intent(BookTable.this,Menu.class);
                            startActivity(i);
                            finish();
                        }

                        }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });
        Intent service=new Intent(BookTable.this,ListenOrders.class);
        startService(service);
    }

   @Override
    public void onBackPressed() //restricting to go back
    {
        moveTaskToBack(true);
    }
    private void collectTables(Map<String,Object> tables) {

        tabs = new ArrayList<>();

        //iterate through each user, ignoring their UID
        for (Map.Entry<String, Object> entry : tables.entrySet()){

            //Get user map
            Map singleTable = (Map) entry.getValue();
            //Get table field and append to list
            tabs.add((String) singleTable.get("bookedTables"));
        }

        Log.d(TAG, "collectTables: "+tabs);
    }

}
