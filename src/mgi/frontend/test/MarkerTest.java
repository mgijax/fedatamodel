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
		
		List<Marker> markers = (List<Marker>) session.createQuery ("from Marker order by symbol").setMaxResults(50).list();
		Timer.write ("retrieved " + markers.size() + " marker(s)");

		Marker marker;
		for (Iterator iter = markers.iterator(); iter.hasNext(); ) {
			marker = (Marker) iter.next();
			System.out.println (marker.getSymbol());
/*			for (Iterator i = marker.getLocations().iterator(); i.hasNext(); ) {
				MarkerLocation ma = (MarkerLocation) i.next();
				System.out.println (" --> " + ma.toString());
			}*/
		}
		Timer.write ("wrote marker(s)");
		
		tx.commit();
		session.close();
		Timer.writeTotal();
		HibernateUtil.shutdown();
	}
}