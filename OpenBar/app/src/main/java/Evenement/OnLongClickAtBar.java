package Evenement;

import android.app.Activity;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.openbar.frappereauolivier.openbar.Activity.FocusActivity;
import com.openbar.frappereauolivier.openbar.R;

import Adapter.BarAdapter;
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
        Bar bTmp = this.myActivity.barAdapter.removeBar(pTmp);
        Snackbar.make(myActivity.findViewById(R.id.coordinator_focus), "Delete a bar", Snackbar.LENGTH_LONG)
                .setAction("Undo", new OnUndoAddBar(myActivity,pTmp,bTmp))
                .show();
        //TODO : Faire la maj dans la future bdd ssi not undo
        return true;
    }
}
