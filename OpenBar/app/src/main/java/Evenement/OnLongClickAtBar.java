package Evenement;

import android.app.Activity;
import android.view.View;
import android.widget.Toast;

import com.openbar.frappereauolivier.openbar.Activity.FocusActivity;

import Adapter.BarAdapter;

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
        Toast.makeText(v.getContext(), "Delete bar " + this.myBVH.getAdapterPosition(), Toast.LENGTH_LONG).show();
        this.myActivity.barAdapter.removeBar(this.myBVH.getAdapterPosition());
        //TODO : Faire la maj dans la future bdd
        return true;
    }
}
