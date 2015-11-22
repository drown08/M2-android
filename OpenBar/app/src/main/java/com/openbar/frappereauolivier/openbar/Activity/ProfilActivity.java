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

import Adapter.DetailProfilAdapter;
import Evenement.OnActionBarMenuSelected;
import Model.Contact;

public class ProfilActivity extends AppCompatActivity {
    Toolbar myToolbar;
    public static Contact currentUser;
    ViewPager pager;
    DetailProfilAdapter adapter;
    TabLayout tabLayout;
    CharSequence titles[]={"Mes infos","Mes notifications","Mes amis"};
    int nbOfTabs = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        setCurrentUser();
        setSpecifyPresentation();
    }

    private void setCurrentUser() {
        //TODO : Reccuperer l'id de l'user depuis la transaction et aller chercher le User (contact) Dans la BDD
        //String currentName = this.getIntent().getStringExtrat("user");
        //CommunicationService comm = new ...
        currentUser = new Contact(R.drawable.profil,"testUser","bidule");

    }

    private void setSpecifyPresentation() {
        setToolBarView();
        setTheTabViewer();
    }

    private void setTheTabViewer() {
        //TODO : Passer l'objet currentUser si besoin)
        this.adapter = new DetailProfilAdapter(getSupportFragmentManager(),titles,nbOfTabs);

        this.pager = (ViewPager) this.findViewById(R.id.pager_profil);
        this.pager.setAdapter(this.adapter);

        initTheTabber();
    }

    private void initTheTabber() {
        this.tabLayout = (TabLayout) this.findViewById(R.id.tablayout_profil);
        this.tabLayout.setupWithViewPager(this.pager);
    }

    private void setToolBarView() {
        this.myToolbar = (Toolbar) findViewById(R.id.my_toolbar_profil);
        setSupportActionBar(this.myToolbar);

        ActionBar ab = getSupportActionBar();
        ab.setTitle(currentUser.getPrenom());
        ab.setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_profil, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        OnActionBarMenuSelected tmpManager = new OnActionBarMenuSelected(this,item);
        return tmpManager.manageActionUsers();
    }
}
