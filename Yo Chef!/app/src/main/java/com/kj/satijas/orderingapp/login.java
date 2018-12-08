package com.kj.satijas.orderingapp;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.kj.satijas.orderingapp.customer;


public class login extends AppCompatActivity {
    Button btn;
    Button Chef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn = findViewById(R.id.btnCustomer);
        //Chef=findViewById(R.id.chef);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


                    Intent i = new Intent(login.this, customer.class);
                    startActivity(i);

            }
        });

     /*   Chef.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(login.this,Chef.class);
                startActivity(i);
            }
        });*/


    }
}
