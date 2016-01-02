package Evenement;

import android.app.Activity;
import android.content.Intent;
import android.provider.MediaStore;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.openbar.frappereauolivier.openbar.Activity.BarActivity;

/**
 * Created by Frappereau Olivier on 02/01/2016.
 */
public class OnClickAtTakePhotoBar implements  View.OnClickListener {
    CheckBox present;
    BarActivity myActivity;

    public OnClickAtTakePhotoBar (CheckBox checkBox, Activity activity) {
        this.present = checkBox;
        this.myActivity = (BarActivity) activity;
    }

    @Override
    public void onClick(View v) {
        if(this.present.isChecked()) {
            Intent itent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            this.myActivity.startActivityForResult(itent,100);
        } else {
            Toast.makeText(this.present.getContext(),"You're not here",Toast.LENGTH_SHORT).show();
        }
    }
}
