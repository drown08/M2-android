package Evenement;

import android.app.Activity;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.openbar.frappereauolivier.openbar.Activity.FocusActivity;
import com.openbar.frappereauolivier.openbar.R;

import Adapter.BarAdapter;
import CommunicationServeur.CommunicationService;
import Model.Bar;

/**
 * Created by Frappereau Olivier on 12/11/2015.
 */
public class OnLongClickAtBar implements View.OnLongClickListener {
    BarAdapter.BarViewHolder myBVH;
    FocusActivity myActivity;

    public OnLongClickAtBar(BarAdapter.BarViewHolder bvh, Activity activity){
        this.myBVH=bvh;
        this.myActivity =(FocusActivity) activity;
    }
    @Override
    public boolean onLongClick(View v) {
        int pTmp = this.myBVH.getAdapterPosition();
        //TODO : MESSAGE de confirmation ?
        Bar bTmp = this.myActivity.barAdapter.removeBar(pTmp);
        //TODO : Delete le bar du current User en base
        CommunicationService removeBar = new CommunicationService(this.myActivity,this.myActivity,true,3);
        removeBar.addParams("ctrl","removeBarToUser");
        removeBar.addParams("nom_bar",bTmp.getNom());
        removeBar.addParams("id_user","12");
        removeBar.sendToServer();
        removeBar.flush();
        Snackbar.make(myActivity.findViewById(R.id.coordinator_focus), "Delete a bar", Snackbar.LENGTH_LONG)
                .setAction("Undo", new OnUndoAddBar(myActivity,pTmp,bTmp))
                .show();
        //TODO : Faire la maj dans la future bdd ssi not undo
        return true;
    }
}
