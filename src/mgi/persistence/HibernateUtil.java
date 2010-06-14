package mgi.persistence;

import org.hibernate.*;
import org.hibernate.cfg.*;

public class HibernateUtil {
	private static SessionFactory sessionFactory;
	
	static {
		try {
			sessionFactory = new AnnotationConfiguration()
			.addAnnotatedClass(mgi.frontend.datamodel.Marker.class)
			.addAnnotatedClass(mgi.frontend.datamodel.MarkerSynonym.class)
			.addAnnotatedClass(mgi.frontend.datamodel.MarkerOrthology.class)
			.addAnnotatedClass(mgi.frontend.datamodel.MarkerID.class)
			.addAnnotatedClass(mgi.frontend.datamodel.Reference.class)
			.addAnnotatedClass(mgi.frontend.datamodel.ReferenceID.class)
			.addAnnotatedClass(mgi.frontend.datamodel.MarkerReferenceAssociation.class)
			.addAnnotatedClass(mgi.frontend.datamodel.MarkerAnnotation.class)
			.addAnnotatedClass(mgi.frontend.datamodel.MarkerNote.class)
			.addAnnotatedClass(mgi.frontend.datamodel.MarkerLocation.class)
			.addAnnotatedClass(mgi.frontend.datamodel.Sequence.class)
			.addAnnotatedClass(mgi.frontend.datamodel.SequenceID.class)
			.addAnnotatedClass(mgi.frontend.datamodel.SequenceLocation.class)
			.configure().buildSessionFactory();
		}
		catch (Throwable ex) {
			throw new ExceptionInInitializerError(ex);
		}
	}
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public static void shutdown() {
		getSessionFactory().close();
	}
}