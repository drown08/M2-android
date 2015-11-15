package Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import FragmentBar.TabBarContact;
import FragmentBar.TabBarInfos;
import FragmentBar.TabBarServices;


/**
 * Created by Frappereau Olivier on 15/11/2015.
 */
public class DetailBarAdapter extends FragmentStatePagerAdapter {

    CharSequence titles[];
    int nbOfTab;

    public DetailBarAdapter(FragmentManager fm, CharSequence charSequence[], int nbOfTab) {
        super(fm);
        this.titles = charSequence;
        this.nbOfTab = nbOfTab;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment returnStatment = new Fragment();
     switch (position) {
         case 0 :
            returnStatment = new TabBarInfos();
             break;
         case 1 :
             returnStatment = new TabBarContact();
             break;
         case 2 :
             returnStatment = new TabBarServices();
             break;
     }
        return returnStatment;
    }

    @Override
    public int getCount() {
        return this.nbOfTab;
    }

    @Override
    public CharSequence getPageTitle(int position) {return this.titles[position];}
}
