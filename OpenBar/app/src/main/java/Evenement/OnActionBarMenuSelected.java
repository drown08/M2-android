package Evenement;

import android.app.Activity;
import android.view.MenuItem;
import android.widget.Toast;

import com.openbar.frappereauolivier.openbar.Activity.ConnexionActivity;
import com.openbar.frappereauolivier.openbar.R;

import Transaction.Transaction;

/**
 * Created by Frappereau Olivier on 08/11/2015.
 * Offer service for the action bar when user click on any times during using application
 */
public class OnActionBarMenuSelected {
    private Activity myActivity;
    private MenuItem myItem;

    public OnActionBarMenuSelected(Activity a, MenuItem mi) {
        this.myActivity = a;
        this.myItem = mi;
    }

    public boolean manageActionUsers() {
        switch (this.myItem.getItemId()) {
            case R.id.action_signout:// CLIQUE SUR LE HAMB -> SignOut
                Toast.makeText(this.myActivity.getApplicationContext(),"Déconnexion...",Toast.LENGTH_SHORT).show();
                Transaction signOut = new Transaction(myActivity, ConnexionActivity.class);
                signOut.exitAndRun();
                return true;

            case R.id.action_favorite : // CLIQUE SUR PROFIL
                Toast.makeText(this.myActivity.getApplicationContext(),"Profil",Toast.LENGTH_SHORT).show();
                return true;

            case R.id.action_refresh : // CLIQUE SUR REFRESH
                Toast.makeText(this.myActivity.getApplicationContext(),"Refresh", Toast.LENGTH_SHORT).show();
                return true;

            default:
                Activity tmp = (Activity) this.myActivity; //Cast in super class for the default case (return super.onOptionsItemSelected
                return tmp.onOptionsItemSelected(this.myItem);
        }
    }
}
