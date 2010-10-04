package mgi.frontend.test;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import mgi.frontend.datamodel.Allele;
import mgi.frontend.datamodel.AlleleID;
import mgi.frontend.datamodel.AlleleNote;
import mgi.frontend.datamodel.AlleleSynonym;
import mgi.frontend.datamodel.AlleleSystem;
import mgi.frontend.datamodel.AlleleSystemAssayResult;
import mgi.frontend.datamodel.AlleleSystemAssayResultImagePane;
import mgi.frontend.datamodel.AlleleSystemOtherAllele;
import mgi.frontend.datamodel.AlleleSystemOtherSystem;
import mgi.frontend.datamodel.Image;
import mgi.frontend.datamodel.Reference;
import mgi.persistence.HibernateUtil;
import mgi.reporting.Timer;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class AlleleTest {
	public static void main(String[] argv) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		Timer.write ("ready for initial query");
		
		List alleles = session.createQuery ("from Allele as a1 where a1.alleleKey = 3205").setMaxResults(200).list();
		//List alleles = session.createQuery ("from Allele as a1 where a1.alleleKey = 22049").setMaxResults(200).list();
		
		Allele all;
		for (Iterator iter = alleles.iterator(); iter.hasNext(); ) {
			all = (Allele) iter.next();
			System.out.println("THE ALLELE");
			System.out.println(all);

			System.out.println("THE IDS FOR THAT ALLELE");
			Set<AlleleID> allID = all.getIds();
			for (AlleleID inId: allID) {
			    System.out.println(inId);
			}
			
			System.out.println("THE NOTES FOR THE ALLELE");
	        List<AlleleNote> allNotes = all.getNotes();
	        for (AlleleNote note: allNotes) {
	            System.out.println(note);
	        }
	        
	        System.out.println("THE ALLELE SYNONYMS");
            Set<AlleleSynonym> allSyn = all.getSynonyms();
            for (AlleleSynonym syn: allSyn) {
                System.out.println(syn);
            }
            
            System.out.println("THE REFERENCES");
            List<Reference> refList = all.getReferences();
            for (Reference ref: refList) {
                System.out.println(ref);
            }
            
            System.out.println("THE ALLELE SYSTEMS");
            List<AlleleSystem> systemList = all.getAlleleSystems();
            
            for (AlleleSystem as: systemList) {
                System.out.println(as);
                
                List<AlleleSystemAssayResult> asarList = as.getAlleleSystemAssayResults();
                for (AlleleSystemAssayResult asar: asarList) {
                    System.out.println(asar);
                    List<AlleleSystemAssayResultImagePane> asaripList = asar.getPanes();
                    for (AlleleSystemAssayResultImagePane asarip: asaripList) {
                        System.out.println(asarip);
                    }
                }
                
                List<Image> imageList = as.getImages();
                for (Image i: imageList) {
                    System.out.println(i);
                }
                
                List<AlleleSystemOtherAllele> asoaList = as.getOtherAlleles();
                for (AlleleSystemOtherAllele asoa: asoaList) {
                    System.out.println(asoa);
                }
                
                List<AlleleSystemOtherSystem> asosList = as.getOtherSystems();
                for (AlleleSystemOtherSystem asos: asosList) {
                    System.out.println(asos);
                }
            }
		}
		Timer.write ("wrote allele(s)");

		tx.commit();
		session.close();
		Timer.writeTotal();
		HibernateUtil.shutdown();
	}
}