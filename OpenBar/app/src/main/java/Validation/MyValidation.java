package Validation;

import android.app.Activity;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.openbar.frappereauolivier.openbar.R;

import java.util.ArrayList;

/**
 * Created by Frappereau Olivier on 05/11/2015.
 */
public class MyValidation {

    private ArrayList<EditText> myFieldsEditText;
    private ArrayList<RadioGroup> myFieldsRadioButtonGroup;
    Activity myActivity;

    public MyValidation(Activity act) {
        this.myFieldsEditText = new ArrayList<EditText>();
        this.myFieldsRadioButtonGroup = new ArrayList<RadioGroup>();
        this.myActivity = act;
    }

    public void setMyFieldsEditText(ArrayList<EditText> lv) {
        this.myFieldsEditText = lv;
    }

    public ArrayList<EditText> getMyFieldsEditText() {
        return  this.myFieldsEditText;
    }

    public ArrayList<RadioGroup> getMyFieldsRadioButtonGroup() {
        return myFieldsRadioButtonGroup;
    }

    public void setMyFieldsRadioButtonGroup(ArrayList<RadioGroup> myFieldsRadioButtonGroup) {
        this.myFieldsRadioButtonGroup = myFieldsRadioButtonGroup;
    }

    public void addFieldEditText(EditText field) {
        this.myFieldsEditText.add(field);
    }

    public void addFieldEditTextAt(int index, EditText field) {
        this.myFieldsEditText.add(index, field);
    }

    public void removeFieldEditText(EditText field) {
        this.myFieldsEditText.remove(field);
    }

    public void removeFieldEditTextAt(int index) {
        this.myFieldsEditText.remove(index);
    }

    public void addFieldRadioGroupButton(RadioGroup field) {
        this.myFieldsRadioButtonGroup.add(field);
    }

    public void addFieldRadioGroupButtonAt(int index, RadioGroup field) {
        this.myFieldsRadioButtonGroup.add(index, field);
    }

    public void removeFieldRadioGroupButton(RadioGroup field) {
        this.myFieldsRadioButtonGroup.remove(field);
    }

    public void removeFieldRadioGroupButtonAt(int index) {
        this.myFieldsRadioButtonGroup.remove(index);
    }

    protected boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    protected boolean areTheSamePassword() {
        EditText mdp1 = (EditText)this.myActivity.findViewById(R.id.password_inscription);
        EditText mdp2 = (EditText)this.myActivity.findViewById(R.id.password_confirm_inscription);
        return (mdp1.getText().toString().equals(mdp2.getText().toString()));
    }


}
