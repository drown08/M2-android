package Evenement;

import android.view.View;

import com.openbar.frappereauolivier.openbar.R;

import ItemViewHolder.ContactViewHolder;

/**
 * Created by Frappereau Olivier on 16/11/2015.
 */
public class OnClickDisplayActionContacts implements View.OnClickListener {

    ContactViewHolder holder;

    public OnClickDisplayActionContacts(ContactViewHolder holder) {
        this.holder = holder;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.photo_profil_contact :
                displayActions();
                break;
        }
    }

    private void displayActions() {
        if(this.holder.getActionSecondaire().getVisibility() == View.GONE) {
            this.holder.getActionSecondaire().setVisibility(View.VISIBLE);
        } else {
            this.holder.getActionSecondaire().setVisibility(View.GONE);
        }
    }
}
