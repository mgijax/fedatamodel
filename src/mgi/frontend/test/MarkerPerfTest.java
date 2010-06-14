package mgi.frontend.test;

import java.util.*;

import mgi.frontend.datamodel.*;
import mgi.persistence.*;
import mgi.reporting.Timer;

import org.hibernate.*;

public class MarkerPerfTest {
	public static void main(String[] argv) {
		int count = 100000;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		Timer.write ("ready for initial query");
		Marker marker = (Marker) session.get(Marker.class, new Integer(10603));
		Timer.write("returned from initial query");		

		// --------------------------------------------------------
		
		long total = 0;
		int markers = 0;
		
		for (int i=1; i <= count; i++) {
			marker = (Marker) session.get(Marker.class, new Integer(i));
			if (marker != null) {
				markers = markers + 1;
			}
		}
		total = Timer.getElapsed();

		// --------------------------------------------------------
		
		int markers2 = 0;
		for (int i=1; i <= count; i++) {
			marker = (Marker) session.get(Marker.class, new Integer(i));
			if (marker != null) {
				markers2 = markers2 + 1;
			}
		}
		long total2 = Timer.getElapsed();

		// --------------------------------------------------------
		
		System.out.println ("Total time retrieving " + markers + " markers: " + total + "ms (" 
				+ (count - markers) + " nulls)");
		System.out.println ("Overall average per marker: " + Double.toString((new Double(total*1000.0/markers)).longValue()/1000.0) + "ms");
		System.out.println ("Overall average per key attempted: " + Double.toString((new Double(total*1000.0/count)).longValue()/1000.0) + "ms");
		System.out.println ("Total time retrieving " + markers + " markers a second time: " + total2 + "ms (" 
				+ (count - markers) + " nulls)");
				
		tx.commit();
		session.close();
		Timer.writeTotal();
		HibernateUtil.shutdown();
	}
}