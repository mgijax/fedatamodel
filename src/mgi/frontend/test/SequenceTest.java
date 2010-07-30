package mgi.frontend.test;

import java.util.Iterator;
import java.util.List;

import mgi.frontend.datamodel.Reference;
import mgi.frontend.datamodel.Sequence;
import mgi.frontend.datamodel.SequenceLocation;
import mgi.persistence.HibernateUtil;
import mgi.reporting.Timer;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class SequenceTest {
	public static void main(String[] argv) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		Timer.write ("ready for initial query");
		
		List<Sequence> seqs = (List<Sequence>) session.createQuery ("from Sequence where primaryID = 'AK047243' order by primaryID").setMaxResults(50).list();
		Timer.write ("retrieved " + seqs.size() + " sequence(s)");

		Sequence seq;
		for (Iterator iter = seqs.iterator(); iter.hasNext(); ) {
			seq = (Sequence) iter.next();
			System.out.println("-------------------");
			System.out.println(seq.getPrimaryID());
			System.out.println(seq.getSequenceType());
			System.out.println(seq.getLength());
			System.out.println(seq.getStrain());
			System.out.println(seq.getDescription());
			System.out.println(seq.getProvider());
			System.out.println(seq.getOrganism());
			System.out.println(seq.getLibrary());
			System.out.println(seq.getTissue());
			
          for (Iterator iter2 = seq.getReferences().iterator(); iter2.hasNext(); ) {
              Reference mra = (Reference) iter2.next();
              System.out.println (" --> " + mra.getTitle());
              }
          
          for (Iterator iter2 = seq.getLocations().iterator(); iter2.hasNext(); ) {
              SequenceLocation loc = (SequenceLocation) iter2.next();
              System.out.println (" --> " + loc.getChromosome());
              }
			//System.out.println(seq.);  Chromosome stuff that isn't there yet.
			//System.out.println(seq.get);
			//System.out.println(seq.get);
			System.out.println("-------------------");
		}
		Timer.write ("wrote sequence(s)");
		
		tx.commit();
		session.close();
		Timer.writeTotal();
		HibernateUtil.shutdown();
	}
}