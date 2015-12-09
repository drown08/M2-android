package Evenement;

import android.app.Activity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.widget.Toast;

import com.openbar.frappereauolivier.openbar.Activity.FocusActivity;
import com.openbar.frappereauolivier.openbar.R;

import java.util.ArrayList;

import Model.Bar;

/**
 * Created by Frappereau Olivier on 14/11/2015.
 */
public class OnRefreshListBar implements SwipeRefreshLayout.OnRefreshListener {
    FocusActivity myActivity;
    SwipeRefreshLayout focusElement;
    public OnRefreshListBar(Activity activity, SwipeRefreshLayout focusElement) {
        this.myActivity = (FocusActivity) activity;
        this.focusElement = focusElement;
    }

    @Override
    public void onRefresh() {
        // Refresh items
        refreshItems();
    }

    private void refreshItems() {
        // Load items dans l'adapter (I.E refaire la liste depuis la bdd)
        // ...
        /*for(int i =0; i<1000000; i++) {
            i = i+1;
        }*/
        ArrayList<Bar> tmp = new ArrayList<Bar>();
        //TODO : Service qui appelle la communication Serveur avec comme info UserPseudo
        // Et comme serviceDuServeur : donne moi la liste des bars de UserPseudo
        tmp.add(new Bar("Bar 1","Happy Hours Ref", R.drawable.options_test));
        tmp.add(new Bar("Bar 2","Diffuse match rugby Ref",R.drawable.options_test));
        tmp.add(new Bar("Bar 3","Rien Ref",R.drawable.options_test));
        tmp.add(new Bar("Bar 4","3 amis ici Ref",R.drawable.options_test));
        tmp.add(new Bar("Bar 5","Ajouter hier Ref",R.drawable.options_test));
        tmp.add(new Bar("Bar 6", "Folie ! Ref", R.drawable.options_test));
        tmp.add(new Bar("Bar 7","Diffuse match basket Ref",R.drawable.options_test));
        tmp.add(new Bar("Bar 8","Diffuse match Hockey Ref",R.drawable.options_test));

       // CommunicationService getBarsOfUser = new CommunicationService(this.myActivity,this.myActivity,true,2);
       // getBarsOfUser.addParams("ctrl", "bars");//getBarOfCurrentUser à remplacer à la place
       // getBarsOfUser.addParams("id_user", String.valueOf(this.myActivity.currentUser.getRefImg()));
       // getBarsOfUser.sendToServer();
       // getBarsOfUser.flush();
        // Load complete
        Log.d("TAILLE-LIST-BAR-AV", String.valueOf(this.myActivity.myBars.size()));
        onItemsLoadComplete(tmp);
        Log.d("TAILLE-LIST-BAR-AV", String.valueOf(this.myActivity.myBars.size()));
    }

   private void onItemsLoadComplete(ArrayList<Bar> lb) {
        // Update the adapter and notify data set changed
        // ...

        this.myActivity.barAdapter.UPBarList(lb);
//       this.myActivity.barAdapter.notify();
        // Stop refresh animation
        this.focusElement.setRefreshing(false);
       Toast.makeText(this.myActivity.getApplicationContext(), "Refreshing done", Toast.LENGTH_SHORT).show();
       //Go to the top of the list

    }
}
