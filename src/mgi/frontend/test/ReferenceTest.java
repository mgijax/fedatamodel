package mgi.frontend.test;

import java.util.Iterator;
import java.util.List;

import mgi.frontend.datamodel.Reference;
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
		
		List refs = session.createQuery ("from Reference order by jnumNumeric").setMaxResults(200).list();
		Timer.write ("retrieved " + refs.size() + " reference(s)");
		
		Reference ref;
		for (Iterator iter = refs.iterator(); iter.hasNext(); ) {
			ref = (Reference) iter.next();
			System.out.println("----------------");
			System.out.println (ref.getJnumID());
			System.out.println (ref.getPubMedID());
			System.out.println (ref.getAuthors());
			System.out.println (ref.getTitle());
			System.out.println (ref.getJournal());
			System.out.println (ref.getYear());
			System.out.println (ref.getVol());
			System.out.println (ref.getIssue());
			System.out.println (ref.getPages());
			System.out.println ("Count of Markers: " +ref.getCountOfMarkers());
			System.out.println ("Count of Probes: " +ref.getCountOfProbes());
			System.out.println ("Count of GXD Assays: " +ref.getCountOfGXDAssays());
			System.out.println ("Count of GXD Assays Results: " +ref.getCountOfGXDResults());
			System.out.println ("Count of GXD Index Records: " +ref.getCountOfGXDIndex());
			System.out.println ("Count of Mapping Results: " +ref.getCountOfMappingResults());
			System.out.println ("Count of Sequence: " +ref.getCountOfSequenceResults());
			System.out.println ("Count of Alleles: " +ref.getCountOfAlleles());
			System.out.println ("Book Editor: " + ref.getBookEditor());
			System.out.println ("Book Title: " + ref.getBookTitle());
			System.out.println ("Book Publisher: " + ref.getBookPublisher());
			System.out.println ("Book Edition: " + ref.getBookEdition());
			System.out.println ("Book Place: " + ref.getBookPlace());
			
			System.out.println("----------------");
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