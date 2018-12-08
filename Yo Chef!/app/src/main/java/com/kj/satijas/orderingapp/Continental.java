package com.kj.satijas.orderingapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.kj.satijas.orderingapp.DatabaseClasses.Database;
import com.kj.satijas.orderingapp.Model.Order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Continental extends AppCompatActivity {
    private static final String TAG ="HELLLLLLOOO";
    ListView listView;
    Button btnOrder,btnAdd;
    String names,prices,quantity;
    SimpleAdapter simpleAdapter;

    String[] snacks = new String[]{"BAKED POTATO","PATTY","CHICKPEA SOUP","HOT CROSS BUNS","PASTA"};
    int[] pix = new int[]{R.drawable.bp,R.drawable.patty,R.drawable.chickpea,R.drawable.buns,R.drawable.pasta};
    String[] price = new String[]{"130","75","100","190","140"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_continental);

        List<HashMap<String, String>> aList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            HashMap<String, String> hm = new HashMap<>();
            hm.put("listview_title", snacks[i]);
            hm.put("listview_image", Integer.toString(pix[i]));
            hm.put("des",price[i]);
            aList.add(hm);
        }

        String[] from = {"listview_image", "listview_title","des"};
        int[] to = {R.id.listview_image, R.id.txtFoodName,R.id.txtPrice};

        simpleAdapter = new SimpleAdapter(getBaseContext(), aList, R.layout.customlistview, from, to){
            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {
                // get filled view from SimpleAdapter
                View itemView=super.getView(position, convertView, parent);
                // find our button there
                final ElegantNumberButton eb=itemView.findViewById(R.id.NumberButton);
                // add an onClickListener
                eb.setOnClickListener(new ElegantNumberButton.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        quantity=eb.getNumber();
                        HashMap<String, Object> obj = (HashMap<String, Object>) simpleAdapter.getItem(position);
                        names = (String) obj.get("listview_title");
                        prices=(String)obj.get("des");
                        Log.d(TAG, "onItemClick: "+names+" "+prices+" "+quantity);
                    }
                });
                return itemView;

            }
        };

        listView = findViewById(R.id.lv3);
        listView.setAdapter(simpleAdapter);

        btnOrder = findViewById(R.id.btnOrderC);
        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder1 = new AlertDialog.Builder(Continental.this);
                builder1.setMessage("Are you done?");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent i=new Intent(Continental.this,CartActivity.class);
                                startActivity(i);
                            }
                        });

                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        });
        btnAdd=findViewById(R.id.btnAddC);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Database(getBaseContext()).AddToCart(new Order(
                        names,quantity,prices,"0"
                ));
                Log.d(TAG, "onClick: "+names+" "+prices+" "+quantity);
            }
        });
    }

    }

