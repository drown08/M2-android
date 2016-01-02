package Evenement;

import android.app.Activity;
import android.view.View;
import android.widget.Toast;

import com.openbar.frappereauolivier.openbar.Activity.BarActivity;
import com.openbar.frappereauolivier.openbar.Activity.FocusActivity;

import java.util.ArrayList;
import java.util.Arrays;

import Adapter.BarAdapter;
import CommunicationServeur.AsyncTaskResponse;
import CommunicationServeur.CommunicationService;
import Model.Bar;
import Transaction.Transaction;

/**
 * Created by Frappereau Olivier on 12/11/2015.
 */
public class OnClickAtBar implements View.OnClickListener, AsyncTaskResponse {
    BarAdapter.BarViewHolder myBVH;
    FocusActivity myActivity;

    public OnClickAtBar(BarAdapter.BarViewHolder bvh, Activity activity){
        this.myBVH=bvh;
        this.myActivity= (FocusActivity) activity;
    }

    @Override
    public void onClick(View v) {
        int focus = this.myBVH.getAdapterPosition();
        CommunicationService test = new CommunicationService(this,this.myActivity,false,1);
        test.addParams("ctrl","getKey");
        test.addParams("id_user","2");
        test.sendToServer();
        test.flush();
        Toast.makeText(v.getContext(), "Item click at " + focus, Toast.LENGTH_LONG).show();
        Transaction goBar = new Transaction(this.myActivity, BarActivity.class);
        Bar barFocused = this.myActivity.getBarByRange(focus);
        String param = barFocused.getNom();
        goBar.addExtras("bar",new ArrayList<String>(Arrays.asList(param)));
        goBar.runWithoutExit();
    }

    @Override
    public void processFinish(String output, int flag) {
        switch (flag){
            case 1 :
                Toast.makeText(this.myActivity.getBaseContext(), "notifOrder send to server", Toast.LENGTH_LONG).show();
                break;
        }
    }
}
