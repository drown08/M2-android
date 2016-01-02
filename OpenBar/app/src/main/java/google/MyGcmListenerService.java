package google;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
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
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0/*Wow, Request code*/, intent, PendingIntent.FLAG_ONE_SHOT); //LOL?

        //On va try d'ajouter un bouton ONLY pour la vue sur la montre connectée
        // Create the action
        NotificationCompat.Action action =
                new NotificationCompat.Action.Builder(R.drawable.ic_media_play,
                        "ACCEPT", pendingIntent)
                        .build();

        // Specify the 'big view' content to display the long
        // event description that may not fit the normal content text.
        NotificationCompat.BigTextStyle bigStyle = new NotificationCompat.BigTextStyle();
        bigStyle.bigText("voici le surplux de text qui doit etre afficher psk sur montre ?");


        // Build the notification and add the action via WearableExtender
        Notification notification =
                new NotificationCompat.Builder(this)
                        .setContentTitle("testTitre")
                        .setContentText("testText")
                        .extend(new NotificationCompat.WearableExtender().addAction(action))
                        .build();

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);//On emet un son de notif
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this) //ON veut une instance de ce service en notif ?
                                                            .setSmallIcon(R.mipmap.ic_launcher)
                                                            .setContentTitle(title)
                                                            .setContentText(message)
                                                            .setAutoCancel(true)
                                                            .setSound(defaultSoundUri)
                                                            .setContentIntent(pendingIntent)
                                                            .addAction(R.drawable.ic_media_play, "PLAY", pendingIntent)
                                                            .setStyle(bigStyle);
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(1,notificationBuilder.build());
        //NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE); //On réccupère une instance du manager de notif, et on indique quel service
        //notificationManager.notify(1,notificationBuilder.build());//On envoit 1 notification qu'on construit avec build


    }
}
