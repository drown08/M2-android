package com.openbar.frappereauolivier.openbar.Activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.openbar.frappereauolivier.openbar.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import Adapter.BarAdapter;
import CommunicationServeur.AsyncTaskResponse;
import CommunicationServeur.CommunicationService;
import Evenement.OnActionBarMenuSelected;
import Evenement.OnClickAddBarFAB;
import Evenement.OnRefreshListBar;
import Evenement.OnScrollListListener;
import Model.Bar;
import Model.Contact;

public class FocusActivity extends AppCompatActivity implements AsyncTaskResponse {
    public Toolbar myToolbar;
    public RecyclerView recListBar;
    public BarAdapter barAdapter;
    public ArrayList<Bar> myBars;
    public FloatingActionButton myFABAddBar;
    public SwipeRefreshLayout mySRL;
    public TextView myTextViewEmptyList;
    public static Contact currentUser;

//TODO : Installer et utiliser ActionBarSherlock ?
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_focus);

        setSpecifyPresentation();

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

    private void setSpecifyPresentation() {

        setCurrentUser();

        setToolBarView();

       // setListOfBarView();

        setFABView();

    }

    private void setCurrentUser() {
        //1. Réccuppération de l'id depuis la connexion
        if(this.getIntent().getStringExtra("user")!=null) {
         String id = this.getIntent().getStringExtra("user");
            //2. Réccuppération en bdd du JSON user
            CommunicationService getContact = new CommunicationService(this,this,false,1);
            getContact.addParams("ctrl","getCurrentUser");
            getContact.addParams("id",id);
            getContact.sendToServer();
            getContact.flush();
            //3. Bind du JSON avec un objet Contact
        }
        //TODO : else : Renvoyer sur form inscription.
    }

    private void setFABView() {

        this.myFABAddBar = (FloatingActionButton)findViewById(R.id.fab);
        this.myFABAddBar.setOnClickListener(new OnClickAddBarFAB(this, myFABAddBar));
        this.myFABAddBar.show();
    }

    private void setToolBarView() {
        this.myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(this.myToolbar);
        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        ab.setTitle(R.string.my_bars);
        // Enable the Up (back P/R à l'activité parente) button
        //ab.setDisplayHomeAsUpEnabled(true);

    }

    private void setListOfBarView(){
        if(this.recListBar == null) {
            this.recListBar = (RecyclerView) findViewById(R.id.cardListBar);
            //this.recListBar.setHasFixedSize(true);
            LinearLayoutManager llm = new LinearLayoutManager(this);
            llm.setOrientation(LinearLayoutManager.VERTICAL);
            this.recListBar.setLayoutManager(llm);
            //Call Service to getBars
            //setListBar();
            //this.myBars = setListBar();
        }
    }

    private void setListBar() {
        //TODO : Service qui appelle la communication Serveur avec comme info UserPseudo
        CommunicationService getBarsOfUser = new CommunicationService(this,this,true,2);
        getBarsOfUser.addParams("ctrl", "getBarOfCurrentUser");//getBarOfCurrentUser à remplacer à la place
        getBarsOfUser.addParams("id_user", "12");
        getBarsOfUser.sendToServer();
        getBarsOfUser.flush();
    }

    public void setListAdapterBar() {
        //if(this.barAdapter == null){
            this.barAdapter = new BarAdapter(myBars,this);
            this.recListBar.setAdapter(barAdapter);
            this.recListBar.setItemAnimator(new DefaultItemAnimator());
            //if(this.mySRL == null){
                //Set refresh action to the liste
                this.mySRL = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
                this.mySRL.setOnRefreshListener(new OnRefreshListBar(this, this.mySRL));
                //Adjust when refresh action is available
                //recListBar.addOnScrollListener(new OnScrollAction(this,mySRL,recListBar));
                this.recListBar.addOnScrollListener(new OnScrollListListener(mySRL,recListBar));
               /* this.recListBar.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                        //super.onScrolled(recyclerView, dx, dy);
                        boolean enable = false;
                        if (recListBar != null && recListBar.getChildCount() > 0) {
                            // check if the first item of the list is visible
                            //boolean firstItemVisible = true;
                            //boolean firstItemVisible = recListBar.() == 0;
                            // check if the top of the first item is visible
                            boolean topOfFirstItemVisible = recListBar.getChildAt(0).getTop() == 0;
                            // enabling or disabling the refresh layout
                            //enable = firstItemVisible && topOfFirstItemVisible;
                            enable = topOfFirstItemVisible;
                        }
                        Log.d("MethodCalled", String.valueOf(enable));
                        mySRL.setEnabled(enable);

                    }
                });*/
            //}
       // }

    }

    public Bar getBarByRange(int range) {
        return this.myBars.get(range);
    }

    @Override
    public void onResume(){
        super.onResume();
        // put your code here...

    }

    @Override
    public void processFinish(String output, int flag) {
        switch (flag) {
            case 1 : //Récuppération du current utilisateur
                Contact contact = new Contact();
                try {
                        JSONArray result = new JSONArray(output);
                        for (int i = 0; i < result.length(); i++){
                            JSONObject row = result.getJSONObject(i);
                            contact.setNom(row.getString("pseudo_user"));
                            contact.setPrenom(row.getString("pseudo_user") + "(prenom)");
                            //TODO : Voir comment faire pour les images
                            //contact.setRefImg(R.drawable.common_google_signin_btn_icon_dark_pressed);
                            contact.setRefImg(row.getInt("id_user"));
                        }
                    this.currentUser = contact;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                setListBar();
                break;
            case 2 : //Réccupération des bars du current user
                ArrayList<Bar> tmp = new ArrayList<Bar>();
                try {
                    JSONArray result = new JSONArray(output);
                    for(int i = 0; i < result.length(); i++) {
                        JSONObject row = result.getJSONObject(i);
                        Bar barTmp = new Bar();
                        //TODO : Faire la même côté serveur et modèle pour faire les tests
                        barTmp.setNom(row.getString("nom_bar"));
                        barTmp.setInfos(row.getString("info_bar"));
                        barTmp.setLogo(R.drawable.common_google_signin_btn_icon_light_normal);
                        tmp.add(barTmp);
                    }
                    Toast.makeText(getApplicationContext(),"TAILLE LISTE : "+String.valueOf(tmp.size()),Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //TODO : Gerer le cas liste vide ici.
                setEmptyListView();
                setListOfBarView();
                Log.d("testlist", String.valueOf(tmp.size()));
                if(tmp.isEmpty()){
                    this.recListBar.setVisibility(View.GONE);
                    this.myTextViewEmptyList.setVisibility(View.VISIBLE);
                } else {
                    this.myBars  =  tmp;
                    this.recListBar.setVisibility(View.VISIBLE);
                    this.myTextViewEmptyList.setVisibility(View.GONE);
                    setListAdapterBar();
                }
                break;
            case 3 : //DELETE BAR
                Toast.makeText(getApplicationContext(),"Bar deleted ",Toast.LENGTH_SHORT).show();
                break;
            case 4 : //RE-ADD-DELETED-BAR (UNDO)
                Toast.makeText(getApplicationContext(),"Undo Bar Deleted ",Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void setEmptyListView(){
        if(this.myTextViewEmptyList == null) {
            this.myTextViewEmptyList = (TextView) this.findViewById(R.id.empty_list_bar);
            this.myTextViewEmptyList.setVisibility(View.INVISIBLE);
        }
    }
}
