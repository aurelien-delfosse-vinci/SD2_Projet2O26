package src.classes;

public class Localisation {
  private Noeud noeud;
  private boolean inondee = false;

  public Localisation(Noeud noeud) {
    this.noeud = noeud;
  }

  public boolean isInondee() {
    return inondee;
  }

  public Noeud getNoeud() {
    return noeud;
  }

  public void setInondee(boolean inondee) {
    this.inondee = inondee;
  }

  @Override
  public String toString() {
    return "Localisation{" +
        "id=" + noeud.getId() +
        ", nom=" + noeud.getNom() +
        ", altitude=" + noeud.getAltitude() +
        ", est inondée=" + inondee +
        '}';
  }

  public Long getId() {
    return noeud.getId();
  }

  public double getAltitude() {
    return noeud.getAltitude();
  }

  public double getLatitude() {
    return noeud.getLatitude();
  }

  public double getLongitude() {
    return noeud.getLongitude();
  }

  public String getNom() {
    return noeud.getNom();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Localisation)) return false;
    Localisation that = (Localisation) o;
    return noeud.getId() == that.noeud.getId();
  }

  @Override
  public int hashCode() {
    return Long.hashCode(noeud.getId());
  }
}
