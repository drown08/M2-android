package Evenement;

import android.app.Activity;
import android.view.View;
import android.widget.Toast;

import com.openbar.frappereauolivier.openbar.Activity.FocusActivity;

import Model.Bar;

/**
 * Created by Frappereau Olivier on 14/11/2015.
 */
public class OnUndoAddBar implements View.OnClickListener {
    FocusActivity myActivity;
    int positionOld;
    Bar bOld;

    public OnUndoAddBar(Activity activity, int positionOld, Bar bOld) {
        this.myActivity =(FocusActivity) activity;
        this.positionOld = positionOld;
        this.bOld = bOld;
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(myActivity, "undo", Toast.LENGTH_LONG).show();
        myActivity.barAdapter.addBar(this.positionOld,this.bOld);

    }
}
