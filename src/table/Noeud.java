package src.table;

public class Noeud {

    private int id;
    private double latitude;
    private double longitude;
    private String nom;
    private int altitude;

    public Noeud(int id, double latitude, double longitude, String nom, int altitude) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.nom = nom;
        this.altitude = altitude;
    }

    public int getId() {
        return id;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getNom() {
        return nom;
    }

    public int getAltitude() {
        return altitude;
    }
}
