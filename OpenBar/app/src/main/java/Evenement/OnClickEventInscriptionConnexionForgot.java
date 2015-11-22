package Evenement;

import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.openbar.frappereauolivier.openbar.Activity.ConnexionActivity;
import com.openbar.frappereauolivier.openbar.Activity.FocusActivity;
import com.openbar.frappereauolivier.openbar.Activity.ForgetMailActivity;
import com.openbar.frappereauolivier.openbar.Activity.InscriptionActivity;
import com.openbar.frappereauolivier.openbar.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;

import CommunicationServeur.AsyncTaskResponse;
import CommunicationServeur.CommunicationService;
import Transaction.Transaction;
import Validation.ConnexionValidation;
import Validation.ForgotPasswordValidation;
import Validation.InscriptionValidation;

/**
 * Created by Frappereau Olivier on 05/11/2015.
 * Offer the dispatcher event of click on this application, what buisness can do for each case (only first "workflow")
 */
public class OnClickEventInscriptionConnexionForgot implements View.OnClickListener, AsyncTaskResponse {
    private Activity myActivity;

    public EditText loginInscr;
    public EditText passInscr;
    public EditText passConfInscr;
    public EditText mailInscr;
    public RadioGroup groupSexInscr;
    public EditText loginConn;
    public EditText passConn;
    public EditText loginSendMail;
    public EditText mailSendMail;

    public String sLoginInscr;
    public String sPassInscr;
    public String sMailInscr;
    public String sLoginConn;
    public String sPassConn;
    public String sLoginSendMail;
    public String sMailSendMail;


    public OnClickEventInscriptionConnexionForgot(Activity a) {
        this.myActivity = a;
        initFocusesElements();
    //getObjectOfElements();
    }

    private void initFocusesElements() {
        this.loginInscr = (EditText) this.myActivity.findViewById(R.id.login_inscription);
        this.passInscr = (EditText) this.myActivity.findViewById(R.id.password_inscription);
        this.passConfInscr = (EditText) this.myActivity.findViewById(R.id.password_confirm_inscription);
        this.mailInscr = (EditText) this.myActivity.findViewById(R.id.email_inscription);
        this.groupSexInscr = (RadioGroup) this.myActivity.findViewById(R.id.group_sexe_inscription);
        this.loginConn = (EditText) this.myActivity.findViewById(R.id.login_connexion);
        this.passConn = (EditText) this.myActivity.findViewById(R.id.password_connexion);
        this.loginSendMail = (EditText) this.myActivity.findViewById(R.id.login_send_mail);
        this.mailSendMail = (EditText) this.myActivity.findViewById(R.id.email_send_mail);
    }

    private void getObjectOfElements() {
        if(this.loginInscr != null)
            this.sLoginInscr = this.loginInscr.getText().toString();
        if(this.passInscr != null)
            this.sPassInscr = this.passInscr.getText().toString();
        if(this.mailInscr != null)
            this.sMailInscr = this.mailInscr.getText().toString();
        if(this.loginConn != null)
            this.sLoginConn = this.loginConn.getText().toString();
        if(this.passConn != null)
            this.sPassConn = this.passConn.getText().toString();
        if(this.loginSendMail != null)
            this.sLoginSendMail = this.loginSendMail.getText().toString();
        if(this.mailSendMail != null)
            this.sMailSendMail = this.mailSendMail.getText().toString();
    }


