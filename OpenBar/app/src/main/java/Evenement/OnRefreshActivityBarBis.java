package Evenement;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.util.Log;
import android.widget.Toast;

import com.openbar.frappereauolivier.openbar.R;

import java.util.ArrayList;

import FragmentBar.TabBarContact;
import Model.Contact;

/**
 * Created by Frappereau Olivier on 16/11/2015.
 */
public class OnRefreshActivityBarBis implements OnRefreshListener {
    TabBarContact context;
    SwipeRefreshLayout focusElement;

    public OnRefreshActivityBarBis(TabBarContact context, SwipeRefreshLayout focusElement) {
        this.context =  context;
        this.focusElement = focusElement;
    }
    @Override
    public void onRefresh() {
        //TODO : Appeller la com' pour aller chercher le bar en bdd et up ses infos
        //TODO : Idem pour les listes des personnnes présentes dans le bars
        ArrayList<Contact> listeTmp = new ArrayList<Contact>();
        for(int i = 0; i<6; i++) {
            boolean rd = i%2==0;
            Contact tmp = new Contact(R.drawable.ic_tick,"Pierre"+String.valueOf(i),"Paul"+String.valueOf(i),rd);
            Log.d("CreateContact", tmp.getPrenom());
            listeTmp.add(tmp);
        }
        Log.d("SizeBefore",String.valueOf(listeTmp.size()));
        this.context.adapteFriends.refreshListing(listeTmp,true);
        this.context.adapteOthers.refreshListing(listeTmp,false);
        this.focusElement.setRefreshing(false);
        Toast.makeText(context.getContext(), "Refresh ok bis", Toast.LENGTH_SHORT).show();
    }
}
