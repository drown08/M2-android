package ItemViewHolder;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.openbar.frappereauolivier.openbar.R;

import Evenement.OnClickAtSocialAction;
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
    protected Button action1;
    protected Button action2;
    protected Button action3;
    String nom;

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
        this.action1 = (Button) itemView.findViewById(R.id.contact_action_1);
        this.action2 = (Button) itemView.findViewById(R.id.contact_action_2);
        this.action3 = (Button) itemView.findViewById(R.id.contact_action_3);
        this.action1.setClickable(true);
        this.action2.setClickable(true);
        this.action3.setClickable(true);
    }

    public void setMyComponents(Activity activity,Fragment fragment) {
        this.actionSecondaire.setVisibility(View.GONE);
        this.logo.setClickable(true);
        this.logo.setOnClickListener(new OnClickDisplayActionContacts(this));
        this.action1.setOnClickListener(new OnClickAtSocialAction(activity, fragment, 1, this.nom));
        this.action2.setOnClickListener(new OnClickAtSocialAction(activity, fragment, 2, this.nom));
        this.action3.setOnClickListener(new OnClickAtSocialAction(activity,fragment,3,this.nom));
    }

    public void bindMyComponents(Contact c) {
        this.logo.setImageResource(c.getRefImg());
        if(c.isMyFriend()) {
            this.actionPrincipale.setImageResource(R.drawable.ic_tick);
        } else {
            this.actionPrincipale.setImageResource(R.drawable.icone_refresh_48);
        }
        this.nomPrenom.setText(c.getNom()+" "+c.getPrenom());
        this.nom = c.getNom();
    }

    public void setLogo(ImageView logo) {
        this.logo = logo;
    }

    public void setNomPrenom(TextView nomPrenom) {
        this.nomPrenom = nomPrenom;
    }

    public void setAction1(Button action1) {
        this.action1 = action1;
    }

    public void setAction2(Button action2) {
        this.action2 = action2;
    }

    public void setAction3(Button action3) {
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

    public Button getAction1() {
        return action1;
    }

    public Button getAction2() {
        return action2;
    }

    public Button getAction3() {
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
