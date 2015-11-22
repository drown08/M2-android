package Evenement;

import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.Toast;

import java.util.ArrayList;

import FragmentBar.TabContactNotifications;
import Model.NotificationUser;

/**
 * Created by Frappereau Olivier on 22/11/2015.
 */
public class OnRefreshListNotificationUser implements SwipeRefreshLayout.OnRefreshListener {
    TabContactNotifications context;
    SwipeRefreshLayout focusElement;

    public OnRefreshListNotificationUser(TabContactNotifications context, SwipeRefreshLayout mySRL) {
        this.context=context;
        this.focusElement=mySRL;
    }

    @Override
    public void onRefresh() {
        refreshNotifs();
    }

    private void refreshNotifs() {
        ArrayList<NotificationUser> tmp = new ArrayList<NotificationUser>();
        tmp.add(new NotificationUser("Notification 5","Machin veut un truc","infos"));
        tmp.add(new NotificationUser("Notification 6","Machin veut un truc","proposition"));
        tmp.add(new NotificationUser("Notification 7","Machin veut un truc","infos"));
        tmp.add(new NotificationUser("Notification 8","Machin veut un truc","invitation"));
        tmp.add(new NotificationUser("Notification 11","Machin veut un truc","proposition"));
        tmp.add(new NotificationUser("Notification 12","Machin veut un truc","proposition"));
        onNotifLoadComplete(tmp);
    }

    private void onNotifLoadComplete(ArrayList<NotificationUser> ln) {
        this.context.notifAdapter.UPNotifList(ln);
        this.focusElement.setRefreshing(false);
        Toast.makeText(context.getContext(),"Refresh notif ok",Toast.LENGTH_SHORT).show();
    }
}
