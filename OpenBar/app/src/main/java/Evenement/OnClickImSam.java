package Evenement;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.CheckBox;

import com.openbar.frappereauolivier.openbar.Activity.BarActivity;

import CommunicationServeur.CommunicationService;
import FragmentBar.TabBarInfos;

/**
 * Created by Frappereau Olivier on 02/01/2016.
 */
public class OnClickImSam  implements View.OnClickListener {

    BarActivity myActivity;
    TabBarInfos myFragment;
    CheckBox first;
    CheckBox second;

    public OnClickImSam(Fragment fragment, CheckBox b1, CheckBox b2) {
        this.myActivity = (BarActivity) fragment.getActivity();
        this.myFragment = (TabBarInfos)fragment;
        this.first = b1;
        this.second = b2;
    }

    @Override
    public void onClick(View v) {
        //Communication service : Update la base.
        CommunicationService setImSam = new CommunicationService(this.myFragment,this.myActivity,true,3);
        setImSam.addParams("ctrl","setImSam");
        setImSam.addParams("user","12"); //TODO : MEttre le current user
        setImSam.addParams("bar",myActivity.myBar.getNom());
        setImSam.addParams("value",String.valueOf(second.isChecked()));
        setImSam.sendToServer();
        setImSam.flush();
    }
}
