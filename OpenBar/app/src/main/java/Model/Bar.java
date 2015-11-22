package Model;

import java.util.ArrayList;

/**
 * Created by Frappereau Olivier on 11/11/2015.
 */
public class Bar {
    private String nom;
    private String infos;
    private int logo;
    private ArrayList<Contact> listePresents;

    public Bar() {
        this.listePresents = new ArrayList<Contact>();
    }

    public Bar (String n, String i, int l){
        this.nom=n;
        this.infos=i;
        this.logo=l;
        this.listePresents = new ArrayList<Contact>();
    }

    public void setNom(String n){
        this.nom=n;
    }

    public String getNom() {
        return this.nom;
    }

    public void setInfos(String infos) {
        this.infos = infos;
    }

    public String getInfos() {
        return infos;
    }

    public void setLogo(int logo) {
        this.logo = logo;
    }

    public int getLogo() {
        return logo;
    }

    public void setListePresents(ArrayList<Contact> l) {
        this.listePresents = l;
    }

    public ArrayList<Contact> getListePresents() {
        return  this.listePresents;
    }

}
