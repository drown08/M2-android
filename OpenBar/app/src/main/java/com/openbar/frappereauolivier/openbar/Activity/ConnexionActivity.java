package com.openbar.frappereauolivier.openbar.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.openbar.frappereauolivier.openbar.R;

import Evenement.OnClickEvent;

public class ConnexionActivity extends Activity {

    //Attributs widgets of the view
    TextView buttonNew;
    TextView buttonForgot;
    TextView header;
    EditText fieldConnexionLogin;
    EditText filedConnexionPass;
    Button buttonLog;


    //Méthodes propres aux activités (implémentées selon les besoins métier de chaque activité : Cf. Story board)
    //Et c'est tout ce qui doit être définit ici ! Voir chaque "action" à réaliser comme un service à appeler.
    //Exception à cette règle : traitement de présentations des éléments affichés (traitement statique)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Full screen activity
        // Pour cacher la barre de titre
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // Pour cacher la barre de statut
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Ajout de la vue
        setContentView(R.layout.activity_connexion);

        //Récupération des widgets
        this.fieldConnexionLogin = (EditText)this.findViewById(R.id.login_connexion);
        this.filedConnexionPass = (EditText)this.findViewById(R.id.password_connexion);
        this.buttonLog = (Button)this.findViewById(R.id.button_connexion);
        this.buttonNew = (TextView)this.findViewById(R.id.new_account);
        this.buttonForgot = (TextView)this.findViewById(R.id.forget_password);
        this.header = (TextView)this.findViewById((R.id.text_connexion));

        //Traitement de présentations sur ces widgets
        this.header.setText(R.string.connexion);

        //Sets action user (events on the widgets)
        this.buttonLog.setOnClickListener(new OnClickEvent(this));
        this.buttonForgot.setOnClickListener(new OnClickEvent(this));
        this.buttonNew.setOnClickListener(new OnClickEvent((this)));

    }

}
