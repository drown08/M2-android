package FragmentBar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.openbar.frappereauolivier.openbar.Activity.BarActivity;
import com.openbar.frappereauolivier.openbar.R;

/**
 * Created by Frappereau Olivier on 15/11/2015.
 */
public class TabBarContact extends Fragment {
    ImageView myImg;
    View mainV;

    public TabBarContact() {super();}

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       this.mainV = inflater.inflate(R.layout.tab_bar_contact,container,false);
        customTheView(container);
        return this.mainV;
    }

    private void customTheView(ViewGroup container) {
        this.myImg = (ImageView) this.mainV.findViewById(R.id.focus_contacts);
        this.myImg.setImageResource(BarActivity.myBar.getLogo());
    }
}
