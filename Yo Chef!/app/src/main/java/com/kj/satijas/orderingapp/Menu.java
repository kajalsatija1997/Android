package com.kj.satijas.orderingapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Menu extends AppCompatActivity {
    ListView listView;
    String CategoryId;
    String[] dishes = new String[]{"INDIAN", "CHINESE", "CONTINENTAL", "SOUTH INDIAN"};
    int[] img = new int[]{R.drawable.m1, R.drawable.m2, R.drawable.m3, R.drawable.m4};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        List<HashMap<String, String>> aList = new ArrayList<>();
        for (int j = 0; j < 4; j++) {
            HashMap<String, String> hm = new HashMap<>();
            hm.put("listview_title", dishes[j]);
            hm.put("listview_image", Integer.toString(img[j]));
            aList.add(hm);
        }

        String[] from = {"listview_image", "listview_title"};
        int[] to = {R.id.listview_image, R.id.listview_item_title};

        final SimpleAdapter simpleAdapter = new SimpleAdapter(getBaseContext(), aList, R.layout.menulistview, from, to);

        listView = findViewById(R.id.lv4);
        listView.setAdapter(simpleAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Intent i = new Intent(view.getContext(), Indian.class);
                    i.putExtra("dishes", listView.getItemAtPosition(position).toString());
                    startActivityForResult(i, 0);
                }
                if (position == 1) {
                    Intent i = new Intent(view.getContext(), Chinese.class);
                    i.putExtra("dishes", listView.getItemAtPosition(position).toString());
                    startActivityForResult(i, 1);
                }
                if (position == 2) {
                    Intent i = new Intent(view.getContext(), Continental.class);
                    i.putExtra("dishes", listView.getItemAtPosition(position).toString());
                    startActivityForResult(i, 2);
                }
                if (position == 3) {
                    Intent i = new Intent(view.getContext(), SouthIndian.class);
                    i.putExtra("dishes", listView.getItemAtPosition(position).toString());
                    startActivityForResult(i, 3);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Do you want to cancel the order?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Menu.this.finish();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }
}