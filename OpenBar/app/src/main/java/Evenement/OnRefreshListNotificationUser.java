package Evenement;

import android.support.v4.widget.SwipeRefreshLayout;

import CommunicationServeur.CommunicationService;
import FragmentBar.TabContactNotifications;

/**
 * Created by Frappereau Olivier on 22/11/2015.
 */
public class OnRefreshListNotificationUser implements SwipeRefreshLayout.OnRefreshListener {
    TabContactNotifications context;
    SwipeRefreshLayout focusElement;
    public OnRefreshListNotificationUser(TabContactNotifications context, SwipeRefreshLayout focusElement) {
        this.context=context;
        this.focusElement = focusElement;
    }

    @Override
    public void onRefresh() {
        refreshNotifs();
        focusElement.setRefreshing(false);
    }

    private void refreshNotifs() {
        CommunicationService refreshList = new CommunicationService(context,context.getActivity(),true,1);
        refreshList.addParams("ctrl", "getNotif");
        refreshList.addParams("id_user","12");//TODO : réccup good id user current
        refreshList.sendToServer();
        refreshList.flush();
    }
}
