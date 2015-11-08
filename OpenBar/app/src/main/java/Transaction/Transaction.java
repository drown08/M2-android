package Transaction;

import android.app.Activity;
import android.content.Intent;

import java.util.List;

/**
 * Created by Frappereau Olivier on 05/11/2015.
 * Offer of this class : Helper for realize an android intent
 */
public class Transaction {
    private Intent myTransaction;
    private Activity myActivity;
    private Class<?> cls;

    //Appeler cette méthode lorsqu'on souhaite changer d'activité : Création d'une nouvelle transaction entre activités
    public Transaction(Activity a, Class<?> c) {
        this.myTransaction = new Intent();
        this.myActivity = a;
        this.cls = c;
        setClassIntent();
    }

    //Constructeur(s) pour des transactions spécifiques (à compléter selon le besoin méier)
    public Transaction(String action) {
        this.myTransaction = new Intent(action);
    }

    public Intent getMyTransaction() {
        return this.myTransaction;
    }

    public void setMyTransaction(Intent t) {
        this.myTransaction = t;
    }

    //Ajouter n'importe quel extra à la transaction
    public void addExtras(String name, List<? extends Object> list) {
        for(Object o : list)
        {
            this.myTransaction.putExtra(name, String.valueOf(o)); //Attention, l'activité reçevra que des strings.
            //Caster au traitement si besoin
        }

    }

    //Lancer l'activité et détruire la précédente
    public void exitAndRun() {
        this.myActivity.finish();
        this.myActivity.startActivity(this.myTransaction);
    }

    //Lancer l'activité sans détruire la précédente
    public void runWithoutExit() {
        this.myActivity.startActivity(this.myTransaction);
    }

    private void setClassIntent() {
        this.myTransaction.setClass(this.myActivity,this.cls);
    }
}
