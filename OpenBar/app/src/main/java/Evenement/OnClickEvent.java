package Evenement;

import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.openbar.frappereauolivier.openbar.Activity.ConnexionActivity;
import com.openbar.frappereauolivier.openbar.Activity.InscriptionActivity;
import com.openbar.frappereauolivier.openbar.R;

import CommunicationServeur.CommunicationService;
import Transaction.Transaction;
import Validation.ConnexionValidation;
import Validation.InscriptionValidation;

/**
 * Created by Frappereau Olivier on 05/11/2015.
 * Offer the dispatcher event of click on this application, what buisness can do for each case
 */
public class OnClickEvent implements View.OnClickListener {
    private Activity myActivity;

    public OnClickEvent (Activity a) {
        this.myActivity = a;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_inscription : // LORSQUE CLIQUE SUR LE BOUTON INSCRIPTION
                //Appel Validation (Une classe de Validation par Formulaire, ca sera more easy)
                InscriptionValidation myValidationInscr = new InscriptionValidation(myActivity);
                myValidationInscr.addFieldEditText((EditText) myActivity.findViewById(R.id.login_inscription));
                myValidationInscr.addFieldEditText((EditText) myActivity.findViewById(R.id.password_inscription));
                myValidationInscr.addFieldEditText((EditText) myActivity.findViewById(R.id.email_inscription));
                myValidationInscr.addFieldRadioGroupButton((RadioGroup) myActivity.findViewById(R.id.group_sexe_inscription));
                boolean inscrOk = myValidationInscr.validate();
                if (!inscrOk) {
                    Toast.makeText(myActivity.getApplication(),"Remplir les champs correctement",Toast.LENGTH_LONG).show();
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
                break;
            case R.id.button_connexion : // LORSQUE CLIQUE SUR BOUTON CONNEXION
                //TODO : Fix l'accès aux widgets
                EditText logCon = (EditText) myActivity.findViewById(R.id.login_connexion);
                EditText passCon = (EditText) myActivity.findViewById(R.id.password_connexion);
                ConnexionValidation myValidation = new ConnexionValidation();
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
        }
    }
}
