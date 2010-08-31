package mgi.frontend.test;

import java.util.Iterator;
import java.util.List;

import mgi.frontend.datamodel.Probe;
import mgi.frontend.datamodel.ProbeCloneCollection;
import mgi.persistence.HibernateUtil;
import mgi.reporting.Timer;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class ProbeTest {
	public static void main(String[] argv) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		Timer.write ("ready for initial query");
		
		List<Probe> probes = (List<Probe>) session.createQuery ("from Probe where probe_key = 1854082 order by probe_key").setMaxResults(50).list();
		//List<Sequence> seqs = (List<Sequence>) session.createQuery ("from Sequence order by primaryID").setMaxResults(50).list();
		Timer.write ("retrieved " + probes.size() + " sequence(s)");

		Probe probe;
		for (Iterator iter = probes.iterator(); iter.hasNext(); ) {
			probe = (Probe) iter.next();
			System.out.println("-------------------");
			System.out.println("probe key: " + probe.getProbeKey());
			System.out.println("name: " + probe.getName());
			System.out.println("primary id: " + probe.getPrimaryid());
			System.out.println("logical db: " + probe.getLogicaldb());
			System.out.println("clone id: " + probe.getCloneid());
			System.out.println("segment type: " + probe.getSegmenttype());
			
	         for (Iterator iter2 = probe.getProbeCloneCollection().iterator(); iter2.hasNext(); ) {
	                ProbeCloneCollection pcc = (ProbeCloneCollection) iter2.next();
	                System.out.println ("  Clone Collection --> " + pcc.getCollection());
	            }
         
		 System.out.println("-------------------");
		}
		Timer.write ("wrote sequence(s)");
		
		tx.commit();
		session.close();
		Timer.writeTotal();
		HibernateUtil.shutdown();
	}
}