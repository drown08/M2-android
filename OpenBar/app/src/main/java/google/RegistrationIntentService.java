package google;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;
import com.openbar.frappereauolivier.openbar.R;

import CommunicationServeur.ConnectionServer;

/**
 * Created by Frappereau Olivier on 24/11/2015.
 */
public class RegistrationIntentService extends IntentService {

    private static final String TAG = "RegIntentService";
    private static final String KEY_TOKEN = "gcm_token";

    public RegistrationIntentService(){
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        InstanceID instanceID=null;
        try{
            //Si jamais problème de synchro lors de multiples refresh, il faut être synchro avant d'executer cette section.
            synchronized (TAG) {
                instanceID = InstanceID.getInstance(this);
                String token = instanceID.getToken(getString(R.string.google_id_app), GoogleCloudMessaging.INSTANCE_ID_SCOPE,null);
               // String token = instanceID.getToken("332561504331", GoogleCloudMessaging.INSTANCE_ID_SCOPE,null);
                Log.i(TAG, "coucou av : " + instanceID.getId());
                //Thread.sleep(5000);
                //String token = instanceID.getToken(getString(R.string.google_id_app), "GCM");

                //Log.i(TAG,"GCM Registration token"+token);


                //Si l'opération a déjà était faite, pas la peine de réitérer
               // if(!sharedPreferences.getBoolean(QuickstartPreferences.SENT_TOKEN_TO_SERVER,false)){
                    sendRegistrationToServer(token);

                //}


                //Il faut indiquer qu'on a envoyer au serveur
                sharedPreferences.edit().putBoolean(QuickstartPreferences.SENT_TOKEN_TO_SERVER,true).apply();
            }
        } catch(Exception e) {
            Log.d(TAG, "Failed to complete token refresh", e);
        }
        Log.i(TAG, "coucou ap : " + instanceID.getId());
        Intent registrationComplete = new Intent(QuickstartPreferences.REGISTRATION_COMPLETE);
        LocalBroadcastManager.getInstance(this).sendBroadcast(registrationComplete);

    }

    private void sendRegistrationToServer(String token) {
       // CommunicationService sendToken = new CommunicationService(this.context,this.activity,false,1);
        Log.i(TAG, "Coucou pendant :"+token);
        ConnectionServer sendToken = new ConnectionServer();
        sendToken.setUrl("http://drown88801.freeheberg.org/serveurOpenBar/server.php?id=10&ctrl=addKey&token_user="+token+"&id_user=12");
        //sendToken.setUrl("http://149.202.51.217/serveurOpenBar/server.php?id=10&ctrl=addKey&token_user="+token+"&id_user=2");
        sendToken.post();
        sendToken.close();
    }
}
