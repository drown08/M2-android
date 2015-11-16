package Evenement;

import android.app.Dialog;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Frappereau Olivier on 16/11/2015.
 */
public class OnAddNewActivity implements View.OnClickListener {
    Dialog d;
    EditText et;
    EditText etName;
    TextView tv;
    public OnAddNewActivity(Dialog dialog, EditText edit, EditText editName,TextView textView) {
        this.d = dialog;
        this.et = edit;
        this.etName = editName;
        this.tv = textView;
    }
    @Override
    public void onClick(View v) {
        String newActivity = this.etName.getText().toString() + " : " + this.et.getText().toString();
        this.tv.append(newActivity);
        this.d.dismiss();

    }
}
