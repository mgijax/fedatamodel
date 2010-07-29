package mgi.frontend.test;

import java.util.Iterator;
import java.util.List;

import mgi.frontend.datamodel.MarkerID;
import mgi.persistence.HibernateUtil;
import mgi.reporting.Timer;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class MarkerIDTest {
	public static void main(String[] argv) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		Timer.write ("ready for initial query");
		
		List<MarkerID> markerIDs = (List<MarkerID>) session.createQuery ("from MarkerID order by markerKey").setMaxResults(1).list();
		Timer.write ("retrieved " + markerIDs.size() + " marker ID(s)");

		MarkerID id;
		for (Iterator iter = markerIDs.iterator(); iter.hasNext(); ) {
			id = (MarkerID) iter.next();
			System.out.println (id.toString());
		}
		Timer.write ("wrote marker ID(s)");

		List<MarkerID> markerIDs2 = (List<MarkerID>) session.createQuery ("from MarkerID order by markerKey").setMaxResults(10).list();
		Timer.write ("retrieved " + markerIDs2.size() + " more marker ID(s)");

		MarkerID marker2;
		for (Iterator iter = markerIDs2.iterator(); iter.hasNext(); ) {
			marker2 = (MarkerID) iter.next();
			System.out.println (marker2.toString());
		}
		Timer.write ("wrote more marker ID(s)");
		
		tx.commit();
		session.close();
		Timer.writeTotal();
		HibernateUtil.shutdown();
	}
}