    @Override
    public void onClick(View v) {
        getObjectOfElements();
        boolean inscrOk = false;
        switch (v.getId()) {
            case R.id.button_inscription : // LORSQUE CLIQUE SUR LE BOUTON INSCRIPTION
                InscriptionValidation myValidationInscr = new InscriptionValidation(this.myActivity);
                myValidationInscr.addFieldEditText(this.loginInscr);
                myValidationInscr.addFieldEditText(this.passInscr);
                myValidationInscr.addFieldEditText(this.passConfInscr);
                myValidationInscr.addFieldEditText(this.mailInscr);
                myValidationInscr.addFieldRadioGroupButton(this.groupSexInscr);
                inscrOk = myValidationInscr.validate();
                if (!inscrOk) {
                    Toast.makeText(myActivity.getApplicationContext(),R.string.fill_correctly_field,Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(myActivity.getApplicationContext(), R.string.verify_pseudo, Toast.LENGTH_LONG).show();
                    CommunicationService goodPseudo = new CommunicationService(this, this.myActivity,true,2);
                    goodPseudo.addParams("ctrl","vPseudo");
                    goodPseudo.addParams("pseudo",this.sLoginInscr);
                    goodPseudo.sendToServer();
                    goodPseudo.flush();
                }
                break;
            case R.id.already_sign : // LORSQUE CLIQUE SUR DEJA INSCRIT
                Toast.makeText(myActivity.getApplicationContext(),R.string.already_sign,Toast.LENGTH_LONG).show();
                Transaction goConnexion = new Transaction(myActivity, ConnexionActivity.class);
                goConnexion.exitAndRun();
                break;
            case R.id.new_account : // LORSQUE CLIQUE SUR JE SUIS NOUVEAU
                Toast.makeText(myActivity.getApplicationContext(),R.string.new_account,Toast.LENGTH_LONG).show();
                Transaction goInscription = new Transaction(myActivity, InscriptionActivity.class);
                goInscription.runWithoutExit();
                break;
            case R.id.forget_password : // LORSQUE CLIQUE SUR PASSWORD OUBLIE
                Toast.makeText(myActivity.getApplicationContext(),R.string.forget_password, Toast.LENGTH_LONG).show();
                Transaction goForgetMail = new Transaction(myActivity, ForgetMailActivity.class);
                goForgetMail.addExtras("pseudo", new ArrayList<String>(Arrays.asList(this.sLoginConn)));
                goForgetMail.runWithoutExit();
                break;
            case R.id.button_connexion : // LORSQUE CLIQUE SUR BOUTON CONNEXION
                ConnexionValidation myValidation = new ConnexionValidation(myActivity);
                myValidation.addFieldEditText(this.loginConn);
                myValidation.addFieldEditText(this.passConn);
                boolean isOk = myValidation.validate();
                if (isOk) {
                    Toast.makeText(myActivity.getApplicationContext(),R.string.checking, Toast.LENGTH_SHORT).show();
                    CommunicationService commTmpVerif = new CommunicationService(this, this.myActivity,true,4);
                    commTmpVerif.addParams("ctrl","verifySign");
                    commTmpVerif.addParams("pseudo",this.sLoginConn);
                    commTmpVerif.addParams("mdp", computeMD5Hash(this.sPassConn));
                    commTmpVerif.sendToServer();
                    commTmpVerif.flush();

                } else {
                    Toast.makeText(myActivity.getApplicationContext(),R.string.fill_correctly_field, Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.button_send_mail : //Lorsque clique sur bouton send (forgot mdp)
                //TODO : Avant d'envoyer le mail, il faut vérifier que le pseudo correspond bien à l'e-mail de l'utilisateur.
                //Il faut donc request le serveur et lui demandant de verifier, en lui envoyant pseudo et mail
                ForgotPasswordValidation myValidationForgot = new ForgotPasswordValidation(myActivity);
                myValidationForgot.addFieldEditText(this.loginSendMail);
                myValidationForgot.addFieldEditText(this.mailSendMail);
                boolean goSend = myValidationForgot.validate();
                if(goSend) {
                    Toast.makeText(myActivity.getApplicationContext(), R.string.checking, Toast.LENGTH_SHORT).show();
                    CommunicationService commTmp = new CommunicationService(this,this.myActivity,true,1); //On souhaite retrouver la réponse et la catch
                    commTmp.addParams("ctrl", "mail");
                    commTmp.addParams("pseudo", this.sLoginSendMail);
                    commTmp.addParams("mail", this.sMailSendMail);
                    commTmp.sendToServer();
                    commTmp.flush();
                } else {
                    Toast.makeText(myActivity.getApplicationContext(),R.string.fill_correctly_field, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.back_connexion : //LORSQUE CLIQUE SUR LE BOUTON RETOUR CONNEXION (DE FORGETPASSWORD)
                Transaction retour = new Transaction(myActivity,ConnexionActivity.class);
                retour.exitAndRun();
                break;
        }
    }

    @Override
    public void processFinish(String output, int flag) {
        getObjectOfElements();
        switch (flag){
            case 1 : // Gérer le cas réccupération de mdp
                String tt = "";
                try {
                    JSONArray result = new JSONArray(output);
                    for (int i = 0; i < result.length(); i++){
                        JSONObject row = result.getJSONObject(i);
                        tt += " "+row.getString("pseudo_user");
                        tt += " "+row.getString("mail_user");
                        tt += " "+row.getString("photo_user");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Toast.makeText(myActivity.getApplicationContext(),tt,Toast.LENGTH_LONG).show();
                break;
            case 2 : //gérer le cas pseudo unique
                    if(output.equals("ok")) { //Si le pseudo est libre alors on record en bdd, avec un tour, pour success ou echec
                        this.sPassInscr = computeMD5Hash(this.passInscr.getText().toString());//For update the variable after many tries
                        CommunicationService goRecordUser = new CommunicationService(this,this.myActivity,true,3);
                        //TODO : Il faut rajouter le sexe de l'user en BDD et en traitement !!!
                        goRecordUser.addParams("ctrl","sign");
                        goRecordUser.addParams("pseudo",this.sLoginInscr);
                        goRecordUser.addParams("mdp",this.sPassInscr);
                        goRecordUser.addParams("mail",this.sMailInscr);
                        goRecordUser.sendToServer();
                        goRecordUser.flush();

                    } else { //Si le pseudo est pas libre
                       // this.loginInscr.setError(R.string.pseudo_already_use);
                        this.loginInscr.setError("Pseudo already used. Try an another");//Fix it
                        this.loginInscr.setText("");
                    }
                break;
            case 3 : // Ici le retour de l'insertion de l'inscription en bdd
                if(!output.equals("not")) {
                    Toast.makeText(myActivity.getApplicationContext(),R.string.sign_done,Toast.LENGTH_SHORT).show();
                    String user = "";
                    try {
                        JSONArray result = new JSONArray(output);
                        for (int i = 0; i < result.length(); i++){
                            JSONObject row = result.getJSONObject(i);
                            user = row.getString("id_user");

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    //Todo : Transaction vers page d'accueil avec un extra boolean pour indiquer si première utilisation ou non
                    Transaction goFocus = new Transaction(myActivity, FocusActivity.class);
                    goFocus.addExtras("user",new ArrayList<String>(Arrays.asList(user)));
                    goFocus.runWithoutExit();
                }else {
                    Toast.makeText(myActivity.getApplicationContext(),R.string.sign_undo,Toast.LENGTH_SHORT).show();
                }
                break;
            case 4 :
                if(output.equals("not")) {
                    //this.passConn.setError(R.string.connect_undo);
                    this.passConn.setError("An error with pass or login. Try again");
                    this.passConn.setText("");
                } else {
                    String user = "";
                    try {
                        JSONArray result = new JSONArray(output);
                        for (int i = 0; i < result.length(); i++){
                            JSONObject row = result.getJSONObject(i);
                            user = row.getString("id_user");
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Transaction goFocus = new Transaction(myActivity, FocusActivity.class);
                    goFocus.addExtras("user",new ArrayList<String>(Arrays.asList(user)));
                    goFocus.runWithoutExit();
                }
        }
    }

    //TODO : Une fois l'appli casi terminée, regrouper les services "à la volée, à la demande" dans un singleton
    private String computeMD5Hash(String password)
    {
        String passMD5 = "";
        StringBuffer MD5Hash = null;
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(password.getBytes());
            byte messageDigest[] = digest.digest();

            MD5Hash = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++)
            {
                String h = Integer.toHexString(0xFF & messageDigest[i]);
                while (h.length() < 2)
                    h = "0" + h;
                MD5Hash.append(h);
            }
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
    passMD5 =  MD5Hash.toString();
    return passMD5;
    }
}
