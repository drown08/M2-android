package Validation;

import android.app.Activity;
import android.widget.EditText;

import com.openbar.frappereauolivier.openbar.R;

/**
 * Created by Frappereau Olivier on 16/11/2015.
 */
public class AjoutActiviteBarValidation extends MyValidation {
    public AjoutActiviteBarValidation(Activity act) {
        super(act);
    }

    public boolean validate() {
        boolean ok = true;
        for(EditText field : this.getMyFieldsEditText()) {
            switch (field.getId()) {
                case R.id.form_add_title_activity :
                    if(field.getText().toString().isEmpty()) {
                        ok=false;
                        field.setError("The name must be not empty");
                    }
                    break;
                case R.id.form_add_activity :
                    if(ok) {
                        if(field.getText().toString().isEmpty()) {
                            ok = false;
                            field.setError("You must add a description");
                        }
                    }
                    break;
            }
        }
        return ok;
    }
}
