package com.openbar.frappereauolivier.openbar.Activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.openbar.frappereauolivier.openbar.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import CommunicationServeur.AsyncTaskResponse;
import CommunicationServeur.CommunicationService;
import Evenement.OnActionBarMenuSelected;
import Evenement.OnClickAddBarByForm;
import Evenement.OnClickMarker;
import Model.BarMap;

public class AddBarMapActivity extends AppCompatActivity implements OnMapReadyCallback, AsyncTaskResponse, AddMarkerCallBack{

    public GoogleMap mMap; // Might be null if Google Play services APK is not available.
    public ArrayList<BarMap> listebar;
    public FloatingActionButton addBarByForm;
    public Toolbar myToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSpecifyPresentation();
    }

    private void setSpecifyPresentation() {
        setContentView(R.layout.activity_add_bar_map);
        this.listebar = new ArrayList<BarMap>();
        setToolBarView();
        //setUpMapIfNeeded();

        //Récuppération des bars existants (TODO : minus ceux du currentUser)
        Toast.makeText(this.getApplicationContext(),"ETAPE 0 : INIT",Toast.LENGTH_SHORT).show();
        CommunicationService getBars = new CommunicationService(this,this,true,1);
        getBars.addParams("ctrl","getBarMarker");
        getBars.addParams("id_user","12");
        getBars.sendToServer();
        getBars.flush();
    }

    private void setFABView() {
        this.addBarByForm = (FloatingActionButton)findViewById(R.id.fab2);
        this.addBarByForm.setOnClickListener(new OnClickAddBarByForm(this));
        this.addBarByForm.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //setUpMapIfNeeded();
    }



    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (this.mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            this.mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (this.mMap != null) {
                onMapReady(this.mMap);
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        LatLng test = new LatLng(45,45);
        this.mMap.addMarker(new MarkerOptions().position(test).title("test"));
        this.mMap.moveCamera(CameraUpdateFactory.newLatLng(test));
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Toast.makeText(this.getApplicationContext(),"ETAPE 3 : READY",Toast.LENGTH_SHORT).show();
        //setUpMap();
       // LatLng teyst = new LatLng(-88,888);
       // googleMap.addMarker(new MarkerOptions().position(teyst).title("Marker in ?"));
       // googleMap.moveCamera(CameraUpdateFactory.newLatLng(teyst));
        setFABView();
        //Add markers
        addMarkerToMap(googleMap);
        //Add listener
        googleMap.setOnMarkerClickListener(new OnClickMarker(this));
    }

    private void addMarkerToMap(GoogleMap googleMap) {
        //googleMap.clear();
        if(googleMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            googleMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
        }
        //mMap = googleMap;
        // Check if we were successful in obtaining the map.
        if (googleMap != null) {
            if(this.listebar.size()>0){
                //this.listebar.add(new BarMap("Truck",55,99));
                //this.listebar.add(new BarMap("Machin",44,36));
                //this.listebar.add(new BarMap("Bidule",23,56));
                //this.listebar.add(new BarMap("Chouet", 45, 67));
                //Log.d("TAILLELISTEMARKER", String.valueOf(this.listebar.size()));
                Toast.makeText(this.getApplicationContext(),"ETAPE 4 : MARK",Toast.LENGTH_SHORT).show();
                LatLng ll = null;
                for(BarMap tmp : this.listebar){
                     ll = new LatLng(tmp.getX(),tmp.getY());
                    BitmapDescriptor bitmapMarker;
                    if(tmp.getY()>50) {
                        bitmapMarker =  BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED);
                    } else {
                        bitmapMarker = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN);
                    }
                    //MarkerOptions markerOptions = new MarkerOptions().position(ll).title(tmp.getNom()).icon(bitmapMarker);
                    //googleMap.addMarker(markerOptions);

                    MarkerOptions markerOption = new MarkerOptions().position(ll);
                    markerOption.title(tmp.getNom());
                    markerOption.icon(bitmapMarker);
                    Marker currentMarker = googleMap.addMarker(markerOption);
                    Toast.makeText(this.getApplicationContext(),"ETAPE 5 :: "+currentMarker.getTitle(),Toast.LENGTH_SHORT).show();
                    currentMarker.setVisible(true);
                    //currentMarker.setDraggable(true);
                    currentMarker.showInfoWindow();
                    googleMap.moveCamera(CameraUpdateFactory.newLatLng(ll));




                    //googleMap.addMarker(new MarkerOptions().position(ll).title(tmp.getNom()).icon(bitmapMarker));
                  //  Log.d("ADDMARKER", "Bar marker named " + tmp.getNom() + " was added ");
                    //Toast.makeText(this.getApplicationContext(),"ETAPE 5 : "+tmp.getNom(),Toast.LENGTH_SHORT).show();
                }
            }
        }

    }

    private void prepareMarkerToMap(String nom, String coordX, String coordY) {
        Toast.makeText(this.getApplicationContext(),"ETAPE 2 : STORE",Toast.LENGTH_SHORT).show();
        Log.e("OMGISHER2,",coordY);
        BarMap tmp = new BarMap(nom,Double.parseDouble(coordX),Double.parseDouble(coordY));
        this.listebar.add(tmp);
    }

    @Override
    public void processFinish(String output, int flag) {
        Toast.makeText(this.getApplicationContext(),"ETAPE 1 : RECEIVE",Toast.LENGTH_SHORT).show();
        switch (flag){
            case 1 :
                try {
                    JSONArray result = new JSONArray(output);
                    for (int i = 0; i < result.length(); i++){
                        JSONObject row = result.getJSONObject(i);
                        prepareMarkerToMap(row.getString("nom_bar"), row.getString("localisation_x_bar"), row.getString("localisation_y_bar"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //Affichage de la map "vierge"
                SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
                mapFragment.getMapAsync(this);
                break;
            case 2 : //On a ajouté le bar dans la base de données au currentUser (user id = 12 pour le moment)
                Toast.makeText(this.getApplicationContext(), "BAR AJOUTE A CURRENT", Toast.LENGTH_SHORT).show();
                break;
            case 3 : //On a ajouté le bar dans la base de données
                Toast.makeText(this.getApplicationContext(), "BAR AJOUTER A ALL", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_focus, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        //int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        //if (id == R.id.action_settings) {
        //    return true;
        //}

        //return super.onOptionsItemSelected(item);

        OnActionBarMenuSelected tmpMnger = new OnActionBarMenuSelected(this,item);
        return tmpMnger.manageActionUsers();
    }

    private void setToolBarView() {
        this.myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(this.myToolbar);
        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        ab.setTitle("Ajout bar");
        // Enable the Up (back P/R à l'activité parente) button
        ab.setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public void addMarker(LatLng ll, String name, GoogleMap googleMap) {
        Toast.makeText(this.getApplicationContext(),"On est dans le delegate",Toast.LENGTH_SHORT).show();
        if(googleMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            googleMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            //mMap = googleMap;
            // Check if we were successful in obtaining the map.
            if (googleMap != null) {
                MarkerOptions markerOption = new MarkerOptions().position(ll);
                markerOption.title(name);
                Marker currentMarker = googleMap.addMarker(markerOption);
                Toast.makeText(this.getApplicationContext(),"A ajouter le marker : "+currentMarker.getTitle(),Toast.LENGTH_SHORT).show();
                currentMarker.setVisible(true);
                currentMarker.showInfoWindow();
               // mMap.addMarker(new MarkerOptions().position(ll).title(name).snippet(name));
                googleMap.moveCamera(CameraUpdateFactory.newLatLng(ll));
                CameraUpdate zoom=CameraUpdateFactory.zoomTo(15);
                googleMap.animateCamera(zoom);
                Toast.makeText(this.getApplicationContext(),"A zoom zomm",Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this.getApplicationContext(),"Pas execute",Toast.LENGTH_SHORT).show();
            }
        }

    }


}
