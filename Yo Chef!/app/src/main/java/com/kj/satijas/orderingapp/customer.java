package com.kj.satijas.orderingapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kj.satijas.orderingapp.Common.Common;
import com.kj.satijas.orderingapp.Model.CustomerClass;

public class customer extends AppCompatActivity {
    Button btnBookTable;
    EditText etName;
    EditText etMobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN); //for making keyboard adjust with keyboard

        btnBookTable=findViewById(R.id.btnBookTable);
        etName=findViewById(R.id.etName);
        etMobile=findViewById(R.id.etmob);

        final FirebaseDatabase db= FirebaseDatabase.getInstance();
        final DatabaseReference postref=db.getReference("Customers");

        btnBookTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String mob=etMobile.getText().toString();
                final String name=etName.getText().toString();
                //final String booktable="TABLE 1";

                final ProgressDialog mDialog =new ProgressDialog(customer.this);
                mDialog.setMessage("Please Waiting...");
                mDialog.show();

                    postref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            //CHECK IF USER NOT EXISTS

                         if (dataSnapshot.child(mob).exists() ) {
                            //GET USER INFO
                             mDialog.dismiss();
                             Toast.makeText(customer.this, "WELCOME AGAIN", Toast.LENGTH_SHORT).show();
                             Intent i = new Intent(customer.this, BookTable.class);
                             i.putExtra("NAME", name);
                             i.putExtra("MOB",mob);
                             startActivity(i);
                        }
                        else if(etMobile.length()<10)
                        {
                                mDialog.dismiss();
                                Toast.makeText(customer.this,"The Mobile number must be of 10 characters",Toast.LENGTH_SHORT).show();
                                etMobile.getText().clear();
                        }
                        else
                            {
                            // mDialog.dismiss();
                            CustomerClass cust = new CustomerClass(name,"");
                            postref.child(mob).setValue(cust);
                            Intent i = new Intent(customer.this, BookTable.class);
                            i.putExtra("NAME", name);
                            i.putExtra("MOB",mob);
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




    }
}
