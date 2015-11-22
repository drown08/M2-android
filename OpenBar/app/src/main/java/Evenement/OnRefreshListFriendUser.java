package Evenement;

import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.Toast;

import com.openbar.frappereauolivier.openbar.R;

import java.util.ArrayList;

import FragmentBar.TabContactFriends;
import Model.Contact;

/**
 * Created by Frappereau Olivier on 22/11/2015.
 */
public class OnRefreshListFriendUser implements SwipeRefreshLayout.OnRefreshListener {

    TabContactFriends context;
    SwipeRefreshLayout focusElement;

    public OnRefreshListFriendUser(TabContactFriends context, SwipeRefreshLayout mySRL) {
        this.context=context;
        this.focusElement=mySRL;
    }

    @Override
    public void onRefresh() {
        refreshFriends();
    }

    private void refreshFriends() {
        ArrayList<Contact> tmp = new ArrayList<Contact>();
        tmp.add(new Contact(R.drawable.powered_by_google_light,"Refresh 1","Machin veut un Refresh",true));
        tmp.add(new Contact(R.drawable.powered_by_google_light,"Refresh 2","Machin veut un truc",true));
        tmp.add(new Contact(R.drawable.powered_by_google_light,"Refresh 3","Machin Refresh un truc",true));
        tmp.add(new Contact(R.drawable.powered_by_google_light,"Refresh 4","Machin veut un truc",true));
        tmp.add(new Contact(R.drawable.powered_by_google_light,"Refresh 5","Machin veut Refresh truc",true));
        tmp.add(new Contact(R.drawable.powered_by_google_light,"Refresh 6","Machin Refresh un truc",true));
        tmp.add(new Contact(R.drawable.powered_by_google_light,"Refresh 7","Machin veut Refresh truc",true));
        tmp.add(new Contact(R.drawable.powered_by_google_light,"Refresh 8","Machin veut un truc",true));
        tmp.add(new Contact(R.drawable.powered_by_google_light,"Refresh 9","Machin veut Refresh truc",true));
        tmp.add(new Contact(R.drawable.powered_by_google_light,"Refresh 10","Refresh veut un truc",true));
        tmp.add(new Contact(R.drawable.powered_by_google_light,"Refresh 11","Machin veut un truc",true));
        tmp.add(new Contact(R.drawable.powered_by_google_light, "Refresh 12", "Machin Refresh un truc", true));
        onFriendLoadComplete(tmp);
    }


    private void onFriendLoadComplete(ArrayList<Contact> ln) {
        this.context.friendAdapter.UPFriendList(ln);
        this.focusElement.setRefreshing(false);
        Toast.makeText(context.getContext(), "Refresh friend ok", Toast.LENGTH_SHORT).show();
    }
}
