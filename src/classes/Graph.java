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
import java.util.PriorityQueue;
import java.util.Set;


public class Graph {

  //ATTRIBUT ?
  //TODO
  protected Map<Long, Noeud> correspondanceIdNoeud;
  protected Map<Noeud, List<Arc>> correspondanceNomRue;


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
        Long id = Long.parseLong(tokens[0]);
        String name = tokens[1];
        double lat = Double.parseDouble(tokens[2]);
        double lon = Double.parseDouble(tokens[3]);
        double alt = Double.parseDouble(tokens[4]);

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
        Long sourceId = Long.parseLong(tokens[0]);
        Long targetId = Long.parseLong(tokens[1]);
        double dist = Double.parseDouble(tokens[2]);
        String streetName = tokens[3];

        Noeud source = correspondanceIdNoeud.get(sourceId);
        Noeud target = correspondanceIdNoeud.get(targetId);

        Arc a = new Arc(streetName, source, target, dist);

        // clé unique pour éviter les collisions sur streetName
        correspondanceNomRue.computeIfAbsent(source, k -> new ArrayList<>()).add(a);
      }
    } catch (Exception e) {
      throw new Exception(e.getMessage());
    }
  }

  public Localisation[] determinerZoneInondee(long[] idsOrigin,double epsilon) {
    //TODO
    Deque<Noeud> queue = new ArrayDeque<>();
    Set<Noeud> visited = new HashSet<>();
    List<Localisation> result = new ArrayList<>();

    // Initialisation
    for (long id : idsOrigin) {
      Noeud n = correspondanceIdNoeud.get(id);
      if (n != null) {
        queue.add(n);
        visited.add(n);
        result.add(new Localisation(n));
      }
    }

    while (!queue.isEmpty()) {
      Noeud current = queue.poll();

      List<Arc> voisins = correspondanceNomRue.get(current);
      if(voisins == null)
        continue;

      for (Arc arc : voisins) {
        Noeud voisin = arc.getArrivee();

        if (!visited.contains(voisin) &&
            voisin.getAltitude() <= current.getAltitude() + epsilon) {

          visited.add(voisin);
          queue.add(voisin);
          result.add(new Localisation(voisin));
        }
      }
    }

    return result.toArray(new Localisation[0]);
  }

  public Deque<Localisation> trouverCheminLePlusCourtPourContournerLaZoneInondee(long idOrigin, long idDestination, Localisation[] floodedZone) {
    //TODO
    Noeud noeudOrigine = correspondanceIdNoeud.get(idOrigin);
    Noeud noeudArrive = correspondanceIdNoeud.get(idDestination);

    Set<Noeud> zoneInondee = new HashSet<>();
    for (Localisation localisation : floodedZone) {
      zoneInondee.add(localisation.getNoeud());
    }

    //permet de garder une trace des Noeuds deja fait pour éviter les boucles
    Set<Noeud> vistiees = new HashSet<>();
    vistiees.add(noeudOrigine);

    //est a la file de noeuds
    Deque<Noeud> listNoeuds = new ArrayDeque<>();
    listNoeuds.add(noeudOrigine);

    //permet de garder les liaisons entre noeuds
    Map<Noeud,Noeud> cheminEnfantParent = new HashMap<>();
    cheminEnfantParent.put(noeudOrigine,null);

    while (!listNoeuds.isEmpty()) {
      Noeud noeudTester = listNoeuds.poll();
      if(noeudTester.equals(noeudArrive)){
        break;
      }
      if(correspondanceNomRue.get(noeudTester) != null){
        for (Arc arc : correspondanceNomRue.get(noeudTester)) {
          Noeud prochainNoeud = arc.getArrivee();
          if(!zoneInondee.contains(prochainNoeud) && !vistiees.contains(prochainNoeud)){
            vistiees.add(prochainNoeud);
            listNoeuds.add(prochainNoeud);
            cheminEnfantParent.put(prochainNoeud,noeudTester);
          }
        }
      }
    }

    if(!cheminEnfantParent.containsKey(noeudArrive)){
      throw new RuntimeException("Pas de chemin de " + noeudOrigine.getId() + "à" + noeudArrive.getId() + "évitant la zone inondée");
    }

    Deque<Localisation> cheminLePlusCourt = new ArrayDeque<>();
    Noeud noeud = noeudArrive;
    while(noeud != null){
      Localisation localisation = new Localisation(noeud);

      cheminLePlusCourt.addFirst(localisation);
      noeud = cheminEnfantParent.get(noeud);
    }

    if(cheminLePlusCourt.getFirst().getNoeud().equals(noeudOrigine)){
      return cheminLePlusCourt;
    }
    throw new RuntimeException("Pas de chemin de " + noeudOrigine.getId() + "à" + noeudArrive.getId() + "évitant la zone inondée");
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