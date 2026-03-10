package src.table;

public class Arc {

  private Noeud origine;
  private Noeud arrivee;
  private double distance;
  private String nomDeRue;

  public Arc(String nomDeRue, Noeud origine, Noeud arrivee, double distance) {
    this.nomDeRue = nomDeRue;
    this.origine = origine;
    this.arrivee = arrivee;
    this.distance = distance;
  }

  public double getDistance() {
    return distance;
  }

  public String getNomDeRue() {
    return nomDeRue;
  }

  public Noeud getArrivee() {
    return arrivee;
  }

  public Noeud getOrigine() {
    return origine;
  }
}
