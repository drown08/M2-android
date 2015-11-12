package Model;

/**
 * Created by Frappereau Olivier on 11/11/2015.
 */
public class Bar {
    private String nom;
    private String infos;
    private int logo;

    public Bar (String n, String i, int l){
        this.nom=n;
        this.infos=i;
        this.logo=l;
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

}
