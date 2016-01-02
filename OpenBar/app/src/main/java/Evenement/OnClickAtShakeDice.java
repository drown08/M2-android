package Evenement;

import android.view.View;

import com.openbar.frappereauolivier.openbar.Activity.DiceShakerActivity;

import FragmentBar.TabBarServices;
import Transaction.Transaction;

/**
 * Created by Frappereau Olivier on 30/12/2015.
 */
public class OnClickAtShakeDice implements View.OnClickListener {

    TabBarServices context;

    public OnClickAtShakeDice(TabBarServices context){
        this.context = context;
    }

    @Override
    public void onClick(View v) {
        Transaction transaction = new Transaction(this.context.getActivity(), DiceShakerActivity.class);
        transaction.runWithoutExit();
    }
}
