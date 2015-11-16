package FragmentBar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.openbar.frappereauolivier.openbar.Activity.BarActivity;
import com.openbar.frappereauolivier.openbar.R;

import java.util.ArrayList;

import Adapter.ContactAdapterViewBar;
import Evenement.OnClickDisplayListContact;
import Evenement.OnRefreshActivityBarBis;
import Model.Contact;

/**
 * Created by Frappereau Olivier on 15/11/2015.
 */
public class TabBarContact extends Fragment {
    public View mainV;
    public TextView titreAmis;
    public RecyclerView manageFriend;
    public LinearLayoutManager llmf;
    public ContactAdapterViewBar adapteFriends;
    public TextView titreAutre;
    public RecyclerView manageOthers;
    public LinearLayoutManager llmo;
    public ContactAdapterViewBar adapteOthers;
    public SwipeRefreshLayout srl;


    public TabBarContact() {super();}

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       this.mainV = inflater.inflate(R.layout.tab_bar_contact,container,false);
        customTheView();
        return this.mainV;
    }

    private void customTheView() {
        setTitles();
        setListes();
        setSwipe();
    }

    private void setSwipe() {
        srl = (SwipeRefreshLayout) this.mainV.findViewById(R.id.swipeRefreshLayoutBarContact);
        srl.setOnRefreshListener( new OnRefreshActivityBarBis(this,srl));
    }

    private void setTitles() {
        titreAmis = (TextView) this.mainV.findViewById(R.id.titre_contact_amis);
        //Do stuff
        titreAmis.setOnClickListener(new OnClickDisplayListContact(this));
        titreAutre = (TextView) this.mainV.findViewById(R.id.titre_contact_autre);
        //Do stuff
        titreAutre.setOnClickListener(new OnClickDisplayListContact(this));
    }

    private void setListes() {
        manageFriend = (RecyclerView) this.mainV.findViewById(R.id.liste_contact_amis_present);
        manageOthers = (RecyclerView) this.mainV.findViewById(R.id.liste_contact_autre_present);

        manageFriend.setVisibility(View.GONE);
        manageOthers.setVisibility(View.GONE);

        llmf = new LinearLayoutManager(this.mainV.getContext());
        llmo = new LinearLayoutManager(this.mainV.getContext());


        manageFriend.setLayoutManager(llmf);
        manageOthers.setLayoutManager(llmo);

        dispatchingContact();

        manageFriend.setAdapter(adapteFriends);
        manageOthers.setAdapter(adapteOthers);

    }

    private void dispatchingContact() {
        ArrayList<Contact> amis = new ArrayList<Contact>();
        ArrayList<Contact> others = new ArrayList<Contact>();
        for(Contact c : BarActivity.myBar.getListePresents()) {
            if (c.isMyFriend()){
                amis.add(c);
            } else {
                others.add(c);
            }
        }
        adapteFriends = new ContactAdapterViewBar(amis);
        adapteOthers = new ContactAdapterViewBar(others);
    }

}
