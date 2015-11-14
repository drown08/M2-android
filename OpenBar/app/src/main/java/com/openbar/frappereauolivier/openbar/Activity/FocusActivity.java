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

import com.openbar.frappereauolivier.openbar.R;

import java.util.ArrayList;

import Adapter.BarAdapter;
import Evenement.OnActionBarMenuSelected;
import Evenement.OnClickAddBarFAB;
import Evenement.OnRefreshListBar;
import Model.Bar;

public class FocusActivity extends AppCompatActivity {
    Toolbar myToolbar;
    RecyclerView recListBar;
   public BarAdapter barAdapter;
    ArrayList<Bar> myBars;
    FloatingActionButton myFABAddBar;
    FloatingActionButton myFABMiniAddBar1;
    FloatingActionButton myFABMiniAddBar2;
    SwipeRefreshLayout mySRL;

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

        setFABView();

    }

    private void setFABView() {

        this.myFABAddBar = (FloatingActionButton)findViewById(R.id.fab);
        this.myFABAddBar.setOnClickListener(new OnClickAddBarFAB(this,myFABAddBar));
        this.myFABAddBar.show();

        this.myFABMiniAddBar1 = (FloatingActionButton) findViewById(R.id.fab_mini_1);
        this.myFABMiniAddBar1.setOnClickListener(new OnClickAddBarFAB(this,myFABMiniAddBar1));
        this.myFABMiniAddBar1.hide();

        this.myFABMiniAddBar2 = (FloatingActionButton) findViewById(R.id.fab_mini_2);
        this.myFABMiniAddBar2.setOnClickListener(new OnClickAddBarFAB(this,myFABMiniAddBar2));
        this.myFABMiniAddBar2.hide();
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

        //Set refresh action to the liste
        mySRL = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        mySRL.setOnRefreshListener(new OnRefreshListBar(this,mySRL));

        //Adjust when refresh action is available
        //recListBar.addOnScrollListener(new OnScrollAction(this,mySRL,recListBar));
        recListBar.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                //super.onScrolled(recyclerView, dx, dy);
                boolean enable = false;
                if(recListBar != null && recListBar.getChildCount() > 0){
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
        });
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
