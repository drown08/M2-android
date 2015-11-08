package com.openbar.frappereauolivier.openbar.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.openbar.frappereauolivier.openbar.R;

import Evenement.OnClickEventInscriptionConnexionForgot;

public class InscriptionActivity extends Activity {

    //Attributs widgets of the view
    TextView boutonConnexion;
    TextView header;
    RadioButton male;
    RadioButton female;
    EditText login;
    EditText password;
    EditText confirmPassword;
    EditText email;
    Button boutonInscription;

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

        //Ajout de la vue - initialisation des widgets
        setContentView(R.layout.activity_inscription);

        //Récupération des widgets
        boutonConnexion = (TextView)this.findViewById(R.id.already_sign);
        header = (TextView)this.findViewById((R.id.text_connexion));
        boutonInscription = (Button)this.findViewById(R.id.button_inscription);

        //Traitement de présentations sur ces widgets
        setSpecifyPresentation();

        //Sets action user (events on the widgets)
        // méthode onclick du textView -> création objetEvent avec les bons params
        //Si appelle une Transaction vers une autre activité -> création objetTransac avec les bons params
        //Si appelle une Communication avec le serveur -> création objetComm avec les bons params
        boutonConnexion.setOnClickListener(new OnClickEventInscriptionConnexionForgot(this));
        boutonInscription.setOnClickListener(new OnClickEventInscriptionConnexionForgot(this));


    }

    private void setSpecifyPresentation() {
        header.setText(R.string.inscription);
    }

}
