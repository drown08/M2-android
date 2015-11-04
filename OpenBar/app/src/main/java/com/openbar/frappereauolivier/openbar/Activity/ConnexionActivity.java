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

public class ConnexionActivity extends Activity {


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

        //Body elements init
        EditText fieldConnexionLogin = (EditText)this.findViewById(R.id.login_connexion);
        EditText fieldConnexionPass = (EditText)this.findViewById(R.id.password_connexion);
        Button buttonLog = (Button)this.findViewById(R.id.button_connexion);
        TextView buttonNew = (TextView)this.findViewById(R.id.new_account);
        TextView buttonForgot = (TextView)this.findViewById(R.id.forget_password);
        TextView header = (TextView)this.findViewById((R.id.text_connexion));

        //Set the header context
        header.setText(R.string.connexion);

        //Set action user
        buttonLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickButtonLog(v);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_connexion, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
       // int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        //if (id == R.id.action_settings) {
        //    return true;
        //}

        //return super.onOptionsItemSelected(item);
        return true;
    }

    private void onClickButtonLog(View v) {
        //TODO : Passer les widgets en attributs de cette classe, pour les isntancier qu'une seule fois et les manipuler de partout
        //TODO : Trouver l'api pour tester si un champ est valide par rapport à une liste de string
        EditText fieldPseudo = (EditText)this.findViewById(R.id.login_connexion);
        EditText fieldPassword = (EditText)this.findViewById(R.id.password_connexion);
        if (fieldPseudo.getText().toString().length() == 0) {
            fieldPseudo.setError("Pseudo is required");
        }
        if (fieldPassword.getText().toString().length() == 0) {
            fieldPassword.setError("Password is required");
        }

        if (fieldPseudo.getText().toString().equals("drown")) {
            if(fieldPassword.getText().toString().equals("drown")) {
                Toast.makeText(getApplicationContext(),"Connexion", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(),"Bad password", Toast.LENGTH_LONG).show();
            }
        }
        else {
            Toast.makeText(getApplicationContext(), "Bad pseudo", Toast.LENGTH_LONG).show();
        }
    }

    public void test(View v) {
        switch (v.getId()) {
            case R.id.forget_password :
                Toast.makeText(getApplicationContext(),"Pass oublié", Toast.LENGTH_LONG).show();
                //Appeler L'action appropriée (passer par serveur ou web service)
                //Mais d'abord passer par une activitée nouvelle pour réccupérer le mail.
                break;
            case R.id.new_account :
                Toast.makeText(getApplicationContext(),"Form nouveau",Toast.LENGTH_LONG).show();
                //Appeler L'action appropriée
                Intent it = new Intent();
                it.setClass(this,InscriptionActivity.class);
                startActivity(it);
                break;
            default:
                Toast.makeText(getApplicationContext(),"Pb receveur",Toast.LENGTH_LONG).show();
                //Toast une exception
                break;
        }
    }
}
