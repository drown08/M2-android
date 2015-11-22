package FragmentBar;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.openbar.frappereauolivier.openbar.Activity.ProfilActivity;
import com.openbar.frappereauolivier.openbar.R;

/**
 * Created by Frappereau Olivier on 22/11/2015.
 */
public class TabContactInfos extends Fragment {
    public View mainV;
    public ImageView photoUser;
    public Button takeAPhoto;
    public Button upAPhoto;
    public TextView nameCurrent;
    public TextView surnameCurrent;
    public TextView emailCurrent;
    public Button changeInfos;


    public  TabContactInfos() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saveInstanceState) {
        this.mainV = inflater.inflate(R.layout.tab_user_infos,container,false);
        customTheView();
        return this.mainV;
    }

    private void customTheView() {
        setPhoto();
        setInfoUser();
        setButtons();
    }

    private void setPhoto() {
        this.photoUser = (ImageView) this.mainV.findViewById(R.id.profil_photo_user);
        this.photoUser.setImageResource(ProfilActivity.currentUser.getRefImg());
    }

    private void setInfoUser() {
        this.nameCurrent = (TextView) this.mainV.findViewById(R.id.user_place_name);
        this.nameCurrent.setText(ProfilActivity.currentUser.getNom());

        this.surnameCurrent = (TextView) this.mainV.findViewById(R.id.user_place_surname);
        this.surnameCurrent.setText(ProfilActivity.currentUser.getPrenom());

        this.emailCurrent = (TextView) this.mainV.findViewById(R.id.user_place_mail);
        this.emailCurrent.setText("Todo : take a mail");
    }

    private void setButtons() {
        this.changeInfos = (Button) this.mainV.findViewById(R.id.profil_change_infos_user);
        this.changeInfos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mainV.getContext(),"Create activity form change user",Toast.LENGTH_SHORT).show();
            }
        });

        this.takeAPhoto = (Button) this.mainV.findViewById(R.id.take_photo_user_profil);
        this.takeAPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                getActivity().startActivityForResult(itent,100);
            }
        });

        this.upAPhoto = (Button) this.mainV.findViewById(R.id.profil_upload_photo);
        this.upAPhoto.setClickable(false);
    }
}
