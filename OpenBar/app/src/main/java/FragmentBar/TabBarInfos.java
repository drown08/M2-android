package FragmentBar;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.openbar.frappereauolivier.openbar.Activity.BarActivity;
import com.openbar.frappereauolivier.openbar.R;
import com.squareup.picasso.Picasso;

import Adapter.GalleryAdapter;
import Evenement.OnAddNewActivity;
import Evenement.OnClickDisplayFocuses;
import ItemViewHolder.PhotoBarViewHolder;

/**
 * Created by Frappereau Olivier on 15/11/2015.
 */
public class TabBarInfos extends Fragment implements GalleryAdapter.OnItemClickListener {
    TextView infos;
    TextView photosField;
    TextView actField;
    View mainV;
    ImageView displayImg;
    LinearLayout myDisplayingPicture;
    CheckBox imHere;
    CheckBox imSam;
    Button addActivity;
    Button takePicture;
    CardView picture;

    private RecyclerView myRecyclerView;
    private GalleryAdapter myGalleryAdapter;
    private LinearLayout myGallery;
    private LinearLayoutManager linearLayoutManager;
    private GridLayoutManager gridLayoutManager;
    private RecyclerView myMainRecyclerView;

    public  TabBarInfos() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.mainV = inflater.inflate(R.layout.tab_bar_infos,container,false);
        customTheView();
        return this.mainV;

    }

    private void customTheView() {

        setInfosFocus();

        setPhotosBar();

        setCheckBoxes();

        setTitles();

        setButtons();

    }

    private void setButtons() {
        this.addActivity = (Button) this.mainV.findViewById(R.id.add_bar_activity);
        this.addActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog d = new Dialog(getContext());
                d.setContentView(R.layout.button_add_activity);
                d.setTitle("Add activity to the bar");
                d.setCancelable(true);
                EditText edit = (EditText) d.findViewById(R.id.form_add_activity);
                EditText editName = (EditText) d.findViewById(R.id.form_add_title_activity);
                Button b = (Button) d.findViewById(R.id.add_activity);
                b.setOnClickListener(new OnAddNewActivity(d,edit,editName,infos));
                d.show();
            }
        });
        this.takePicture = (Button) this.mainV.findViewById(R.id.take_picture);
        this.takePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                getActivity().startActivityForResult(itent,100);
            }
        });
    }

    private void setCheckBoxes() {
        this.imHere = (CheckBox) this.mainV.findViewById(R.id.check_here);
        this.imSam = (CheckBox) this.mainV.findViewById(R.id.check_sam);

        this.imHere.setOnClickListener(new View.OnClickListener() {

            //TODO : Voir la gestion de sam en bdd
            @Override
            public void onClick(View v) {
                if (imHere.isChecked()) {
                    imSam.setVisibility(View.VISIBLE);
                } else {
                    imSam.setVisibility(View.GONE);
                    if (imSam.isChecked()) {
                        imSam.setChecked(false);
                    }
                }

            }
        });
    }

    private void setInfosFocus () {
        this.infos = (TextView) this.mainV.findViewById(R.id.focus_infos);
        this.infos.setText(BarActivity.myBar.getNom());
        this.infos.setMovementMethod(new ScrollingMovementMethod());
    }

    private void setTitles() {
        this.photosField = (TextView) this.mainV.findViewById(R.id.daily_picture);
        this.actField = (TextView) this.mainV.findViewById(R.id.daily_activity);
        this.picture = (CardView) this.mainV.findViewById(R.id.card_info_bar);
        this.photosField.setOnClickListener(new OnClickDisplayFocuses(this.mainV.getContext(),myRecyclerView,null,this.photosField.getId()));
        this.actField.setOnClickListener(new OnClickDisplayFocuses(this.mainV.getContext(), null, this.infos, this.actField.getId()));
    }

    private void setPhotosBar() {
        //this.myGallery = (LinearLayout) this.mainV.findViewById(R.id.my_gallery_bar);
        myRecyclerView = (RecyclerView)this.mainV.findViewById(R.id.myrecyclerview);
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false);
        myGalleryAdapter = new GalleryAdapter(this);
        myGalleryAdapter.setOnItemClickListener(this);
        myRecyclerView.setAdapter(myGalleryAdapter);
        myRecyclerView.setLayoutManager(linearLayoutManager);

        displayImg = (ImageView) this.mainV.findViewById(R.id.hide_picture);
        displayImg.setVisibility(View.GONE);
        displayImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayImg.setVisibility(View.GONE);
            }
        });


        prepareGallery();
    }

    private void prepareGallery() {
        //String externakStorageDirectoryPath = Environment.getExternalStorageDirectory().getAbsolutePath();
       // String targetPath = externakStorageDirectoryPath + "/test/";
        //String targetPath =
        //Log.d("TestLink", targetPath);
        //Toast.makeText(getContext(),targetPath,Toast.LENGTH_SHORT).show();
       // File targetDirector = new File(targetPath);
       // File[] files = targetDirector.listFiles();
       // for(File file : files) {
       //     Uri uri = Uri.fromFile(file);
       //     myGalleryAdapter.add(myGalleryAdapter.getItemCount(),uri);
       // }
         //Uri uri = Uri.parse("https://pixabay.com/static/uploads/photo/2015/02/25/22/10/chicken-649572_640.jpg");
        //TODO : reccuperer les images du jour du bar à la bien
         /*Uri uri = Uri.parse("http://i.imgur.com/Pukv3zV.gif");
        myGalleryAdapter.add(myGalleryAdapter.getItemCount(),uri);
        uri = Uri.parse("http://i.imgur.com/RKchYn7.jpg");
        myGalleryAdapter.add(myGalleryAdapter.getItemCount(),uri);
        uri = Uri.parse("http://i.imgur.com/nuMMnwY.png");
        myGalleryAdapter.add(myGalleryAdapter.getItemCount(),uri);*/
        Uri uri = Uri.parse("android.resource://com.openbar.frappereauolivier.openbar/"+R.drawable.ic_tick);
        myGalleryAdapter.add(myGalleryAdapter.getItemCount(),uri);
        uri = Uri.parse("android.resource://com.openbar.frappereauolivier.openbar/"+R.drawable.ic_plus_circle);
        myGalleryAdapter.add(myGalleryAdapter.getItemCount(),uri);
        uri = Uri.parse("android.resource://com.openbar.frappereauolivier.openbar/"+R.drawable.ic_favorite_black_48dp);
        myGalleryAdapter.add(myGalleryAdapter.getItemCount(),uri);
        uri = Uri.parse("android.resource://com.openbar.frappereauolivier.openbar/"+ R.drawable.ic_add);
        myGalleryAdapter.add(myGalleryAdapter.getItemCount(),uri);



    }

    @Override
    public void onItemClick(PhotoBarViewHolder item, int position) {
        String stringitemUri = item.getItemUri();
        Uri uri = Uri.parse("android.resource://com.openbar.frappereauolivier.openbar/" + stringitemUri);
        Toast.makeText(getContext(),stringitemUri,Toast.LENGTH_SHORT).show();

        if(uri != null) {
            Context context = displayImg.getContext();
            Picasso.with(context)
                    .load(uri)
                    .into(displayImg);

        }


        displayImg.setVisibility(View.VISIBLE);


    }
}
