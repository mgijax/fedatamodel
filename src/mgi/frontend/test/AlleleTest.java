package mgi.frontend.test;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import mgi.frontend.datamodel.Allele;
import mgi.frontend.datamodel.AlleleID;
import mgi.frontend.datamodel.AlleleNote;
import mgi.frontend.datamodel.AlleleSynonym;
import mgi.persistence.HibernateUtil;
import mgi.reporting.Timer;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class AlleleTest {
	public static void main(String[] argv) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		Timer.write ("ready for initial query");
		
		List alleles = session.createQuery ("from Allele as a1 where a1.alleleKey < 10").setMaxResults(200).list();
		
		Allele all;
		for (Iterator iter = alleles.iterator(); iter.hasNext(); ) {
			all = (Allele) iter.next();
			System.out.println(all);
			
			Set<AlleleID> allID = all.getIds();
			for (AlleleID inId: allID) {
			    System.out.println(inId);
			}
			
	        List<AlleleNote> allNotes = all.getNotes();
	        for (AlleleNote note: allNotes) {
	            System.out.println(note);
	        }
	        
            Set<AlleleSynonym> allSyn = all.getSynonyms();
            for (AlleleSynonym syn: allSyn) {
                System.out.println(syn);
            }	        
		}
		Timer.write ("wrote allele(s)");

		tx.commit();
		session.close();
		Timer.writeTotal();
		HibernateUtil.shutdown();
	}
}