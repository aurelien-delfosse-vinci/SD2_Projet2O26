package src.classes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class Graph {

  //ATTRIBUT ?
  //TODO
  protected Map<Integer, Noeud> correspondanceIdNoeud;
  protected Map<String, Arc> correspondanceNomRue;


  public Graph(String localisations, String roads) throws Exception {
    //TODO

    // --- Initialisation des maps ---
    correspondanceIdNoeud = new HashMap<>();
    correspondanceNomRue = new HashMap<>();

    // --- Lecture des noeuds CSV ---
    try (BufferedReader br = new BufferedReader(new FileReader(localisations))) {
      String line;
      boolean firstLine = true; // pour ignorer l'en-tête
      while ((line = br.readLine()) != null) {
        if (firstLine) { firstLine = false; continue; }
        String[] tokens = line.split(",");
        int id = Integer.parseInt(tokens[0]);
        String name = tokens[1];
        double lat = Double.parseDouble(tokens[2]);
        double lon = Double.parseDouble(tokens[3]);
        int alt = Integer.parseInt(tokens[4]);

        Noeud n = new Noeud(id, lat, lon, name, alt);
        correspondanceIdNoeud.put(id, n);
      }
    }

    // --- Lecture des arcs CSV ---
    try (BufferedReader br = new BufferedReader(new FileReader(roads))) {
      String line;
      boolean firstLine = true; // pour ignorer l'en-tête
      while ((line = br.readLine()) != null) {
        if (firstLine) { firstLine = false; continue; }
        String[] tokens = line.split(",");
        int sourceId = Integer.parseInt(tokens[0]);
        int targetId = Integer.parseInt(tokens[1]);
        double dist = Double.parseDouble(tokens[2]);
        String streetName = tokens[3];

        Noeud source = correspondanceIdNoeud.get(sourceId);
        Noeud target = correspondanceIdNoeud.get(targetId);

        if (source == null || target == null) {
          throw new Exception("Noeud source ou target non trouvé pour l'arc " + streetName);
        }

        Arc a = new Arc(streetName, source, target, dist);

        // clé unique pour éviter les collisions sur streetName
        correspondanceNomRue.put(streetName + "_" + sourceId + "_" + targetId, a);
      }
    }
  }

  public Localisation[] determinerZoneInondee(long[] idsOrigin,double epsilon) {
    //TODO
    Deque<Noeud> queue = new ArrayDeque<>();
    Set<Noeud> visited = new HashSet<>();
    List<Localisation> result = new ArrayList<>();
    Map<Noeud, Localisation> mapLoc = new HashMap<>();

    // Initialisation
    for (long id : idsOrigin) {
      Noeud n = correspondanceIdNoeud.get((int) id);
      if (n != null) {
        queue.add(n);
        visited.add(n);

        Localisation loc = new Localisation(n);
        loc.setInondee(true);
        mapLoc.put(n, loc);
        result.add(loc);
      }
    }

    while (!queue.isEmpty()) {
      Noeud current = queue.poll();

      for (Arc arc : correspondanceNomRue.values()) {
        if (arc.getOrigine().equals(current)) {

          Noeud voisin = arc.getArrivee();

          if (!visited.contains(voisin) &&
              voisin.getAltitude() <= current.getAltitude() + epsilon) {

            visited.add(voisin);
            queue.add(voisin);

            Localisation loc = new Localisation(voisin);
            loc.setInondee(true);
            mapLoc.put(voisin, loc);
            result.add(loc);
          }
        }
      }
    }

    return result.toArray(new Localisation[0]);
  }

  public Deque<Localisation> trouverCheminLePlusCourtPourContournerLaZoneInondee(long idOrigin, long idDestination, Localisation[] floodedZone) {
    //TODO
    return null ;
  }

  public Map<Localisation,Double> determinerChronologieDeLaCrue(long[] idsOrigin, double vWaterInit,double k) {
    //TODO
    return null ;
  }

  public Deque<Localisation> trouverCheminDEvacuationLePlusCourt(long idOrigin, long idEvacuation, double vVehicule, Map<Localisation,Double> tFlood) {
    //TODO
    return null ;
  }


}