package com.openbar.frappereauolivier.openbar.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.openbar.frappereauolivier.openbar.R;

import Evenement.OnClickEventInscriptionConnexionForgot;

public class ForgetMailActivity extends Activity {

    EditText pseudo;
    EditText mail;
    TextView retourConnexion;
    Button send;
    TextView header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_forget_mail);

        this.pseudo = (EditText) this.findViewById(R.id.login_send_mail);
        //this.mail = (EditText) this.findViewById(R.id.email_send_mail);
        this.send = (Button) this.findViewById(R.id.button_send_mail);
        this.header = (TextView)this.findViewById((R.id.text_connexion));
        this.retourConnexion = (TextView)this.findViewById(R.id.back_connexion);

        setSpecifyPresentation();

        this.send.setOnClickListener(new OnClickEventInscriptionConnexionForgot(this));
        this.retourConnexion.setOnClickListener(new OnClickEventInscriptionConnexionForgot(this));
    }

    private void setSpecifyPresentation() {

        this.header.setText(R.string.forget_password);

        if(this.getIntent().getStringExtra("pseudo")!=null) {
            this.pseudo.setText(this.getIntent().getStringExtra("pseudo"));
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        // put your code here...

    }

}
