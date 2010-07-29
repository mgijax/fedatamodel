package mgi.frontend.test;

import java.util.Iterator;
import java.util.List;

import mgi.frontend.datamodel.Sequence;
import mgi.persistence.HibernateUtil;
import mgi.reporting.Timer;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class SequenceTest {
	public static void main(String[] argv) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		Timer.write ("ready for initial query");
		
		List<Sequence> seqs = (List<Sequence>) session.createQuery ("from Sequence order by primaryID").setMaxResults(50).list();
		Timer.write ("retrieved " + seqs.size() + " sequence(s)");

		Sequence seq;
		for (Iterator iter = seqs.iterator(); iter.hasNext(); ) {
			seq = (Sequence) iter.next();
			System.out.println (seq.toString());
		}
		Timer.write ("wrote sequence(s)");
		
		tx.commit();
		session.close();
		Timer.writeTotal();
		HibernateUtil.shutdown();
	}
}