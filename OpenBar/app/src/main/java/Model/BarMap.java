package Model;

/**
 * Created by Frappereau Olivier on 29/12/2015.
 */
public class BarMap {
    private String nom;
    private double x;
    private double y;

    public BarMap() {
        this.nom="exemple";
        this.x = -88;
        this.y = 888;
    }

    public BarMap(String nom, int coordX, int coordY) {
        this.nom = nom;
        this.x = coordX;
        this.y = coordY;
    }
    public BarMap(String nom, double coordX, double coordY) {
        this.nom = nom;
        this.x = coordX;
        this.y = coordY;
    }

    public String getNom() {
        return this.nom;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return  this.y;
    }

    public void setNom(String nom){
        this.nom = nom;
    }

    public void setX(int coordX){
        this.x = coordX;
    }
    public void setX(double coordX){
        this.x = coordX;
    }

    public void setY(int coordY){
        this.y = coordY;
    }
    public void setY(double coordY){
        this.y = coordY;
    }

}
