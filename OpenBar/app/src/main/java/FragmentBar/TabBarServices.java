package FragmentBar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.openbar.frappereauolivier.openbar.Activity.BarActivity;
import com.openbar.frappereauolivier.openbar.R;

import Evenement.OnClickAtShakeDice;

/**
 * Created by Frappereau Olivier on 15/11/2015.
 */
public class TabBarServices extends Fragment {
    TextView services;
    TextView diceText;
    TextView service2Text;
    TextView service3Text;
    Button dice;
    Button service2;
    Button service3;
    View mainV;
    public  TabBarServices() {
        super();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.mainV = inflater.inflate(R.layout.tab_bar_services,container,false);
        customTheView(container);
        return this.mainV;
    }
    private void customTheView(ViewGroup container) {

        setTitleOfBarServices();

        setDiceGameLauncher();

        setService2Launcher();

        setService3Launcher();

    }

    private void setTitleOfBarServices() {
        this.services = (TextView) this.mainV.findViewById(R.id.focus_services);
        this.services.setText(BarActivity.myBar.getInfos());
        //this.services.setText("Shake dice !");
    }

    private void setDiceGameLauncher() {
        this.diceText = (TextView) this.mainV.findViewById(R.id.textView2);
        this.diceText.setText("Games in bar");
        this.dice = (Button) this.mainV.findViewById(R.id.button);
        this.dice.setText("Shake dice");
        this.dice.setOnClickListener(new OnClickAtShakeDice(this));
    }

    private void setService2Launcher() {
        this.service2Text = (TextView) this.mainV.findViewById(R.id.textView3);
        this.service2Text.setText("Sam rescue");
        this.service2 = (Button) this.mainV.findViewById(R.id.button3);
        this.service2.setText("Comming soon");
        this.service2.setClickable(false);
    }

    private void setService3Launcher() {
        this.service3Text = (TextView) this.mainV.findViewById(R.id.textView4);
        this.service3Text.setText("Ethylotest");
        this.service3 = (Button) this.mainV.findViewById(R.id.button2);
        this.service3.setText("Comming soon");
        this.service3.setClickable(false);
    }

}
