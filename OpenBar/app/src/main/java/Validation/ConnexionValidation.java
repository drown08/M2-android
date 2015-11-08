package Validation;

import android.app.Activity;
import android.widget.EditText;

import com.openbar.frappereauolivier.openbar.R;

/**
 * Created by Frappereau Olivier on 05/11/2015.
 * Offer validation for a specifiate form
 */
public class ConnexionValidation extends MyValidation {


    public ConnexionValidation(Activity activity) {
        super(activity);
    }

    public boolean validate() {
        boolean ok = true;
        for (EditText field : this.getMyFieldsEditText()) {
            switch (field.getId()) {
                case R.id.login_connexion:
                    if (field.getText().toString().length() == 0) {
                        ok = false;
                        field.setError("Pseudo is required");
                    }
                    break;
                case R.id.password_connexion:
                    if (field.getText().toString().length() == 0) {
                        ok = false;
                        field.setError("Password is required");
                    }
                    break;
            }
        }
        return ok;
    }

}
