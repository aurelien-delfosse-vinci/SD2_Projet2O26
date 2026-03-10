package src.classes;

import java.io.File;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class Graph {

	//ATTRIBUT ?
	//TODO
  protected Map<String, Noeud> correspondanceIdNoeud;
  protected Map<String, Arc> correspondanceNomRue;


    public Graph(String localisations, String roads) throws Exception {
        //TODO
      correspondanceIdNoeud = new HashMap<>();
      File csv = new File(localisations);
      DocumentBuilderFactory docBuildFact = DocumentBuilderFactory.newInstance();
      DocumentBuilder docBuild = docBuildFact.newDocumentBuilder();
      Document doc = docBuild.parse(csv);

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
