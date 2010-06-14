package mgi.frontend.test;

import java.util.*;

import mgi.frontend.datamodel.MarkerReferenceAssociation;
import mgi.frontend.datamodel.Reference;
import mgi.persistence.*;
import org.hibernate.*;
import mgi.reporting.Timer;

public class ReferenceTest {
	public static void main(String[] argv) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		Timer.write ("ready for initial query");
		
		List refs = session.createQuery ("from Reference order by jnumNumeric").setMaxResults(20).list();
		Timer.write ("retrieved " + refs.size() + " reference(s)");
		
		Reference ref;
		for (Iterator iter = refs.iterator(); iter.hasNext(); ) {
			ref = (Reference) iter.next();
			System.out.println (ref.toString());
//			System.out.println (ref.getJnumID() + " : " + ref.getShortCitation());
//			for (Iterator iter2 = ref.getMarkerAssociations().iterator(); iter2.hasNext(); ) {
//				MarkerReferenceAssociation mra = (MarkerReferenceAssociation) iter2.next();
//				System.out.println (" --> " + mra.getMarker().getSymbol());
//			}
		}
		Timer.write ("wrote reference(s)");

		tx.commit();
		session.close();
		Timer.writeTotal();
		HibernateUtil.shutdown();
	}
}