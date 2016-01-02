package Evenement;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Toast;

import com.openbar.frappereauolivier.openbar.Activity.AddBarMapActivity;
import com.openbar.frappereauolivier.openbar.R;

/**
 * Created by Frappereau Olivier on 30/12/2015.
 */
public class OnClickAddBarByForm  implements View.OnClickListener {

    AddBarMapActivity myActivity;

    public OnClickAddBarByForm(Activity activity) {
        this.myActivity = (AddBarMapActivity) activity;
    }

    @Override
    public void onClick(View v) {
        //Mettre en place un dialog avec saisir une adresse, ca créer un marker à l'endroit de la saisie
        //Puis sa ouvre un dialogue avec nom du bar et on l'inscrit
        Toast.makeText(this.myActivity.getApplicationContext(), "TADAM !", Toast.LENGTH_SHORT).show();
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this.myActivity);
        alertDialogBuilder.setMessage("Ajouter l'adresse");
        alertDialogBuilder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                // sign in the user ...
                dialog.cancel();
            }
        });
        View v_iew = v.inflate(this.myActivity.getApplicationContext(),R.layout.dialog_add_address_bar,null);
        alertDialogBuilder.setView(v_iew);
        alertDialogBuilder.setPositiveButton("Valider", new OnTapeAdressBar(this.myActivity,this.myActivity,v_iew));
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
//        View vv = alertDialog.getListView().getFocusedChild();
//        Toast.makeText(this.myActivity.getApplicationContext(),vv.toString(),Toast.LENGTH_SHORT).show();
    }
}
