package Evenement;

import android.app.Activity;
import android.view.MenuItem;
import android.widget.Toast;

import com.openbar.frappereauolivier.openbar.R;

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
            case R.id.action_settings :
                Toast.makeText(this.myActivity.getApplicationContext(),"héhé",Toast.LENGTH_SHORT).show();
                return true;

            case R.id.action_favorite :
                Toast.makeText(this.myActivity.getApplicationContext(),"huhu",Toast.LENGTH_SHORT).show();
                return true;

            default:
                Activity tmp = (Activity) this.myActivity; //Cast in super class for the default case (return super.onOptionsItemSelected
                return tmp.onOptionsItemSelected(this.myItem);
        }
    }
}
