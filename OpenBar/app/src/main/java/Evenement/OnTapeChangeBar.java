package Evenement;

import android.content.DialogInterface;
import android.support.v4.app.Fragment;

import com.openbar.frappereauolivier.openbar.Activity.BarActivity;

import CommunicationServeur.CommunicationService;
import FragmentBar.TabBarInfos;

/**
 * Created by Frappereau Olivier on 02/01/2016.
 */
public class OnTapeChangeBar implements DialogInterface.OnClickListener {
    BarActivity myActivity;
    TabBarInfos myFragment;
    String otherBar;

    public OnTapeChangeBar(Fragment fragment, String bar) {
        this.myFragment = (TabBarInfos) fragment;
        this.myActivity = (BarActivity) fragment.getActivity();
        this.otherBar = bar;
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        CommunicationService updateCurrentBar = new CommunicationService(this.myFragment,this.myActivity,true,5);
        updateCurrentBar.addParams("ctrl","updateCurrentBar");
        updateCurrentBar.addParams("bar",this.myActivity.myBar.getNom());
        updateCurrentBar.addParams("other_bar",this.otherBar);
        updateCurrentBar.addParams("user","12");//TODO : mettre le current User
        updateCurrentBar.sendToServer();
        updateCurrentBar.flush();
    }
}
