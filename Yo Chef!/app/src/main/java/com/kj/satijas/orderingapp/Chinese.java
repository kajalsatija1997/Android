package com.kj.satijas.orderingapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kj.satijas.orderingapp.DatabaseClasses.Database;
import com.kj.satijas.orderingapp.Model.Order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Chinese extends AppCompatActivity {
    private static final String TAG ="HELLLLLLOOO";
    ListView listView;
    Button btnOrder,btnAdd;
    String quantity;
    String names;
    String prices;
    HashMap<String, String> hm;
    SimpleAdapter simpleAdapter;

    String[] snacks = new String[]{"NOODLES","SPRING ROLLS","SOUP","MANCHURIAN","FRIED RICE"};
    int[] pix = new int[]{R.drawable.noodles,R.drawable.rolls,R.drawable.soup,R.drawable.manchurian,R.drawable.fr};
    String[] price = new String[]{"110","140","95","150","90"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chinese);


       final List<HashMap<String, String>> aList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            hm = new HashMap<>();
            hm.put("listview_title", snacks[i]);
            hm.put("listview_image", Integer.toString(pix[i]));
            hm.put("des", price[i]);
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

        listView = findViewById(R.id.lv1);
        listView.setAdapter(simpleAdapter);

        btnOrder = findViewById(R.id.btn);
        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder1 = new AlertDialog.Builder(Chinese.this);
                builder1.setMessage("Are you done?");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                               Intent i=new Intent(Chinese.this,CartActivity.class);
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

        btnAdd=findViewById(R.id.btnAdd);
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

