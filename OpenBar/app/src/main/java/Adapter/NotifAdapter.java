package Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.openbar.frappereauolivier.openbar.R;

import java.util.ArrayList;

import Evenement.OnClickChoiceNotif;
import FragmentBar.TabContactNotifications;
import ItemViewHolder.NotifViewHolder;
import Model.NotificationUser;

/**
 * Created by Frappereau Olivier on 22/11/2015.
 */
public class NotifAdapter extends RecyclerView.Adapter<NotifViewHolder> {
    private ArrayList<NotificationUser> notifList;
    private View itemNotifView;
    private Context context;
    Activity myActivity;
    TabContactNotifications myFragment;

    public NotifAdapter(ArrayList<NotificationUser> myNotifs,Context context, TabContactNotifications fragment) {
        this.notifList = myNotifs;
        this.myFragment = fragment;
        this.context = context;
        this.myActivity = fragment.getActivity();
    }

    @Override
    public NotifViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.itemNotifView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_notification,parent,false);
        NotifViewHolder tmpNotifV = new NotifViewHolder(this.itemNotifView);
        return tmpNotifV;
    }

    @Override
    public void onBindViewHolder(NotifViewHolder holder, final int position) {
        NotificationUser notif = this.notifList.get(position);
        holder.nameNotif.setText(notif.getName());
        holder.objectNotif.setText(notif.getObject());
        switch (notif.getTypeAction()) {
            case "invitation":
                holder.buttonAction1.setText("Accept");
                holder.buttonAction2.setText("Refuse");
                break;
            case "infos":
                holder.buttonAction1.setText("Ok");
                holder.buttonAction2.setText("Don't care");
                break;
            case "proposition":
                holder.buttonAction1.setText("Confirm");
                holder.buttonAction2.setText("Denied");
                break;
        }
        holder.buttonAction1.setOnClickListener(new OnClickChoiceNotif(this.myActivity, this.myFragment, this, position, notif.getTypeAction(), 1, notif,holder));
        holder.buttonAction2.setOnClickListener(new OnClickChoiceNotif(this.myActivity, this.myFragment, this, position, notif.getTypeAction(), 0, notif,holder));
    }

    @Override
    public int getItemCount() {
        return notifList.size();
    }

    public void UPNotifList(ArrayList<NotificationUser> ln) {
        this.notifList = new ArrayList<NotificationUser>(ln);;
        //notifyDataSetChanged();
        notifyItemRangeChanged(0, ln.size());
    }

    public void removeNotif(int position) {
        this.notifList.remove(position);
        notifyItemRemoved(position);
    }
}
