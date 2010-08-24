package mgi.frontend.test;

import java.util.Iterator;
import java.util.List;

import mgi.frontend.datamodel.Marker;
import mgi.frontend.datamodel.Reference;
import mgi.frontend.datamodel.Sequence;
import mgi.frontend.datamodel.SequenceID;
import mgi.frontend.datamodel.SequenceLocation;
import mgi.frontend.datamodel.SequenceSource;
import mgi.persistence.HibernateUtil;
import mgi.reporting.Timer;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class SequenceTest {
	public static void main(String[] argv) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		Timer.write ("ready for initial query");
		
		List<Sequence> seqs = (List<Sequence>) session.createQuery ("from Sequence where primaryID in ('AK047243', 'X65997') order by primaryID").setMaxResults(50).list();
		//List<Sequence> seqs = (List<Sequence>) session.createQuery ("from Sequence order by primaryID").setMaxResults(50).list();
		Timer.write ("retrieved " + seqs.size() + " sequence(s)");

		Sequence seq;
		for (Iterator iter = seqs.iterator(); iter.hasNext(); ) {
			seq = (Sequence) iter.next();
			System.out.println("-------------------");
			System.out.println("Primary ID: " + seq.getPrimaryID());
			System.out.println("Sequence Type: " + seq.getSequenceType());
			System.out.println("Length: " + seq.getLength());
			System.out.println("Description: " + seq.getDescription());
			System.out.println("Provider: " + seq.getProvider());
			System.out.println("Version: " + seq.getVersion());
			System.out.println("Status: " + seq.getStatus());
			System.out.println("Organism: " + seq.getOrganism());
			System.out.println("library: " + seq.getLibrary());
			System.out.println("marker count: " + seq.getCountOfMarkers());

	     for (Iterator <SequenceID> iter2 = seq.getIds().iterator(); iter2.hasNext(); ) {
	         SequenceID sid = (SequenceID) iter2.next();
    	     if (!sid.isPreferred()) {
    	         System.out.println ("All Sequence ID's --> " + sid.getAccID());
    	         }
	         }			
			
         for (Iterator <Reference> iter2 = seq.getReferences().iterator(); iter2.hasNext(); ) {
             Reference mra = (Reference) iter2.next();
             System.out.println (" --> " + mra.getTitle());
             }
          
         for (Iterator <SequenceLocation> iter2 = seq.getLocations().iterator(); iter2.hasNext(); ) {
             SequenceLocation loc = (SequenceLocation) iter2.next();
             System.out.println (" Location --> " + loc.getChromosome());
             System.out.println (" Location --> " + loc.getStartCoordinate());
             System.out.println (" Location --> " + loc.getEndCoordinate());
             System.out.println (" Location --> " + loc.getProvider());
             System.out.println (" Location --> " + loc.getLocationType());
             System.out.println (" Location --> " + loc.getBuildIdentifier());
             }
         
         for (Iterator <SequenceSource> iter2 = seq.getSources().iterator(); iter2.hasNext(); ) {
             SequenceSource source = (SequenceSource) iter2.next();
             System.out.println (" Source --> " + source.getAge());
             System.out.println (" Source --> " + source.getStrain());
             System.out.println (" Source --> " + source.getTissue());
             System.out.println (" Source --> " + source.getCellLine());
             System.out.println (" Source --> " + source.getSex());
             }         

         for (Iterator <Marker> iter2 = seq.getMarkers().iterator(); iter2.hasNext(); ) {
             Marker marker = (Marker) iter2.next();
             System.out.println ("Marker Symbol --> " + marker.getSymbol());
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