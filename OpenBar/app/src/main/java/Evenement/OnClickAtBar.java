package Evenement;

import android.app.Activity;
import android.view.View;
import android.widget.Toast;

import Adapter.BarAdapter;

/**
 * Created by Frappereau Olivier on 12/11/2015.
 */
public class OnClickAtBar implements View.OnClickListener {
    BarAdapter.BarViewHolder myBVH;
    Activity myActivity;

    public OnClickAtBar(BarAdapter.BarViewHolder bvh, Activity activity){
        this.myBVH=bvh;
        this.myActivity=activity;
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(v.getContext(), "Item click at " + this.myBVH.getAdapterPosition(), Toast.LENGTH_LONG).show();
        //Transaction goBar = new Transaction(this.myActivity, ForgetMailActivity.class); //JUST FOR EXEMPLE
        //goBar.runWithoutExit();
    }
}
