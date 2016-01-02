package Evenement;

import android.app.Activity;
import android.content.DialogInterface;
import android.location.Address;
import android.location.Geocoder;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.openbar.frappereauolivier.openbar.Activity.AddBarMapActivity;
import com.openbar.frappereauolivier.openbar.Activity.AddMarkerCallBack;
import com.openbar.frappereauolivier.openbar.R;

import java.io.IOException;
import java.util.List;

import CommunicationServeur.CommunicationService;
import Model.BarMap;

/**
 * Created by Frappereau Olivier on 30/12/2015.
 */
public class OnTapeAdressBar implements DialogInterface.OnClickListener {

    AddBarMapActivity myActivity;
    AddMarkerCallBack delegate;
    View view;

    public OnTapeAdressBar(Activity activity,Activity delegate,View v){
        this.myActivity = (AddBarMapActivity) activity;
        this.delegate = (AddMarkerCallBack) delegate;
        this.view = v;
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        EditText tmp = (EditText) view.findViewById(R.id.adresse_bar);
        EditText nameTmp = (EditText) view.findViewById(R.id.nom_bar);
        String name = nameTmp.getText().toString();
        String texte = tmp.getText().toString();
        Toast.makeText(this.myActivity.getApplicationContext(),texte, Toast.LENGTH_SHORT).show();
        Address address = getAdressFromString(texte);
        Toast.makeText(this.myActivity.getApplicationContext(),"Latitude : "+String.valueOf(address.getLatitude()),Toast.LENGTH_SHORT).show();
        Toast.makeText(this.myActivity.getApplicationContext(),"Longitude : "+String.valueOf(address.getLongitude()),Toast.LENGTH_SHORT).show();
        BarMap newBarMap = new BarMap(name,address.getLatitude(),address.getLongitude());
        CommunicationService addBarToBD = new CommunicationService(this.myActivity,this.myActivity,true,3);
        addBarToBD.addParams("ctrl","addBarToBD");
        addBarToBD.addParams("nom_bar",newBarMap.getNom());
        addBarToBD.addParams("x",String.valueOf(newBarMap.getX()));
        addBarToBD.addParams("y",String.valueOf(newBarMap.getY()));
        addBarToBD.sendToServer();
        addBarToBD.flush();
        Toast.makeText(this.myActivity.getApplicationContext(),"Nouveau bar : "+name,Toast.LENGTH_SHORT).show();
        this.myActivity.listebar.add(newBarMap);
        Toast.makeText(this.myActivity.getApplicationContext(),"Taille liste : "+String.valueOf(this.myActivity.listebar.size()),Toast.LENGTH_SHORT).show();
        LatLng tmpll = new LatLng(newBarMap.getX(),newBarMap.getY());
        this.delegate.addMarker(tmpll,newBarMap.getNom(),this.myActivity.mMap);
        //this.myActivity.mMap.addMarker(new MarkerOptions().position(tmpll).title("Click pour ajouter un nom"));
        //this.myActivity.mMap.moveCamera(CameraUpdateFactory.newLatLng(tmpll));
    }

    private Address getAdressFromString(String address) {
        Geocoder coder = new Geocoder(myActivity.getApplicationContext());
        List<Address> addresses;
        Address location = null;
        try {
            addresses = coder.getFromLocationName(address, 1);
            if (address==null) {
                return null;
            }
            location=addresses.get(0);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return location;
    }
}
