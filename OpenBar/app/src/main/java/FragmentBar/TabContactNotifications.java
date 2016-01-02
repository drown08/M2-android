package FragmentBar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.openbar.frappereauolivier.openbar.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import Adapter.NotifAdapter;
import CommunicationServeur.AsyncTaskResponse;
import CommunicationServeur.CommunicationService;
import Evenement.OnRefreshListNotificationUser;
import Evenement.OnScrollListListener;
import Model.NotificationUser;

/**
 * Created by Frappereau Olivier on 22/11/2015.
 */
public class TabContactNotifications extends Fragment implements AsyncTaskResponse {

    public View mainV;
    RecyclerView notifList;
    public NotifAdapter notifAdapter;
    public ArrayList<NotificationUser> myNotifs;
    public TextView myTextViewEmptyList;
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

    private void setListOfNotifView(){
        if(this.notifList == null) {
            this.notifList = (RecyclerView) this.mainV.findViewById(R.id.cardListNotification);
            //this.recListBar.setHasFixedSize(true);
            LinearLayoutManager llm = new LinearLayoutManager(this.getContext());
            llm.setOrientation(LinearLayoutManager.VERTICAL);
            this.notifList.setLayoutManager(llm);
            //Call Service to getBars
            //setListBar();
            //this.myBars = setListBar();
        }
    }

    private void setListOfNotifications() {

        CommunicationService getNotif = new CommunicationService(this,this.getActivity(),true,1);
        getNotif.addParams("ctrl", "getNotif");
        getNotif.addParams("id_user","12");//TODO : réccup good id user current
        getNotif.sendToServer();
        getNotif.flush();

    }

    private void setListAdapterNotif() {
        this.notifAdapter = new NotifAdapter(myNotifs,this.getContext(),this);
        this.notifList.setAdapter(this.notifAdapter);
        this.notifList.setItemAnimator(new DefaultItemAnimator());

        mySRL = (SwipeRefreshLayout) this.mainV.findViewById(R.id.swipeRefreshLayoutProfilNotification);
        mySRL.setOnRefreshListener(new OnRefreshListNotificationUser(this,this.mySRL));

        notifList.addOnScrollListener(new OnScrollListListener(this.mySRL, this.notifList));
    }


    private NotificationUser getNotifByRange(int range) {
        return this.myNotifs.get(range);
    }

    @Override
    public void processFinish(String output, int flag) {
        switch (flag){
            case 1 : //Récupération des notifs
                ArrayList<NotificationUser> listNotif = new ArrayList<NotificationUser>();
                try {
                    JSONArray result = new JSONArray(output);
                    for(int i = 0; i < result.length(); i++) {
                        JSONObject row = result.getJSONObject(i);
                        NotificationUser notif = new NotificationUser("Notification"+String.valueOf(i),row.getString("sujet"),"proposition",row.getInt("id_notification"));
                        listNotif.add(notif);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                setEmptyListView();
                setListOfNotifView();

                Log.d("testlist", String.valueOf(listNotif.size()));
                if(listNotif.isEmpty()){
                    this.notifList.setVisibility(View.GONE);
                    this.myTextViewEmptyList.setVisibility(View.VISIBLE);
                } else {
                    this.myNotifs = listNotif;
                    this.notifList.setVisibility(View.VISIBLE);
                    this.myTextViewEmptyList.setVisibility(View.GONE);
                    setListAdapterNotif();
                }
                Toast.makeText(this.getContext(),"Notif reccup",Toast.LENGTH_SHORT).show();
                break;
            case 2 : //Retour traitement de la notif
                Toast.makeText(this.getContext(),"Notif traitée",Toast.LENGTH_SHORT).show();
                break;
            case 3 : //Refresh des notifs
                ArrayList<NotificationUser> tmp = new ArrayList<NotificationUser>();
                try {
                    JSONArray result = new JSONArray(output);
                    for(int i = 0; i < result.length(); i++) {
                        JSONObject row = result.getJSONObject(i);
                        NotificationUser notif = new NotificationUser("Notification"+String.valueOf(i),row.getString("sujet"),"proposition",row.getInt("id_notification"));
                        //this.myNotifs.add(notif);
                        tmp.add(notif);
                    }
                    onNotifLoadComplete(tmp);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
    private void onNotifLoadComplete(ArrayList<NotificationUser> ln) {
        this.myNotifs = ln;
        this.notifAdapter.UPNotifList(ln);
        this.mySRL.setRefreshing(false);
        Toast.makeText(this.getContext(),"Refresh notif ok",Toast.LENGTH_SHORT).show();
    }
    private void setEmptyListView(){
        if(this.myTextViewEmptyList == null) {
            this.myTextViewEmptyList = (TextView) this.mainV.findViewById(R.id.empty_list_notif);
            this.myTextViewEmptyList.setVisibility(View.INVISIBLE);
        }
    }
}
