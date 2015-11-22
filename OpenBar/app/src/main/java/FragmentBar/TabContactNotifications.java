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

import Adapter.NotifAdapter;
import Evenement.OnRefreshListNotificationUser;
import Model.NotificationUser;

/**
 * Created by Frappereau Olivier on 22/11/2015.
 */
public class TabContactNotifications extends Fragment {

    public View mainV;
    RecyclerView notifList;
    public NotifAdapter notifAdapter;
    ArrayList<NotificationUser> myNotifs;
    SwipeRefreshLayout mySRL;

    public TabContactNotifications() {
        super();
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saveInstanceState) {
        this.mainV = inflater.inflate(R.layout.tab_user_notifications,container,false);
        customTheView();
        return this.mainV;
    }

    private void customTheView() {
        setListOfNotifications();
    }

    private void setListOfNotifications() {
        notifList = (RecyclerView) this.mainV.findViewById(R.id.cardListNotification);
        notifList.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(this.getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        notifList.setLayoutManager(llm);

        myNotifs = setListNotif();

        this.notifAdapter = new NotifAdapter(myNotifs,this.getContext());
        this.notifList.setAdapter(this.notifAdapter);

        mySRL = (SwipeRefreshLayout) this.mainV.findViewById(R.id.swipeRefreshLayoutProfilNotification);
        mySRL.setOnRefreshListener(new OnRefreshListNotificationUser(this,mySRL));

        notifList.addOnScrollListener(new RecyclerView.OnScrollListener(){
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                boolean enable = false;
                if(notifList != null && notifList.getChildCount() > 0) {
                    boolean topOfFirstItemVisible = notifList.getChildAt(0).getTop() == 0;
                    enable = topOfFirstItemVisible;
                }
                Log.d("MethodCalledBis", String.valueOf(enable));
                mySRL.setEnabled(enable);
            }
        });

    }

    private ArrayList<NotificationUser> setListNotif() {
        ArrayList<NotificationUser> tmp = new ArrayList<NotificationUser>();
        tmp.add(new NotificationUser("Notification 1","Machin veut un truc","invitation"));
        tmp.add(new NotificationUser("Notification 2","Machin veut un truc","infos"));
        tmp.add(new NotificationUser("Notification 3","Machin veut un truc","proposition"));
        tmp.add(new NotificationUser("Notification 4","Machin veut un truc","infos"));
        tmp.add(new NotificationUser("Notification 5","Machin veut un truc","infos"));
        tmp.add(new NotificationUser("Notification 6","Machin veut un truc","proposition"));
        tmp.add(new NotificationUser("Notification 7","Machin veut un truc","infos"));
        tmp.add(new NotificationUser("Notification 8","Machin veut un truc","invitation"));
        tmp.add(new NotificationUser("Notification 9","Machin veut un truc","proposition"));
        tmp.add(new NotificationUser("Notification 10","Machin veut un truc","proposition"));
        tmp.add(new NotificationUser("Notification 11","Machin veut un truc","proposition"));
        tmp.add(new NotificationUser("Notification 12","Machin veut un truc","proposition"));

        return tmp;
    }

    private NotificationUser getNotifByRange(int range) {
        return this.myNotifs.get(range);
    }
}
