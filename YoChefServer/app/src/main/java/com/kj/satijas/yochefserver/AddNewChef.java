package com.kj.satijas.yochefserver;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kj.satijas.yochefserver.Model.User;

public class AddNewChef extends AppCompatActivity {

    Button btnAddChef;
    EditText Password;
    EditText etMobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_chef);

        btnAddChef=findViewById(R.id.btnAddChef);
        Password=findViewById(R.id.etPassword);
        etMobile=findViewById(R.id.etmob);

        final FirebaseDatabase db= FirebaseDatabase.getInstance();
        final DatabaseReference postref=db.getReference("Customers");

        btnAddChef.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String mob=etMobile.getText().toString();
                final String pass=Password.getText().toString();

                final ProgressDialog mDialog =new ProgressDialog(AddNewChef.this);
                mDialog.setMessage("Please Waiting...");
                mDialog.show();

                postref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        //CHECK IF USER NOT EXISTS

                        if (dataSnapshot.child(mob).exists() ) {
                            //GET USER INFO
                            mDialog.dismiss();
                            Toast.makeText(AddNewChef.this, "Chef Already exist..", Toast.LENGTH_SHORT).show();
                        }
                        else if(etMobile.length()<10)
                        {
                            mDialog.dismiss();
                            Toast.makeText(AddNewChef.this,"The Mobile number must be of 10 characters",Toast.LENGTH_SHORT).show();
                            etMobile.getText().clear();
                        }
                        else
                        {
                            // mDialog.dismiss();
                            User user = new User(mob,pass,"true");
                            postref.child(mob).setValue(user);
                            Toast.makeText(AddNewChef.this, "Chef Added:", Toast.LENGTH_SHORT).show();
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
