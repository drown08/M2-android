package Validation;

import android.app.Activity;
import android.widget.EditText;

import com.openbar.frappereauolivier.openbar.R;

/**
 * Created by Frappereau Olivier on 08/11/2015.
 */
public class ForgotPasswordValidation extends MyValidation {

    public ForgotPasswordValidation(Activity act) {
        super(act);
    }

    public boolean validate() {
        boolean ok = true;
        for (EditText field : this.getMyFieldsEditText()) {
            switch (field.getId()) {
                case R.id.login_send_mail:
                    if (field.getText().toString().length() < 5) {
                        ok = false;
                        field.setError(myActivity.getApplicationContext().getString(R.string.pseudo_length));
                    }
                    break;
                case R.id.email_send_mail:
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
        return ok;
    }
}
