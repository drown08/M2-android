package Evenement;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.openbar.frappereauolivier.openbar.R;

import FragmentBar.TabBarContact;

/**
 * Created by Frappereau Olivier on 16/11/2015.
 */
public class OnClickDisplayListContact implements View.OnClickListener {
    public TabBarContact context;

    public OnClickDisplayListContact(TabBarContact context) {
        this.context = context;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.titre_contact_amis :
                hideOrDisplayListe(context.manageFriend);
                break;
            case R.id.titre_contact_autre :
                hideOrDisplayListe(context.manageOthers);
                break;
        }
    }

    private void hideOrDisplayListe(RecyclerView view) {
        if(view.getVisibility() == View.GONE) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.GONE);
        }
    }
}
