package mgi.frontend.test;

import java.util.Iterator;
import java.util.List;

import mgi.frontend.datamodel.Marker;
import mgi.frontend.datamodel.Reference;
import mgi.persistence.HibernateUtil;
import mgi.reporting.Timer;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class ReferenceTest {
	public static void main(String[] argv) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		Timer.write ("ready for initial query");
		
		//List refs = session.createQuery ("from Reference order by jnumNumeric").setMaxResults(200).list();
		List refs = session.createQuery ("from Reference where referenceKey = 614 order by jnumNumeric").setMaxResults(200).list();
		Timer.write ("retrieved " + refs.size() + " reference(s)");
		
		Reference ref;
		for (Iterator iter = refs.iterator(); iter.hasNext(); ) {
			ref = (Reference) iter.next();
			System.out.println("----------------");
			System.out.println (ref);
			
			System.out.println("----------------");
//			System.out.println (ref.getJnumID() + " : " + ref.getShortCitation());
			for (Iterator iter2 = ref.getMarkers().iterator(); iter2.hasNext(); ) {
				Marker mra = (Marker) iter2.next();
				System.out.println (" Marker: --> " + mra);
			}
		}
		Timer.write ("wrote reference(s)");

		tx.commit();
		session.close();
		Timer.writeTotal();
		HibernateUtil.shutdown();
	}
}