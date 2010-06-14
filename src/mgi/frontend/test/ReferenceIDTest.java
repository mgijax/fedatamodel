package mgi.frontend.test;

import java.util.*;
import mgi.frontend.datamodel.*;
import mgi.persistence.*;
import mgi.reporting.Timer;

import org.hibernate.*;

public class ReferenceIDTest {
	public static void main(String[] argv) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		Timer.write ("ready for initial query");
		
		List<ReferenceID> referenceIDs = (List<ReferenceID>) session.createQuery ("from ReferenceID order by referenceKey").setMaxResults(1).list();
		Timer.write ("retrieved " + referenceIDs.size() + " reference ID(s)");

		ReferenceID id;
		for (Iterator iter = referenceIDs.iterator(); iter.hasNext(); ) {
			id = (ReferenceID) iter.next();
			System.out.println (id.toString());
		}
		Timer.write ("wrote reference ID(s)");

		List<ReferenceID> referenceIDs2 = (List<ReferenceID>) session.createQuery ("from ReferenceID order by referenceKey").setMaxResults(10).list();
		Timer.write ("retrieved " + referenceIDs2.size() + " more reference ID(s)");

		ReferenceID id2;
		for (Iterator iter = referenceIDs2.iterator(); iter.hasNext(); ) {
			id2 = (ReferenceID) iter.next();
			System.out.println (id2.toString());
		}
		Timer.write ("wrote more reference ID(s)");
/*		
		Marker marker = (Marker) session.get(Marker.class, new Integer(10603));
		System.out.println ("elapsed: " + (System.currentTimeMillis() - startTime) + "ms");
		System.out.println (marker.toString());
		System.out.println ("elapsed: " + (System.currentTimeMillis() - startTime) + "ms");
*/
		
		tx.commit();
		session.close();
		Timer.writeTotal();
		HibernateUtil.shutdown();
	}
}