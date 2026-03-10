package src.classes;

import java.io.File;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.SynchronousQueue;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Graph {

	//ATTRIBUT ?
	//TODO
  protected Map<Integer, Noeud> correspondanceIdNoeud;
  protected Map<String, Arc> correspondanceNomRue;


    public Graph(String localisations, String roads) throws Exception {
        //TODO
      correspondanceIdNoeud = new HashMap<>();
      File csv = new File(localisations);
      DocumentBuilderFactory docBuildFact = DocumentBuilderFactory.newInstance();
      DocumentBuilder docBuild = docBuildFact.newDocumentBuilder();
      Document doc = docBuild.parse(csv);
      NodeList noeuds = doc.getElementsByTagName("id");
      for (int i = 0; i < noeuds.getLength(); i++) {
        Node noeud = noeuds.item(i);
        Element elNoeud = (Element) noeud;
        int id = Integer.parseInt(elNoeud.getAttribute("id"));
        String name = elNoeud.getAttribute("name");
        double lat = Double.parseDouble(elNoeud.getAttribute("lat"));
        double lon = Double.parseDouble(elNoeud.getAttribute("lon"));
        int alt = Integer.parseInt(elNoeud.getAttribute("alt"));
        Noeud n = new Noeud(id,lat,lon,name,alt);
        correspondanceIdNoeud.put(id,n);
        //ajouter : ajouterNoeud();
      }
    }

    public Localisation[] determinerZoneInondee(long[] idsOrigin,double epsilon) {
        //TODO
		return null ;
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
