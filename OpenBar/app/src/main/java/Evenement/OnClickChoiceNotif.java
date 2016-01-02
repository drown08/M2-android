package Evenement;

import android.app.Activity;
import android.view.View;
import android.widget.Toast;

import Adapter.NotifAdapter;
import CommunicationServeur.CommunicationService;
import FragmentBar.TabContactNotifications;
import ItemViewHolder.NotifViewHolder;
import Model.NotificationUser;

/**
 * Created by Frappereau Olivier on 02/01/2016.
 */
public class OnClickChoiceNotif implements View.OnClickListener {
    Activity myActivity;
    TabContactNotifications myFragment;
    NotifAdapter myAdapter;
    int positionFocus;
    String typeAction;
    int choice;
    NotificationUser target;
    NotifViewHolder targetAdapt;

    public OnClickChoiceNotif(Activity activity,TabContactNotifications fragment, NotifAdapter adapter,int position, String type, int choice, NotificationUser notificationUser, NotifViewHolder notifViewHolder){
        this.myActivity = activity;
        this.myFragment = fragment;
        this.myAdapter = adapter;
        this.positionFocus = position;
        this.typeAction = type;
        this.choice = choice;
        this.target = notificationUser;
        this.targetAdapt = notifViewHolder;
    }

    @Override
    public void onClick(View v) {
        int focus = this.targetAdapt.getAdapterPosition();
        Toast.makeText(myActivity.getApplicationContext(), "Click at Notif : "+focus, Toast.LENGTH_SHORT).show();
        //TODO : Traiter différement les types d'action, et si oui ou non
        CommunicationService traitementNotif = new CommunicationService(this.myFragment,this.myActivity,true,2);
        traitementNotif.addParams("ctrl", "traiterNotif");
        traitementNotif.addParams("id_notif", String.valueOf(this.target.getNum()));
        switch (typeAction) {
            case "invitation" :
                traitementNotif.addParams("type","invitation");
                if(choice == 1) { //Si positif (ajout d'amis)
                    traitementNotif.addParams("status","success");
                } else { //Si négatif (refus d'ajout)
                    traitementNotif.addParams("status","failure");
                }
                break;
            case "proposition" :
                traitementNotif.addParams("type","proposition");
                if(choice == 1) { //Si positif (accepter proposition)
                    traitementNotif.addParams("status","success");
                } else { //Si négatif (refuser proposition)
                    traitementNotif.addParams("status","failure");
                }
                break;
            case "infos":
                traitementNotif.addParams("type","infos");
                if(choice == 1) { //Si positif (Accorde de l'importance)
                    traitementNotif.addParams("status","success");
                } else { //Si négatif (S'en fiche)
                    traitementNotif.addParams("status","failure");
                }
                break;
        }
        traitementNotif.sendToServer();
        traitementNotif.flush();
        //this.myFragment.myNotifs.remove(this.target);
        this.myAdapter.removeNotif(focus);


    }
}
