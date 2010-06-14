package mgi.frontend.test;

import java.util.*;

import mgi.frontend.datamodel.*;
import mgi.persistence.*;
import mgi.reporting.Timer;

import org.hibernate.*;

public class MarkerPerfTest2 {
	public static void main(String[] argv) {
		int count = 100000;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		Timer.write ("ready for initial query");
		Marker marker = (Marker) session.get(Marker.class, new Integer(10603));
		Timer.write("returned from initial query");		

		// --------------------------------------------------------
		
		List<Marker> markers2 = (List<Marker>) session.createQuery ("from Marker where markerKey <= 100000").list();
		long retrieved = Timer.write ("retrieved " + markers2.size() + " marker(s) at once");

		Marker marker2;
		for (Iterator iter = markers2.iterator(); iter.hasNext(); ) {
			marker2 = (Marker) iter.next();
		}
		long walked = Timer.write ("walked through list of markers");

		Session session2 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx2 = session2.beginTransaction();

		// --------------------------------------------------------
		
		Timer.write ("ready for initial query in 2nd session");
		Marker marker4 = (Marker) session2.get(Marker.class, new Integer(10603));
		Timer.write("returned from initial query in 2nd session");
		
		List<Marker> markers3 = (List<Marker>) session.createQuery ("from Marker where markerKey <= 100000").list();
		long retrieved2 = Timer.write ("retrieved " + markers3.size() + " marker(s) at once a second time");

		Marker marker3;
		for (Iterator iter = markers3.iterator(); iter.hasNext(); ) {
			marker3 = (Marker) iter.next();
		}
		long walked2 = Timer.write ("walked through list of markers a second time");

		System.out.println ("Average (retrieve and walk) per marker a second time: " + Double.toString((new Double((retrieved2+walked2)*1000.0/markers3.size())).longValue()/1000.0) + "ms");

		// --------------------------------------------------------
		
		tx.commit();
		session.close();
		tx2.commit();
		session2.close();

		Timer.writeTotal();
		HibernateUtil.shutdown();
	}
}