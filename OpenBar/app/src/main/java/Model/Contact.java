package Model;

/**
 * Created by Frappereau Olivier on 16/11/2015.
 */
public class Contact {

    private int refImg;
    private boolean isFriend;
    private String nom;
    private String prenom;
    private Bar place;

    public Contact(int logo, String nom, String prenom) {
        this.refImg = logo;
        this.nom = nom;
        this.prenom = prenom;
        this.isFriend = false;
    }

    public Contact (int logo, String nom, String prenom, Boolean is) {
        this.refImg = logo;
        this.nom = nom;
        this.prenom = prenom;
        this.isFriend = is;
    }

    public int getRefImg() {
        return refImg;
    }

    public void setRefImg(int refImg) {
        this.refImg = refImg;
    }

    public boolean isMyFriend() {
        return this.isFriend;
    }

    public void setNom(String nom){
        this.nom = nom;
    }

    public void setPrenom(String prenom){
        this.prenom = prenom;
    }

    public String getNom() {
        return this.nom;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public void setIsFriend(boolean is) {
        this.isFriend = is;
    }
}
