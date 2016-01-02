package Evenement;

import android.support.v4.app.Fragment;
import android.view.View;

import com.openbar.frappereauolivier.openbar.Activity.BarActivity;

import CommunicationServeur.CommunicationService;
import FragmentBar.TabBarInfos;

/**
 * Created by Frappereau Olivier on 02/01/2016.
 */
public class OnClickImHere implements View.OnClickListener {

    BarActivity myActivity;
    TabBarInfos myFragment;

    public OnClickImHere(Fragment fragment) {
        this.myActivity = (BarActivity) fragment.getActivity();
        this.myFragment = (TabBarInfos) fragment;
    }

    @Override
    public void onClick(View v) {
        CommunicationService checkPresentAtOtherBar = new CommunicationService(this.myFragment,this.myActivity,true,2);
        checkPresentAtOtherBar.addParams("ctrl","checkPresentAtOtherBar");
        checkPresentAtOtherBar.addParams("user","12");//TODO : Mettre le current user
        checkPresentAtOtherBar.addParams("bar",BarActivity.myBar.getNom());
        checkPresentAtOtherBar.sendToServer();
        checkPresentAtOtherBar.flush();
    }
}
