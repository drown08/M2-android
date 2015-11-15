package FragmentBar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.openbar.frappereauolivier.openbar.Activity.BarActivity;
import com.openbar.frappereauolivier.openbar.R;

/**
 * Created by Frappereau Olivier on 15/11/2015.
 */
public class TabBarInfos extends Fragment {
    TextView infos;
    View mainV;

    public  TabBarInfos() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.mainV = inflater.inflate(R.layout.tab_bar_infos,container,false);

        customTheView();
        return this.mainV;
    }

    private void customTheView() {
        //this.infos = (TextView) container.findViewById(R.id.focus_infos);
        //this.infos.setText(BarActivity.myBar.getNom());
        //this.infos.setText("BOUDIN");
        //Log.d("getView",this.infos.toString());

        this.infos = (TextView) this.mainV.findViewById(R.id.focus_infos);


        //this.infos = (TextView) this.mainV;
        this.infos.setText(BarActivity.myBar.getNom());

    }
}
