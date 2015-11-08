package Evenement;

import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.openbar.frappereauolivier.openbar.Activity.ConnexionActivity;
import com.openbar.frappereauolivier.openbar.Activity.ForgetMailActivity;
import com.openbar.frappereauolivier.openbar.Activity.InscriptionActivity;
import com.openbar.frappereauolivier.openbar.R;

import java.util.ArrayList;
import java.util.Arrays;

import CommunicationServeur.CommunicationService;
import Transaction.Transaction;
import Validation.ConnexionValidation;
import Validation.ForgotPasswordValidation;
import Validation.InscriptionValidation;

/**
 * Created by Frappereau Olivier on 05/11/2015.
 * Offer the dispatcher event of click on this application, what buisness can do for each case
 */
public class OnClickEventInscriptionConnexionForgot implements View.OnClickListener {
    private Activity myActivity;

    public OnClickEventInscriptionConnexionForgot(Activity a) {
        this.myActivity = a;
    }
    @Override
    public void onClick(View v) {
        boolean inscrOk = false;
        switch (v.getId()) {
            case R.id.button_inscription : // LORSQUE CLIQUE SUR LE BOUTON INSCRIPTION
                //Appel Validation (Une classe de Validation par Formulaire, ca sera more easy)
                InscriptionValidation myValidationInscr = new InscriptionValidation(myActivity);
                myValidationInscr.addFieldEditText((EditText) myActivity.findViewById(R.id.login_inscription));
                myValidationInscr.addFieldEditText((EditText) myActivity.findViewById(R.id.password_inscription));
                myValidationInscr.addFieldEditText((EditText) myActivity.findViewById(R.id.password_confirm_inscription));
                myValidationInscr.addFieldEditText((EditText) myActivity.findViewById(R.id.email_inscription));
                myValidationInscr.addFieldRadioGroupButton((RadioGroup) myActivity.findViewById(R.id.group_sexe_inscription));

                inscrOk = myValidationInscr.validate();
                if (!inscrOk) {
                    Toast.makeText(myActivity.getApplicationContext(),"Remplir les champs correctement ",Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(myActivity.getApplicationContext(), "Inscription validee !", Toast.LENGTH_LONG).show();
                }
                //Appel Communication avec Serveur pour persister l'inscription
                //Persister dans SQlite pour connexion automatique
                //Transaction vers page d'accueil avec un extra boolean pour indiquer si première utilisation ou non
                break;
            case R.id.already_sign : // LORSQUE CLIQUE SUR DEJA INSCRIT
                Toast.makeText(myActivity.getApplicationContext(),"Deja inscrit",Toast.LENGTH_LONG).show();
                //Utilisation de  Transaction service
                Transaction goConnexion = new Transaction(myActivity, ConnexionActivity.class);
                goConnexion.exitAndRun();
                break;
            case R.id.new_account : // LORSQUE CLIQUE SUR JE SUIS NOUVEAU
                Toast.makeText(myActivity.getApplicationContext(),"Form nouveau",Toast.LENGTH_LONG).show();
                Transaction goInscription = new Transaction(myActivity, InscriptionActivity.class);
                goInscription.runWithoutExit();
                break;
            case R.id.forget_password : // LORSQUE CLIQUE SUR PASSWORD OUBLIE
                Toast.makeText(myActivity.getApplicationContext(),"Pass oublié", Toast.LENGTH_LONG).show();
                Transaction goForgetMail = new Transaction(myActivity, ForgetMailActivity.class);
                EditText tmp = (EditText)myActivity.findViewById(R.id.login_connexion);
                goForgetMail.addExtras("pseudo", new ArrayList<String>(Arrays.asList(tmp.getText().toString())));
                goForgetMail.runWithoutExit();
                break;
            case R.id.button_connexion : // LORSQUE CLIQUE SUR BOUTON CONNEXION
                //TODO : Fix l'accès aux widgets
                EditText logCon = (EditText) myActivity.findViewById(R.id.login_connexion);
                EditText passCon = (EditText) myActivity.findViewById(R.id.password_connexion);
                ConnexionValidation myValidation = new ConnexionValidation(myActivity);
                myValidation.addFieldEditText(logCon);
                myValidation.addFieldEditText(passCon);
                boolean isOk = myValidation.validate();
                if (isOk) {
                    Toast.makeText(myActivity.getApplicationContext(),"Connexion...", Toast.LENGTH_SHORT).show();
                    CommunicationService.getInstance().addParams("log", logCon.getText().toString()); //Ajout de ce qu on demande au serveur avec params
                    String jsp = CommunicationService.getInstance().sendToServer(); //On l'envoit au serveur, et on réccupère
                    Toast.makeText(myActivity.getApplication(),jsp, Toast.LENGTH_LONG).show(); //ON affiche
                    CommunicationService.getInstance().flush(); //On vide les params
                } else {
                    Toast.makeText(myActivity.getApplicationContext(),"Remplir correctement", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.button_send_mail : //Lorsque clique sur bouton send (forgot mdp)
                //TODO : Avant d'envoyer le mail, il faut vérifier que le pseudo correspond bien à l'e-mail de l'utilisateur.
                //Il faut donc request le serveur et lui demandant de verifier, en lui envoyant pseudo et mail
                EditText logForgotPass = (EditText) myActivity.findViewById(R.id.login_send_mail);
                EditText mailForgotPass = (EditText) myActivity.findViewById(R.id.email_send_mail);
                ForgotPasswordValidation myValidationForgot = new ForgotPasswordValidation(myActivity);
                myValidationForgot.addFieldEditText(logForgotPass);
                myValidationForgot.addFieldEditText(mailForgotPass);
                boolean goSend = myValidationForgot.validate();
                if(goSend) {
                    Toast.makeText(myActivity.getApplicationContext(), "Ok, verification pseudo -> mail...", Toast.LENGTH_SHORT).show();
                    CommunicationService commTmp = CommunicationService.getInstance();
                    commTmp.addParams("ctrl", "mail");
                    commTmp.addParams("pseudo", logForgotPass.getText().toString());
                    commTmp.addParams("mail", mailForgotPass.getText().toString());
                    String ok = commTmp.sendToServer();
                    commTmp.flush();
                    if (ok == "send") {
                        Toast.makeText(myActivity.getApplicationContext(),"Mail mdp send :-)",Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(myActivity.getApplicationContext(),ok,Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(myActivity.getApplicationContext(),"BADDD REMPLI BIEN !", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.back_connexion : //LORSQUE CLIQUE SUR LE BOUTON RETOUR CONNEXION (DE FORGETPASSWORD)
                Transaction retour = new Transaction(myActivity,ConnexionActivity.class);
                retour.exitAndRun();
                break;
        }
    }
}
