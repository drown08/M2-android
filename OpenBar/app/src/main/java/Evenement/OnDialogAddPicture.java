package Evenement;

import android.content.DialogInterface;
import android.content.Intent;

import com.openbar.frappereauolivier.openbar.Activity.BarActivity;

/**
 * Created by Frappereau Olivier on 02/01/2016.
 */
public class OnDialogAddPicture implements DialogInterface.OnClickListener {
    public BarActivity myActivity;

    public OnDialogAddPicture(BarActivity activity) {
        this.myActivity = activity;
    }
    @Override
    public void onClick(DialogInterface dialog, int which) {
       // Intent itent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Intent itent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        myActivity.startActivityForResult(itent,1);
    }
}
