package src.classes;

public class Noeud {

    private int id;
    private double lat;
    private double lon;
    private String name;
    private double alt;

    public Noeud(int id, double lat, double lon, String name, int alt) {
        this.id = id;
        this.lat = lat;
        this.lon = lon;
        this.name = name;
        this.alt = alt;
    }

    public int getId() {
        return id;
    }

    public double getLatitude() {
        return lat;
    }

    public double getLongitude() {
        return lon;
    }

    public String getNom() {
        return name;
    }

    public double getAltitude() {
        return alt;
    }
}
