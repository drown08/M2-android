package Evenement;

import android.app.Activity;
import android.support.v7.app.AlertDialog;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.openbar.frappereauolivier.openbar.Activity.AddBarMapActivity;

/**
 * Created by Frappereau Olivier on 29/12/2015.
 */
public class OnClickMarker implements GoogleMap.OnMarkerClickListener {

    AddBarMapActivity myActivity;

    public OnClickMarker(Activity activity){
        this.myActivity = (AddBarMapActivity)activity;
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this.myActivity);
        alertDialogBuilder.setMessage("Voulez-vous ajouter ce bar ? ("+marker.getTitle()+")");
        alertDialogBuilder.setPositiveButton("d'accord", new DialogAddBar(this.myActivity,marker));
        alertDialogBuilder.setNegativeButton("pas d'accord", new DialogAddBar(this.myActivity,marker));
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
        return true;
    }
}
