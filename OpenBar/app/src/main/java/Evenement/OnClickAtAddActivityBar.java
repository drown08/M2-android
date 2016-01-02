package Evenement;

import android.app.Dialog;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.openbar.frappereauolivier.openbar.R;

/**
 * Created by Frappereau Olivier on 02/01/2016.
 */
public class OnClickAtAddActivityBar implements View.OnClickListener {
    CheckBox present;
    TextView infos;
    public OnClickAtAddActivityBar (CheckBox checkBox, TextView textView) {
        this.present = checkBox;
        this.infos = textView;
    }

    @Override
    public void onClick(View v) {
        if(this.present.isChecked()) {
            Dialog d = new Dialog(v.getContext());
            d.setContentView(R.layout.button_add_activity);
            d.setTitle("Add activity to the bar");
            d.setCancelable(true);
            EditText edit = (EditText) d.findViewById(R.id.form_add_activity);
            EditText editName = (EditText) d.findViewById(R.id.form_add_title_activity);
            Button b = (Button) d.findViewById(R.id.add_activity);
            b.setOnClickListener(new OnAddNewActivity(d, edit, editName, infos));
            d.show();
        } else {
            Toast.makeText(this.present.getContext(), "You're not here", Toast.LENGTH_SHORT).show();
        }
    }
}
