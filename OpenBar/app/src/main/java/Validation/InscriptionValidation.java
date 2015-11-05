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
    Activity myActivity;

    public InscriptionValidation(Activity act) {
       super();
        this.myActivity = act;
    }

    public boolean validate() {
        boolean ok = true;
        for (RadioGroup group : this.getMyFieldsRadioButtonGroup()) {
            switch (group.getId()) {
                case R.id.group_sexe_inscription:
                    if(group.getCheckedRadioButtonId() == -1) {
                        RadioButton tmp = (RadioButton)myActivity.findViewById(R.id.radio_male);
                        tmp.setError("You must choose a type !");
                    }
            }
        }
        for (EditText field : this.getMyFieldsEditText()) {
            switch (field.getId()) {
                case R.id.login_inscription:
                    if (field.getText().toString().length() < 5) {
                        ok = false;
                        field.setError("Pseudo is lg than 5 chars");
                    }
                    break;
                case R.id.password_inscription:
                    if (field.getText().toString().length() < 8) {
                        ok = false;
                        field.setError("Password is lg than 8chars");
                    }
                    break;
                case R.id.email_inscription:
                    ok = isValidEmail(field.getText());
                    if (!ok) {
                        field.setError("Email is not well-formed");
                    }
                    break;
            }
        }
        return ok;
    }

    public boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }
}
