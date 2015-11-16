package ItemViewHolder;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.openbar.frappereauolivier.openbar.R;

import Evenement.OnClickDisplayActionContacts;
import Model.Contact;

/**
 * Created by Frappereau Olivier on 16/11/2015.
 */
public class ContactViewHolder extends RecyclerView.ViewHolder {

    protected CardView card;
    protected ImageView logo;
    protected ImageButton actionPrincipale;
    protected LinearLayout actionSecondaire;
    protected TextView nomPrenom;
    protected TextView action1;
    protected TextView action2;
    protected TextView action3;

    public ContactViewHolder(View itemView) {
        super(itemView);
        initComponents(itemView);
    }

    private void initComponents(View itemView) {
        this.card = (CardView) itemView;
        this.logo = (ImageView) itemView.findViewById(R.id.photo_profil_contact);
        this.actionPrincipale = (ImageButton) itemView.findViewById(R.id.action_principale_contact);
        this.actionSecondaire = (LinearLayout) itemView.findViewById(R.id.display_hide_action);
        this.nomPrenom = (TextView) itemView.findViewById(R.id.detail_contact);
        this.action1 = (TextView) itemView.findViewById(R.id.contact_action_1);
        this.action2 = (TextView) itemView.findViewById(R.id.contact_action_2);
        this.action3 = (TextView) itemView.findViewById(R.id.contact_action_3);

    }

    public void setMyComponents() {
        this.actionSecondaire.setVisibility(View.GONE);
        this.logo.setClickable(true);
        this.logo.setOnClickListener(new OnClickDisplayActionContacts(this));
    }

    public void bindMyComponents(Contact c) {
        this.logo.setImageResource(c.getRefImg());
        if(c.isMyFriend()) {
            this.actionPrincipale.setImageResource(R.drawable.ic_tick);
        } else {
            this.actionPrincipale.setImageResource(R.drawable.icone_refresh_48);
        }
        this.nomPrenom.setText(c.getNom()+" "+c.getPrenom());
    }

    public void setLogo(ImageView logo) {
        this.logo = logo;
    }

    public void setNomPrenom(TextView nomPrenom) {
        this.nomPrenom = nomPrenom;
    }

    public void setAction1(TextView action1) {
        this.action1 = action1;
    }

    public void setAction2(TextView action2) {
        this.action2 = action2;
    }

    public void setAction3(TextView action3) {
        this.action3 = action3;
    }

    public void setActionPrincipale(ImageButton actionPrincipale) {
        this.actionPrincipale = actionPrincipale;
    }

    public void setActionSecondaire(LinearLayout actionSecondaire) {
        this.actionSecondaire = actionSecondaire;
    }

    public void setCard(CardView card) {
        this.card = card;
    }

    public TextView getAction1() {
        return action1;
    }

    public TextView getAction2() {
        return action2;
    }

    public TextView getAction3() {
        return action3;
    }

    public TextView getNomPrenom() {
        return nomPrenom;
    }

    public CardView getCard() {
        return card;
    }

    public ImageButton getActionPrincipale() {
        return actionPrincipale;
    }

    public ImageView getLogo() {
        return logo;
    }

    public LinearLayout getActionSecondaire() {
        return actionSecondaire;
    }
}
