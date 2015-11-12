package com.openbar.frappereauolivier.openbar.Activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.openbar.frappereauolivier.openbar.R;

import java.util.ArrayList;

import Adapter.BarAdapter;
import Evenement.OnActionBarMenuSelected;
import Model.Bar;

public class FocusActivity extends AppCompatActivity {
    Toolbar myToolbar;
    RecyclerView recListBar;
   public BarAdapter barAdapter;
    ArrayList<Bar> myBars;

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
        setToolBarView();

        setListOfBarView();

    }

    private void setToolBarView() {
        this.myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(this.myToolbar);
        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        ab.setTitle("Mes bars");
        // Enable the Up (back P/R à l'activité parente) button
        ab.setDisplayHomeAsUpEnabled(true);

    }

    private void setListOfBarView(){
        recListBar = (RecyclerView) findViewById(R.id.cardListBar);
        recListBar.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recListBar.setLayoutManager(llm);
        //Call Service to getBars
        myBars = setListBar();
        barAdapter = new BarAdapter(myBars,this);
        recListBar.setAdapter(barAdapter);
        recListBar.setItemAnimator(new DefaultItemAnimator());
    }

    private ArrayList<Bar> setListBar() {
        ArrayList<Bar> tmp = new ArrayList<Bar>();
        //TODO : Service qui appelle la communication Serveur avec comme info UserPseudo
        // Et comme serviceDuServeur : donne moi la liste des bars de UserPseudo
        tmp.add(new Bar("Bar 1","Happy Hours",R.drawable.options_test));
        tmp.add(new Bar("Bar 2","Diffuse match rugby",R.drawable.options_test));
        tmp.add(new Bar("Bar 3","Rien",R.drawable.options_test));
        tmp.add(new Bar("Bar 4","3 amis ici",R.drawable.options_test));
        tmp.add(new Bar("Bar 5","Ajouter hier",R.drawable.options_test));
        tmp.add(new Bar("Bar 6", "Folie !", R.drawable.options_test));
        tmp.add(new Bar("Bar 7","Diffuse match basket",R.drawable.options_test));
        tmp.add(new Bar("Bar 8","Diffuse match Hockey",R.drawable.options_test));
        return tmp;
    }
}
