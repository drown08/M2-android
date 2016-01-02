package Evenement;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.openbar.frappereauolivier.openbar.Activity.BarActivity;

import CommunicationServeur.CommunicationService;
import FragmentBar.TabBarContact;

/**
 * Created by Frappereau Olivier on 02/01/2016.
 */
public class OnClickAtSocialAction implements View.OnClickListener {

    BarActivity myActivity;
    TabBarContact myFragment;
    int action;
    String nomContact;

    public OnClickAtSocialAction(Activity activity,Fragment fragment, int choice, String target){
        this.myActivity = (BarActivity) activity;
        this.myFragment = (TabBarContact) fragment;
        this.action = choice;
        this.nomContact = target;
    }

    @Override
    public void onClick(View v) {
        Log.d("teyyyst-","here");
        //TODO : Si ami non, si pas ami alors proposer d'envoyer une photo
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this.myActivity);
        alertDialogBuilder.setMessage("Vous avez ajouter une photo ? ");
        alertDialogBuilder.setNegativeButton("Non", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                // sign in the user ...
                dialog.cancel();
            }
        });
        alertDialogBuilder.setPositiveButton("Oui",new OnDialogAddPicture(this.myActivity));
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
        CommunicationService sendNotif = new CommunicationService(this.myFragment,this.myActivity,true,1);
        sendNotif.addParams("ctrl","sendNotif");
        sendNotif.addParams("to_user","cathy777");//TODO : Réccupérer le nom du user cible
        sendNotif.addParams("from_user","12");//TODO : Réccupérer le current user
        switch (this.action){
            case 1 :
                sendNotif.addParams("object","action1");
                break;
            case 2 :
                sendNotif.addParams("object","action2");
                break;
            case 3 :
                sendNotif.addParams("object","action3");
                break;
        }
        sendNotif.sendToServer();
        sendNotif.flush();
        Toast.makeText(this.myActivity.getApplicationContext(), "Action "+String.valueOf(this.action)+" : notif a " + this.nomContact, Toast.LENGTH_SHORT).show();
    }
}
