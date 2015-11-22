package FragmentBar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.openbar.frappereauolivier.openbar.R;

import java.util.ArrayList;

import Adapter.FriendAdapter;
import Evenement.OnRefreshListFriendUser;
import Model.Contact;

/**
 * Created by Frappereau Olivier on 22/11/2015.
 */
public class TabContactFriends extends Fragment {
    public View mainV;
    RecyclerView friendList;
    public FriendAdapter friendAdapter;
    ArrayList<Contact> myFriends;
    SwipeRefreshLayout mySRL;

    public TabContactFriends() {
        super();
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saveInstanceState) {
        this.mainV = inflater.inflate(R.layout.tab_user_friends,container,false);
        customTheView();
        return this.mainV;
    }

    private void customTheView() {
        setFriendOfNotifications();
    }

    private void setFriendOfNotifications() {
        friendList = (RecyclerView) this.mainV.findViewById(R.id.cardListFriend);
        friendList.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(this.getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        friendList.setLayoutManager(llm);

        myFriends = setFriendNotif();

        this.friendAdapter = new FriendAdapter(myFriends,this.getContext());
        this.friendList.setAdapter(this.friendAdapter);

        mySRL = (SwipeRefreshLayout) this.mainV.findViewById(R.id.swipeRefreshLayoutProfilFriend);
        mySRL.setOnRefreshListener(new OnRefreshListFriendUser(this,mySRL));

        friendList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                boolean enable = false;
                if (friendList != null && friendList.getChildCount() > 0) {
                    boolean topOfFirstItemVisible = friendList.getChildAt(0).getTop() == 0;
                    enable = topOfFirstItemVisible;
                }
                Log.d("MethodCalledBisBis", String.valueOf(enable));
                mySRL.setEnabled(enable);
            }
        });

    }

    private ArrayList<Contact> setFriendNotif() {
        ArrayList<Contact> tmp = new ArrayList<Contact>();
        tmp.add(new Contact(R.drawable.powered_by_google_light,"Notification 1","Machin veut un truc",true));
        tmp.add(new Contact(R.drawable.powered_by_google_light,"Notification 2","Machin veut un truc",true));
        tmp.add(new Contact(R.drawable.powered_by_google_light,"Notification 3","Machin veut un truc",true));
        tmp.add(new Contact(R.drawable.powered_by_google_light,"Notification 4","Machin veut un truc",true));
        tmp.add(new Contact(R.drawable.powered_by_google_light,"Notification 5","Machin veut un truc",true));
        tmp.add(new Contact(R.drawable.powered_by_google_light,"Notification 6","Machin veut un truc",true));
        tmp.add(new Contact(R.drawable.powered_by_google_light,"Notification 7","Machin veut un truc",true));
        tmp.add(new Contact(R.drawable.powered_by_google_light,"Notification 8","Machin veut un truc",true));
        tmp.add(new Contact(R.drawable.powered_by_google_light,"Notification 9","Machin veut un truc",true));
        tmp.add(new Contact(R.drawable.powered_by_google_light,"Notification 10","Machin veut un truc",true));
        tmp.add(new Contact(R.drawable.powered_by_google_light,"Notification 11","Machin veut un truc",true));
        tmp.add(new Contact(R.drawable.powered_by_google_light,"Notification 12","Machin veut un truc",true));

        return tmp;
    }

    private Contact getFriendByRange(int range) {
        return this.myFriends.get(range);
    }
}
