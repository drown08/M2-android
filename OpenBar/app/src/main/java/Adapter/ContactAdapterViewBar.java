package Adapter;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.openbar.frappereauolivier.openbar.R;

import java.util.ArrayList;

import ItemViewHolder.ContactViewHolder;
import Model.Contact;

/**
 * Created by Frappereau Olivier on 16/11/2015.
 */
    public class ContactAdapterViewBar extends RecyclerView.Adapter<ContactViewHolder> {

    private ArrayList<Contact> contacts;
    Activity myActivity;
    Fragment myFragment;

    public  ContactAdapterViewBar(ArrayList<Contact> l, Activity activity, Fragment fragment) {
        this.contacts = new ArrayList<Contact>();
        this.contacts.addAll(l);
        this.myActivity = activity;
        this.myFragment = fragment;
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.layout_contact_item, parent, false);
       return new ContactViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {
        Contact c = contacts.get(position);
        holder.setMyComponents(this.myActivity,this.myFragment);
        holder.bindMyComponents(c);
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public void refreshListing(ArrayList<Contact> l,boolean isFriend) {
       ArrayList<Contact> bb = new ArrayList<Contact>();
        for(Contact c : l) {
            Log.d("Contact",String.valueOf(c.isMyFriend()));
                if(c.isMyFriend()==isFriend)
                {
                    bb.add(c);
                    Log.d("ContactIsFriend", String.valueOf(c.isMyFriend()));
                }
        }

        this.contacts = bb;
        notifyDataSetChanged();
    }
}
