package com.kj.satijas.yochefserver;

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
import com.kj.satijas.yochefserver.Common.Common;
import com.kj.satijas.yochefserver.Model.User;

public class ManagerActivity extends AppCompatActivity {
        EditText etPassword;
        EditText etMobile;
        Button btnSignIn;
        Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN); //for making keyboard adjust with keyboard

        btnSignIn=findViewById(R.id.btnBookTable);
        etPassword=findViewById(R.id.etPassword);
        etMobile=findViewById(R.id.etmob);

        //init firebase
        final FirebaseDatabase db= FirebaseDatabase.getInstance();
        final DatabaseReference postref=db.getReference("Customers");

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String mob=etMobile.getText().toString();
                final String password=etPassword.getText().toString();

                signInUser(mob,password);

            }

            private void signInUser(String mob, String password) {
                final ProgressDialog mDialog =new ProgressDialog(ManagerActivity.this);
                mDialog.setMessage("Please Waiting...");
                mDialog.show();

                final String localmob=mob;
                final String localPassword=password;
                    postref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        //CHECK IF USER NOT EXISTS

                        if (dataSnapshot.child(localmob).exists() )
                        {
                            //GET USER INFO
                            mDialog.dismiss();
                            User user=dataSnapshot.child(localmob).getValue(User.class);
                            user.setPhone(localmob);
                            if(Boolean.parseBoolean(user.getIsStaff()))
                            {
                                if(user.getPassword().equals(localPassword))
                                {
                                    //login ok
                                    Common.CurrentUser=user;
                                    Intent i=new Intent(ManagerActivity.this,Order_Management.class);
                                    startActivity(i);
                                    finish();
                                    i=new Intent(ManagerActivity.this,Functionality.class);
                                    startActivity(i);
                                }
                                else
                                    Toast.makeText(ManagerActivity.this, "Wrong Password..", Toast.LENGTH_SHORT).show();
                            }
                            else
                                Toast.makeText(ManagerActivity.this, "Please Login with Staff Account..", Toast.LENGTH_SHORT).show();
                           /* Intent i = new Intent(customer.this, BookTable.class);
                            i.putExtra("NAME", name);
                            i.putExtra("MOB",mob);
                            startActivity(i);*/
                        }
                        else
                        {
                            mDialog.dismiss();;
                            Toast.makeText(ManagerActivity.this, "User not exist in database..", Toast.LENGTH_SHORT).show();
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
