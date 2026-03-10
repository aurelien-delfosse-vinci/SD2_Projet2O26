package src;

public class Arc {

  private Noeud pointDOrigine;
  private Noeud pointDArrivee;
  private double distanceOrigineArrivee;
  private String nomDeRue;

  public Arc(String nomDeRue, Noeud pointDOrigine, Noeud pointDArrivee, double distanceOrigineArrivee) {
    this.nomDeRue = nomDeRue;
    this.pointDOrigine = pointDOrigine;
    this.pointDArrivee = pointDArrivee;
    this.distanceOrigineArrivee = distanceOrigineArrivee;
  }

  public double getDistanceOrigineArrivee() {
    return distanceOrigineArrivee;
  }

  public String getNomDeRue() {
    return nomDeRue;
  }

  public Noeud getPointDArrivee() {
    return pointDArrivee;
  }

  public Noeud getPointDOrigine() {
    return pointDOrigine;
  }
}
