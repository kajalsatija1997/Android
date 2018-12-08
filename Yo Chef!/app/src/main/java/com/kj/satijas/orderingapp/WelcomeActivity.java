package com.kj.satijas.orderingapp;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity {
    ImageView ivChef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int TIME_OUT = 4000;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        ivChef=findViewById(R.id.ivChef);
        Animation myAnim= AnimationUtils.loadAnimation(this,R.anim.mytransitiom);
        ivChef.startAnimation(myAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i=new Intent(WelcomeActivity.this,login.class);
                startActivity(i);
                finish();
            }
        },TIME_OUT);


    }
}
