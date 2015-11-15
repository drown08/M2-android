package com.openbar.frappereauolivier.openbar.Activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.openbar.frappereauolivier.openbar.R;

import Adapter.DetailBarAdapter;
import Evenement.OnActionBarMenuSelected;
import Model.Bar;

public class BarActivity extends AppCompatActivity {
    Toolbar myToolbar;
    public static Bar myBar;
    ViewPager pager;
    DetailBarAdapter adapter;
    TabLayout tabLayout;
    CharSequence titles[]={"Infos","Contacts","Services"};
    int nbOfTabs = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar);
        setSpecifyBar();
        setSpecifyPresentation();
    }

    private void setSpecifyBar() {
        //TODO : Reccuperer l'id du bar depuis la transaction et aller le chercher en bdd
        String barName = this.getIntent().getStringExtra("bar"); //On réccupère l'id via la précédente activité
        //Puis simulation d'appelle à la BDD via CommunicationService pour réccupérer le bar en question
        myBar = new Bar("Test"+barName,"ppcdkd",R.drawable.options_test);
    }

    private void setSpecifyPresentation() {
        setToolBarView();
        setTheTabViewer();
    }

    private void setTheTabViewer() {
        //On creer l'adapter (TODO : Passer l'objet Bar focus si besoin)
        this.adapter = new DetailBarAdapter(getSupportFragmentManager(),titles,nbOfTabs);

        //On creer le focus, i.e le ViewPager et on lie les deux
        this.pager = (ViewPager) this.findViewById(R.id.pager);
        this.pager.setAdapter(this.adapter);

        initTheTabber();

    }

    private void initTheTabber() {

        this.tabLayout = (TabLayout) this.findViewById(R.id.tablayout);
        this.tabLayout.setupWithViewPager(this.pager);

    }

    private void setToolBarView() {
        this.myToolbar = (Toolbar) findViewById(R.id.my_toolbar_bar);
        setSupportActionBar(this.myToolbar);
        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        ab.setTitle(myBar.getNom());
        // Enable the Up (back P/R à l'activité parente) button
        ab.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
       // int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        //if (id == R.id.action_settings) {
         //   return true;
       // }

       // return super.onOptionsItemSelected(item);

        OnActionBarMenuSelected tmpMnger = new OnActionBarMenuSelected(this,item);
        return tmpMnger.manageActionUsers();
    }
}
