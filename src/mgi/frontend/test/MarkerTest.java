package mgi.frontend.test;

import java.util.*;
import mgi.frontend.datamodel.*;
import mgi.persistence.*;
import mgi.reporting.Timer;

import org.hibernate.*;

public class MarkerTest {
	public static void main(String[] argv) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		Timer.write ("ready for initial query");
		
		List<Marker> markers = (List<Marker>) session.createQuery ("from Marker order by symbol").setMaxResults(10).list();
		//List<Marker> markers = (List<Marker>) session.createQuery ("from Marker where markerKey = 30 order by symbol").setMaxResults(50).list();
		Timer.write ("retrieved " + markers.size() + " marker(s)");

		Marker marker;
		for (Iterator iter = markers.iterator(); iter.hasNext(); ) {
			marker = (Marker) iter.next();
			System.out.println ("marker symbol: " +marker.getSymbol());
			System.out.println ("marker name: " + marker.getName());
			System.out.println ("marker key: " + marker.getMarkerKey());
			System.out.println ("marker subtype: " + marker.getMarkerSubtype());
			System.out.println ("marker type: " + marker.getMarkerType());
			System.out.println ("organism: " + marker.getOrganism());
			System.out.println ("primary ID: " + marker.getPrimaryID());
			System.out.println ("marker status: " + marker.getStatus());
			System.out.println ("count of references: " + marker.getCountOfReferences());
			System.out.println ("count of sequences: " + marker.getCountOfSequences());
			
			for (Iterator i = marker.getLocations().iterator(); i.hasNext(); ) {
				MarkerLocation ma = (MarkerLocation) i.next();
				System.out.println (" --> " + ma.toString());
			}
			
            for (Iterator i = marker.getReferences().iterator(); i.hasNext(); ) {
                Reference ma = (Reference) i.next();
                System.out.println (" --> " + ma.toString());
            }
            
            for (Iterator i = marker.getIds().iterator(); i.hasNext(); ) {
                MarkerID ma = (MarkerID) i.next();
                System.out.println (" --> " + ma.toString());
            }     
            
            for (Iterator i = marker.getNotes().iterator(); i.hasNext(); ) {
                MarkerNote ma = (MarkerNote) i.next();
                System.out.println (" --> " + ma.toString());
            }            
            
            for (Iterator i = marker.getOrthologousMarkers().iterator(); i.hasNext(); ) {
                MarkerOrthology ma = (MarkerOrthology) i.next();
                System.out.println (" --> " + ma.toString());
            }  
            
            for (Iterator i = marker.getSynonyms().iterator(); i.hasNext(); ) {
                MarkerSynonym ma = (MarkerSynonym) i.next();
                System.out.println (" --> " + ma.toString());
            }
		}
		
		Timer.write ("wrote marker(s)");
		
		tx.commit();
		session.close();
		Timer.writeTotal();
		HibernateUtil.shutdown();
	}
}