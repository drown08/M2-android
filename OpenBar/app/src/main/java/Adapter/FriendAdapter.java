package Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.openbar.frappereauolivier.openbar.R;

import java.util.ArrayList;

import ItemViewHolder.FriendViewHolder;
import Model.Contact;

/**
 * Created by Frappereau Olivier on 22/11/2015.
 */
public class FriendAdapter extends RecyclerView.Adapter<FriendViewHolder> {
    private ArrayList<Contact> friendList;
    private View itemFriendView;
    private Context context;

    public FriendAdapter(ArrayList<Contact> myFriends, Context context) {
        this.friendList = myFriends;
        this.context = context;
    }

    @Override
    public FriendViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.itemFriendView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_friend,parent,false);
        FriendViewHolder tmpNotifV = new FriendViewHolder(this.itemFriendView);
        return tmpNotifV;
    }

    @Override
    public void onBindViewHolder(final FriendViewHolder holder, final int position) {
        Contact c = this.friendList.get(position);
        holder.name.setText(c.getNom());
        holder.surname.setText(c.getPrenom());
        holder.photo.setImageResource(c.getRefImg());

        holder.photo.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //Dialog sure = new Dialog();
                removeFriend(position);
                return true;
            }
        });

        holder.buttonAction1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "go to " + holder.surname.getText() + " profile", Toast.LENGTH_SHORT).show();
                // Transaction goFriendProfil = new Transaction(context,ProfilFriendActivity.class);
                // goFriendProfil.runWithoutExit();
            }
        });
        holder.buttonAction2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "go to " + holder.surname.getText() + " take a call", Toast.LENGTH_SHORT).show();
                // Transaction goFriendProfil = new Transaction(context,PhoneCallService.class);
                // goFriendProfil.runWithoutExit();
            }
        });
        holder.buttonAction3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"go to "+holder.surname.getText()+" notify to go drink",Toast.LENGTH_SHORT).show();
               // Comunication service...
            }
        });

    }

    @Override
    public int getItemCount() {
        return friendList.size();
    }

    public void UPFriendList(ArrayList<Contact> ln) {
        this.friendList = ln;
        notifyDataSetChanged();
    }

    public void removeFriend(int position) {
        this.friendList.remove(position);
        notifyItemRemoved(position);
    }
}