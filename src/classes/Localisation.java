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
}
