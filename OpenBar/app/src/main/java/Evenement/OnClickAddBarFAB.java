package Evenement;

import android.app.Activity;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.Toast;

import com.openbar.frappereauolivier.openbar.R;

/**
 * Created by Frappereau Olivier on 14/11/2015.
 */
public class OnClickAddBarFAB implements View.OnClickListener {
    Activity myActivity;
    FloatingActionButton myMainFAB;
    FloatingActionButton myFirstMinFAB;
    FloatingActionButton mySecondMiniFAB;
    FloatingActionButton myTarget;
    Boolean isShow;

    public OnClickAddBarFAB(Activity activity, FloatingActionButton target) {
        this.myActivity = activity;
        this.myTarget = target;
        this.isShow = false;
        this.myMainFAB = (FloatingActionButton) myActivity.findViewById(R.id.fab);
        this.myFirstMinFAB = (FloatingActionButton) myActivity.findViewById(R.id.fab_mini_1);
        this.mySecondMiniFAB = (FloatingActionButton) myActivity.findViewById(R.id.fab_mini_2);
    }

    @Override
    public void onClick(View v) {
        switch ((this.myTarget.getId()))
        {
            case R.id.fab :
                this.isShow = this.myFirstMinFAB.isShown();
                Toast.makeText(this.myActivity.getApplicationContext(),"Ajout bar",Toast.LENGTH_SHORT).show();
                if(this.isShow) {
                    this.myFirstMinFAB.hide();
                    this.mySecondMiniFAB.hide();
                } else {
                    this.myFirstMinFAB.show();
                    this.mySecondMiniFAB.show();
                }

                //www;imkan.fr
                break;
            case R.id.fab_mini_1 :
                Toast.makeText(this.myActivity.getApplicationContext(),"Via formulaire",Toast.LENGTH_SHORT).show();
                break;
            case R.id.fab_mini_2 :
                Toast.makeText(this.myActivity.getApplicationContext(),"Via locate",Toast.LENGTH_SHORT).show();
                break;

        }

    }
}
