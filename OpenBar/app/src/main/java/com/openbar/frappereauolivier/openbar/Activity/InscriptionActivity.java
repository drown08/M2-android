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

public class InscriptionActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Full screen activity
        // Pour cacher la barre de titre
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // Pour cacher la barre de statut
       getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Ajout de la vue
        setContentView(R.layout.activity_inscription);

        //Body elements init
        TextView boutonConnexion = (TextView)this.findViewById(R.id.already_sign);
        TextView header = (TextView)this.findViewById((R.id.text_connexion));

        //Set the header context
        header.setText(R.string.inscription);

        //Sets action user


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
        Toast.makeText(getApplicationContext(), "Connexion", Toast.LENGTH_LONG).show();
    }

    public void test(View v) {
        Toast.makeText(getApplicationContext(),"Deja inscrit",Toast.LENGTH_LONG).show();
        //Appeler L'action appropriée
        Intent i = new Intent();
        i.setClass(this,ConnexionActivity.class);
        startActivity(i);
    }
}
