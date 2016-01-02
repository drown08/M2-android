package Evenement;

import android.app.Activity;
import android.content.DialogInterface;
import android.widget.Toast;

import com.google.android.gms.maps.model.Marker;
import com.openbar.frappereauolivier.openbar.Activity.AddBarMapActivity;

import CommunicationServeur.CommunicationService;

/**
 * Created by Frappereau Olivier on 30/12/2015.
 */
public class DialogAddBar implements DialogInterface.OnClickListener {

    AddBarMapActivity myActivity;
    Marker marker;

    public DialogAddBar(Activity activity, Marker marker){
        this.myActivity = (AddBarMapActivity) activity;
        this.marker = marker;
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        switch (which){
            case -1 :
                CommunicationService addBarToCurrentUser = new CommunicationService(this.myActivity,this.myActivity,true,2);
                addBarToCurrentUser.addParams("ctrl","addBarToUser");
                addBarToCurrentUser.addParams("id_user","12");
                addBarToCurrentUser.addParams("nom_bar",marker.getTitle());
                addBarToCurrentUser.sendToServer();
                addBarToCurrentUser.flush();
                marker.setVisible(false);
                marker.remove();
                //marker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN));
                break;
            case -2:
                Toast.makeText(myActivity.getApplicationContext(),"Rien a ajouter",Toast.LENGTH_SHORT).show();
                dialog.cancel();
                break;
        }
    }
}
