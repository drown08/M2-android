package FragmentBar;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.openbar.frappereauolivier.openbar.Activity.BarActivity;
import com.openbar.frappereauolivier.openbar.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Adapter.GalleryAdapter;
import CommunicationServeur.AsyncTaskResponse;
import CommunicationServeur.CommunicationService;
import Evenement.OnClickAtAddActivityBar;
import Evenement.OnClickAtTakePhotoBar;
import Evenement.OnClickDisplayFocuses;
import Evenement.OnClickImHere;
import Evenement.OnClickImSam;
import Evenement.OnTapeChangeBar;
import ItemViewHolder.PhotoBarViewHolder;

/**
 * Created by Frappereau Olivier on 15/11/2015.
 */
public class TabBarInfos extends Fragment implements GalleryAdapter.OnItemClickListener, AsyncTaskResponse {
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
        //this.addActivity.setClickable(this.imHere.isChecked());
        this.addActivity.setOnClickListener(new OnClickAtAddActivityBar(this.imHere, this.infos));
        this.takePicture = (Button) this.mainV.findViewById(R.id.take_picture);
        //this.takePicture.setClickable(this.imHere.isChecked());
        this.takePicture.setOnClickListener(new OnClickAtTakePhotoBar(this.imHere, this.getActivity()));
    }

    private void setCheckBoxes() {
        this.imHere = (CheckBox) this.mainV.findViewById(R.id.check_here);
        this.imSam = (CheckBox) this.mainV.findViewById(R.id.check_sam);

        this.imHere.setOnClickListener(new OnClickImHere(this));
        this.imSam.setOnClickListener(new OnClickImSam(this,imHere,imSam));


        //Récuppérer user-to-bar (currentUserid, BarId/Name) => is-prsent, is-sam
        Toast.makeText(this.getContext(),"DEBUG : 1", Toast.LENGTH_SHORT).show();
        CommunicationService getHereAndSam = new CommunicationService(this,this.getActivity(),true,1);
        getHereAndSam.addParams("ctrl","getHereAndSam");
        getHereAndSam.addParams("user","12"); //TODO : MEttre le current user
        getHereAndSam.addParams("bar", BarActivity.myBar.getNom());
        getHereAndSam.sendToServer();
        getHereAndSam.flush();
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
        this.photosField.setOnClickListener(new OnClickDisplayFocuses(this.mainV.getContext(), myRecyclerView, null, this.photosField.getId()));
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

    @Override
    public void processFinish(String output, int flag) {
        Toast.makeText(this.getContext(),"DEBUG : 2", Toast.LENGTH_SHORT).show();
        switch (flag){
            case 1 : //Réccupération de is_here et is_sam, pour les set.
                Toast.makeText(this.getContext(),"DEBUG : 3", Toast.LENGTH_SHORT).show();
                try {
                    Toast.makeText(this.getContext(),"DEBUG : 4", Toast.LENGTH_SHORT).show();
                    JSONArray result = new JSONArray(output);
                    Toast.makeText(this.getContext(),"DEBUG : 5", Toast.LENGTH_SHORT).show();
                    Boolean is_here = false;
                    Boolean is_sam = false;
                    Log.d("DEBUG-output",output);
                    for(int i = 0; i < result.length(); i++) {
                        Toast.makeText(this.getContext(),"DEBUG : 6 - "+String.valueOf(i), Toast.LENGTH_SHORT).show();
                        JSONObject row = result.getJSONObject(i);
                        String tmpHere = row.getString("is_present");
                        String tmpSam = row.getString("is_sam");
                        is_here = tmpHere.equals("1");
                        is_sam = tmpSam.equals("1");
                    }
                    this.imHere.setChecked(is_here);
                    Toast.makeText(this.getContext(),"DEBUG : 7 - "+String.valueOf(this.imHere.isChecked()), Toast.LENGTH_SHORT).show();
                    this.imSam.setChecked(is_sam);
                    Toast.makeText(this.getContext(),"DEBUG : 8 - "+String.valueOf(this.imSam.isChecked()), Toast.LENGTH_SHORT).show();
                    Toast.makeText(getActivity(),"Reccup info is_* +setting ",Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case  2 : //Réccupération si déjà présent dans un autre bar
                try {
                    JSONArray result = new JSONArray(output);
                    Boolean is_present_other_bar = false;
                    String nomBar = "notset";
                    for(int i = 0; i < result.length(); i++) {
                        JSONObject row = result.getJSONObject(i);
                        String tmpIsP = row.getString("is_present");
                        is_present_other_bar = tmpIsP.equals("1");
                        nomBar = row.getString("nom_bar");
                    }
                    if(is_present_other_bar) {
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                        alertDialogBuilder.setMessage("Vous avez indiquer etre au bar " + nomBar + ", voulez-vous vraiment changer de bar ?");
                        alertDialogBuilder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                // sign in the user ...
                                dialog.cancel();
                            }
                        });
                        alertDialogBuilder.setPositiveButton("Valider",new OnTapeChangeBar(this,nomBar));
                        AlertDialog alertDialog = alertDialogBuilder.create();
                        alertDialog.show();
                    } else {
                        CommunicationService setImHere = new CommunicationService(this,this.getActivity(),true,4);
                        setImHere.addParams("ctrl", "setImHere");
                        setImHere.addParams("user", "12"); //TODO : MEttre le current user
                        setImHere.addParams("bar",BarActivity.myBar.getNom());
                        setImHere.addParams("value", String.valueOf(this.imHere.isChecked()));
                        setImHere.sendToServer();
                        setImHere.flush();
                       /* if (this.imHere.isChecked()) {
                            this.imSam.setVisibility(View.VISIBLE);
                            //this.addActivity.setClickable(true);
                            //this.takePicture.setClickable(true);
                        } else {
                            this.imSam.setVisibility(View.GONE);
                            //this.addActivity.setClickable(false);
                            //this.takePicture.setClickable(false);
                            if (this.imSam.isChecked()) {//Useless ?
                                this.imSam.setChecked(false);
                            }
                        } */
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case 3 : //Update sam ok
                Toast.makeText(this.getContext(),"Vous etes considéré comme Sam a cette soirée !",Toast.LENGTH_SHORT).show();
                Toast.makeText(this.getContext(),"L'abus d'alcool est dangereux pour la santé",Toast.LENGTH_SHORT).show();
                break;
            case 4 : //Update here ok
               // this.addActivity.setClickable(true);
               // this.takePicture.setClickable(true);
                //this.imSam.setVisibility(View.VISIBLE);
                Toast.makeText(this.getContext(),"Vous etes localisé dans ce bar",Toast.LENGTH_SHORT).show();
                break;
            case 5 : //Change bar ok
                Toast.makeText(this.getContext(),"Vous avez bien changer de bar",Toast.LENGTH_SHORT).show();
                break;

        }
    }
}
