package google;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.android.gms.gcm.GcmListenerService;
import com.openbar.frappereauolivier.openbar.Activity.FocusActivity;
import com.openbar.frappereauolivier.openbar.R;

/**
 * Created by Frappereau Olivier on 24/11/2015.
 */
public class MyGcmListenerService extends GcmListenerService {
    private static final String TAG = "MyGcmListenerService";

    @Override
    public void onMessageReceived(String from, Bundle data){
        String message = data.getString("message");
        String title = data.getString("title");

        Log.d(TAG,"From :"+from);
        Log.d(TAG,"Msg :"+message);

        sendNotification(message,title);
    }

    private void sendNotification(String message, String title){
        Intent intent = new Intent(this, FocusActivity.class); //ON va dire que la push amène directement là pour le moment.
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //Quelle est donc la nature de cette option?
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0/*Wow, Request code*/,intent,PendingIntent.FLAG_ONE_SHOT); //LOL?

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);//On emet un son de notif
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this) //ON veut une instance de ce service en notif ?
                                                            .setSmallIcon(R.mipmap.ic_launcher)
                                                            .setContentTitle(title)
                                                            .setContentText(message)
                                                            .setAutoCancel(true)
                                                            .setSound(defaultSoundUri)
                                                            .setContentIntent(pendingIntent);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE); //On réccupère une instance du manager de notif, et on indique quel service
        notificationManager.notify(1,notificationBuilder.build());//On envoit 1 notification qu'on construit avec build


    }
}
