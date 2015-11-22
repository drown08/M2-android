package Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import FragmentBar.TabContactFriends;
import FragmentBar.TabContactInfos;
import FragmentBar.TabContactNotifications;

/**
 * Created by Frappereau Olivier on 22/11/2015.
 */
public class DetailProfilAdapter extends FragmentStatePagerAdapter {

    CharSequence titles[];
    int nbOfTabs;

    public DetailProfilAdapter(FragmentManager supportFragmentManager, CharSequence[] titles, int nbOfTabs) {
        super(supportFragmentManager);
        this.titles = titles;
        this.nbOfTabs = nbOfTabs;
    }

    @Override
    public int getCount() {
        return this.nbOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment returnStatment = new Fragment();
        switch (position) {
            case 0:
                returnStatment = new TabContactInfos();
                break;
            case 1:
                returnStatment = new TabContactNotifications();
                break;
            case 2:
                returnStatment = new TabContactFriends();
        }
        return  returnStatment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return this.titles[position];
    }
}
