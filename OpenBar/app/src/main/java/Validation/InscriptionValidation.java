package Validation;

import android.app.Activity;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.openbar.frappereauolivier.openbar.R;

/**
 * Created by Frappereau Olivier on 05/11/2015.
 * Offer validation for a specifiate form
 */
public class InscriptionValidation extends MyValidation {

    public InscriptionValidation(Activity act) {
       super(act);
    }

    public boolean validate() {
        boolean ok = true;
        for (RadioGroup group : this.getMyFieldsRadioButtonGroup()) {
            switch (group.getId()) {
                case R.id.group_sexe_inscription:
                    if(group.getCheckedRadioButtonId() == -1) {
                        RadioButton tmp = (RadioButton)myActivity.findViewById(R.id.radio_female);
                        ok = false;
                        tmp.setError(myActivity.getApplicationContext().getString(R.string.sexe_type));
                    } else {
                        RadioButton tmp = (RadioButton)myActivity.findViewById(R.id.radio_female);
                        tmp.setError(null);
                    }
            }
        }
        if(ok)
        {
            for (EditText field : this.getMyFieldsEditText()) {
                switch (field.getId()) {
                    case R.id.login_inscription:
                        if (field.getText().toString().length() < 5) {
                            ok = false;
                            field.setError(myActivity.getApplicationContext().getString(R.string.pseudo_length));
                        }
                        break;
                    case R.id.password_inscription:
                        if (ok)
                        {
                            if (field.getText().toString().length() < 8) {
                                ok = false;
                                field.setError(myActivity.getApplicationContext().getString(R.string.password_length));
                            }
                        }
                        break;
                    case R.id.email_inscription:
                        if (ok)
                        {
                            ok = isValidEmail(field.getText());
                            if (!ok) {
                                field.setError(myActivity.getApplicationContext().getString(R.string.email_well_formed));
                            }
                        }
                        break;
                }
            }
        }
        if (ok) {
            ok = areTheSamePassword();
            if(!ok) {
                EditText tmp = (EditText)myActivity.findViewById(R.id.password_confirm_inscription);
                tmp.setError(myActivity.getApplicationContext().getString(R.string.password_same));
            }
        }
        return ok;
    }

}
