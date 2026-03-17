package src.classes;

import java.util.Objects;

public class Noeud {

    private Long id;
    private double lat;
    private double lon;
    private String name;
    private double alt;

    public Noeud(Long id, double lat, double lon, String name, double alt) {
        this.id = id;
        this.lat = lat;
        this.lon = lon;
        this.name = name;
        this.alt = alt;
    }

    public Long getId() {
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

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Noeud noeud = (Noeud) o;
    return Objects.equals(id, noeud.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }
}
