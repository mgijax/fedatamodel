package mgi.frontend.test;

import java.util.*;

import mgi.frontend.datamodel.*;
import mgi.persistence.*;
import mgi.reporting.Timer;

import org.hibernate.*;

public class MarkerReferenceAssociationTest {
	public static void main(String[] argv) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		Timer.write ("ready for initial query");
		
		List<MarkerReferenceAssociation> assocList = (List<MarkerReferenceAssociation>) session.createQuery ("from MarkerReferenceAssociation").setMaxResults(1000).list();
		Timer.write ("retrieved " + assocList.size() + " association(s)");
		
		MarkerReferenceAssociation assoc;
		for (Iterator iter = assocList.iterator(); iter.hasNext(); ) {
			assoc = (MarkerReferenceAssociation) iter.next();
//			System.out.println (assoc.toString());
			System.out.println (assoc.getMarker().getSymbol() 
					+ " --> " + assoc.getReference().getJnumID());
		}
		Timer.write ("wrote association(s)");
		
		tx.commit();
		session.close();
		Timer.writeTotal();
		HibernateUtil.shutdown();
	}
}