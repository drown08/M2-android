package FragmentBar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.openbar.frappereauolivier.openbar.Activity.BarActivity;
import com.openbar.frappereauolivier.openbar.R;

import java.util.ArrayList;

import Adapter.ContactAdapterViewBar;
import CommunicationServeur.AsyncTaskResponse;
import Evenement.OnClickDisplayListContact;
import Evenement.OnRefreshActivityBarBis;
import Model.Contact;

/**
 * Created by Frappereau Olivier on 15/11/2015.
 */
public class TabBarContact extends Fragment implements AsyncTaskResponse {
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
    public ScrollView sv;
    public ImageView imageView;


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
        setScrollView();
        setImageView();
    }

    private void setImageView() {
        this.imageView = (ImageView) this.mainV.findViewById(R.id.display_taking_picture);
    }

    private void setScrollView() {
        this.sv = (ScrollView) this.mainV.findViewById(R.id.scroll_view_contact_bar);
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

        //manageFriend.setNestedScrollingEnabled(true);
        //manageOthers.setNestedScrollingEnabled(true);
        //manageFriend.setHasFixedSize(true);
        //manageOthers.setHasFixedSize(true);


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
        adapteFriends = new ContactAdapterViewBar(amis, this.getActivity(),this);
        adapteOthers = new ContactAdapterViewBar(others, this.getActivity(),this);
    }

    @Override
    public void processFinish(String output, int flag) {
        switch (flag){
            case 1 :
                break;
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == getActivity().RESULT_OK) {

            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            Toast.makeText(getActivity(), "Photo la : " + imageBitmap.toString(), Toast.LENGTH_SHORT).show();
           // AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this.getActivity());
            // alertDialogBuilder.setMessage("Vous avez indiquer etre au bar " + nomBar + ", voulez-vous vraiment changer de bar ?");
           // alertDialogBuilder.setIcon(imageBitmap.getGenerationId());
           // AlertDialog alertDialog = alertDialogBuilder.create();
           // alertDialog.show();
            Uri selectedImage = data.getData();
            imageView.setImageURI(selectedImage);
            imageView.setVisibility(View.VISIBLE);
        }else if(requestCode == 1 && resultCode == getActivity().RESULT_OK) {
            Uri selectedImage = data.getData();
            imageView.setImageURI(selectedImage);
            imageView.setVisibility(View.VISIBLE);
            Toast.makeText(getActivity(), "Photo la 2 : " + selectedImage.toString(), Toast.LENGTH_SHORT).show();
            //AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this.getActivity());
            // alertDialogBuilder.setMessage("Vous avez indiquer etre au bar " + nomBar + ", voulez-vous vraiment changer de bar ?");
            //alertDialogBuilder.setIcon(selectedImage.);
            //AlertDialog alertDialog = alertDialogBuilder.create();
            //alertDialog.show();
        } else {
            Integer constCode1 = getActivity().RESULT_CANCELED;
            boolean codeRetour1 =  constCode1.equals(new Integer(resultCode));
            Toast.makeText(getActivity(), "Canceled ? : " + String.valueOf(codeRetour1), Toast.LENGTH_SHORT).show();
            Integer constCode2 = getActivity().RESULT_FIRST_USER;
            boolean codeRetour2 =  constCode2.equals(new Integer(resultCode));
            Toast.makeText(getActivity(), "First User ? : " + String.valueOf(codeRetour2), Toast.LENGTH_SHORT).show();
            Integer constCode3 = getActivity().RESULT_OK;
            boolean codeRetour3 =  constCode3.equals(new Integer(resultCode));
            Toast.makeText(getActivity(), "Ok (zarb) ? : " + String.valueOf(codeRetour3), Toast.LENGTH_SHORT).show();
        }
    }
}
