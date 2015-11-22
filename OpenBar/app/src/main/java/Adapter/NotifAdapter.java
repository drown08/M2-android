package Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.openbar.frappereauolivier.openbar.R;

import java.util.ArrayList;

import ItemViewHolder.NotifViewHolder;
import Model.NotificationUser;

/**
 * Created by Frappereau Olivier on 22/11/2015.
 */
public class NotifAdapter extends RecyclerView.Adapter<NotifViewHolder> {
    private ArrayList<NotificationUser> notifList;
    private View itemNotifView;
    private Context context;

    public NotifAdapter(ArrayList<NotificationUser> myNotifs, Context context) {
        this.notifList = myNotifs;
        this.context = context;
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
                holder.buttonAction1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context,"Click at Accept",Toast.LENGTH_SHORT).show();
                        removeNotif(position);
                    }
                });
                holder.buttonAction1.setText("Accept");
                holder.buttonAction2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context,"Click at Refuse",Toast.LENGTH_SHORT).show();
                        removeNotif(position);
                    }
                });
                holder.buttonAction2.setText("Refuse");
                break;
            case "infos":
                holder.buttonAction1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context,"Click at ok",Toast.LENGTH_SHORT).show();
                        removeNotif(position);
                    }
                });
                holder.buttonAction1.setText("Ok");
                holder.buttonAction2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context,"Click at osef",Toast.LENGTH_SHORT).show();
                        removeNotif(position);
                    }
                });
                holder.buttonAction2.setText("Don't care");
                break;
            case "proposition":
                holder.buttonAction1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context,"Click at Confirm",Toast.LENGTH_SHORT).show();
                        removeNotif(position);
                    }
                });
                holder.buttonAction1.setText("Confirm");
                holder.buttonAction2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context,"Click at Denied",Toast.LENGTH_SHORT).show();
                        removeNotif(position);
                    }
                });
                holder.buttonAction2.setText("Denied");
                break;
        }
    }

    @Override
    public int getItemCount() {
        return notifList.size();
    }

    public void UPNotifList(ArrayList<NotificationUser> ln) {
        this.notifList = ln;
        notifyDataSetChanged();
    }

    public void removeNotif(int position) {
        this.notifList.remove(position);
        notifyItemRemoved(position);
    }
}
