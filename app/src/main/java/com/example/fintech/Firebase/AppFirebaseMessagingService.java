package com.example.fintech.Firebase;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;

import com.example.fintech.MenuActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class AppFirebaseMessagingService extends FirebaseMessagingService {
    int badge_count ; // 브로드캐스트 수행순서
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        Map<String, String> pushDataMap = remoteMessage.getData();
         sendNotification(pushDataMap);
    }

    @Override
    public void onNewToken(@NonNull String token) {

        Log.d(TAG, "Refreshed token: " + token);
        sendRegistrationToServer(token);

    }

    private void sendRegistrationToServer(String token){
        // 토큰을 서버에게 전송!

    }

    private void sendNotification(Map<String, String> data){
        Intent intent = new Intent(this, MenuActivity.class) ;
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent contenntIntent = PendingIntent.getActivity(this, 0,intent, PendingIntent.FLAG_ONE_SHOT);
        Uri soundUrl = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                        .setContentTitle(data.get("title"))
                        .setContentText(data.get("msg")) // 서버에서 받은 데이터를 공지!
                        .setAutoCancel(true)
                        .setSound(soundUrl)
                        .setContentIntent(contenntIntent);

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, notificationBuilder.build());
    }




}
