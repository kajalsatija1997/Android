package com.kj.satijas.yochefserverchef.Service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.kj.satijas.yochefserverchef.Common.Common;
import com.kj.satijas.yochefserverchef.Model.Request;
import com.kj.satijas.yochefserverchef.Order_Management;
import com.kj.satijas.yochefserverchef.R;

import java.util.Random;

public class ListenOrders extends Service implements ChildEventListener {
    FirebaseDatabase db;
    DatabaseReference orders;

    public static final String NOTIFICATION_CHANNEL_ID = "10001";

    public ListenOrders() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        orders.addChildEventListener(this);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        db=FirebaseDatabase.getInstance();
        orders=db.getReference("Requests");
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
        //trigger here
        Request request=dataSnapshot.getValue(Request.class);
        if(request.getStatus().equals("0"))
            showNotification(dataSnapshot.getKey(),request);

    }

    @Override
    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

    }

    private void showNotification(String key, Request request) {
        Intent intent=new Intent(getBaseContext(),Order_Management.class);
        PendingIntent contentIntent=PendingIntent.getActivity(getBaseContext(),0,intent,0 );

        NotificationCompat.Builder builder=new NotificationCompat.Builder(getBaseContext());
        //NotificationChannel mChannel=new NotificationChannel("com.kj.satijas.yochefserver.Service","YoChef",NotificationManager.IMPORTANCE_HIGH);
        builder.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setTicker("YoChef")
                .setContentInfo("New Order")
                .setContentText("You have new order #"+key  )
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(contentIntent);

        NotificationManager manager=(NotificationManager)getBaseContext().getSystemService(Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O)
        {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "NOTIFICATION_CHANNEL_NAME", importance);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            assert manager != null;
            builder.setChannelId(NOTIFICATION_CHANNEL_ID);
            manager.createNotificationChannel(notificationChannel);
        }
        assert manager != null;

        int randomInt=new Random().nextInt(9999-1)+1;
        manager.notify(randomInt,builder.build());

    }

    @Override
    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

    }

    @Override
    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
}
