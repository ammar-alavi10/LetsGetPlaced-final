package com.ammar.letsgetplaced;

import android.animation.ObjectAnimator;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class Home extends AppCompatActivity {

    Button companybttn,userinfobttn,logoutbttn,notificationsbttn;

    public static final String Channel_id = "sc";
    public static final String Channel_name = "sc";
    public static final String Channel_desc = "sc";
    private Handler mHandler = new Handler();
    private Runnable mUpdateTimeTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(Channel_id,Channel_name,NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription(Channel_desc);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        companybttn = findViewById(R.id.companybutton);
        userinfobttn = findViewById(R.id.UserInfo);
        notificationsbttn = findViewById(R.id.Notifications);
        logoutbttn = findViewById(R.id.Logout);

        companybttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ObjectAnimator flip = ObjectAnimator.ofFloat(view, "rotationX", 0f, 360f);
                flip.setDuration(300);
                flip.start();

                mUpdateTimeTask = new Runnable() {
                    public void run() {
                        Intent intent = new Intent(Home.this,CompanyInfo.class);
                        startActivity(intent);
                    }
                };
                mHandler.postDelayed(mUpdateTimeTask,300);
            }
        });

        userinfobttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ObjectAnimator flip = ObjectAnimator.ofFloat(view, "rotationX", 0f, 360f);
                flip.setDuration(300);
                flip.start();
                mUpdateTimeTask = new Runnable() {
                    public void run() {
                        Intent intent = new Intent(Home.this,UserInfo1.class);
                        startActivity(intent);
                    }
                };
                mHandler.postDelayed(mUpdateTimeTask,300);

            }
        });

        notificationsbttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ObjectAnimator flip = ObjectAnimator.ofFloat(view, "rotationX", 0f, 360f);
                flip.setDuration(300);
                flip.start();
                mUpdateTimeTask = new Runnable() {
                    public void run() {
                        Intent intent = new Intent(Home.this,Notifs.class);
                        startActivity(intent);
                    }
                };
                mHandler.postDelayed(mUpdateTimeTask,300);

            }
        });

        logoutbttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                ObjectAnimator flip = ObjectAnimator.ofFloat(view, "rotationX", 0f, 360f);
                flip.setDuration(300);
                flip.start();
                mUpdateTimeTask = new Runnable() {
                    public void run() {
                        FirebaseAuth.getInstance().signOut();
                        Intent intent = new Intent(Home.this,LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                    }
                };
                mHandler.postDelayed(mUpdateTimeTask,300);

            }
        });
    }

 /*   public void displayNotification(Context context, String title, String message){

        notificationchannel();

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context,Home.Channel_id)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_MAX);

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(context);
        managerCompat.notify(1,mBuilder.build());
    }

    private void notificationchannel() {

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(Channel_id,Channel_name,NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription(Channel_desc);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
    }
*/

    public void ShowNotification(Context context,String title,String messageBody) {
        Intent intent = new Intent(context, Notifs.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT | PendingIntent.FLAG_UPDATE_CURRENT);

        String channelId = getString(R.string.default_notification_channel_id);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(context, channelId)
                        .setSmallIcon(R.drawable.ic_launcher)
                        .setContentTitle(title)
                        .setContentText(messageBody)
                        .setAutoCancel(true)
                        .setSound(defaultSoundUri)
                        .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);
        }

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }

}
