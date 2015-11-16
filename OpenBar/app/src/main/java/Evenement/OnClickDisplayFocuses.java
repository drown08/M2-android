package Evenement;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.openbar.frappereauolivier.openbar.R;

/**
 * Created by Frappereau Olivier on 16/11/2015.
 */
public class OnClickDisplayFocuses implements View.OnClickListener {
    private Context context;
    private RecyclerView target1;
    private TextView target2;
    private int source;

    public  OnClickDisplayFocuses(Context context, RecyclerView target1,TextView target2,int source) {
        this.context = context;
        this.target1 = target1;
        this.source = source;
        this.target2 = target2;
    }

    @Override
    public void onClick(View v) {
        switch (this.source) {
            case R.id.daily_picture :
                if(target1!=null) {
                    Toast.makeText(context,"Click pic",Toast.LENGTH_SHORT).show();
                    hideOrDisplay(target1);
                } else {
                    Toast.makeText(context,"erreur, bizarre..",Toast.LENGTH_SHORT).show();
                }
                    break;
            case R.id.daily_activity :
                if(target2!=null) {
                    Toast.makeText(context,"Click act",Toast.LENGTH_SHORT).show();
                   hideOrDisplay(target2);
                }
                    break;
        }

    }

    private void hideOrDisplay(View v) {
        if(v.getVisibility() == View.GONE){
            v.setVisibility(View.VISIBLE);
        } else {
            v.setVisibility(View.GONE);
        }
    }

}
