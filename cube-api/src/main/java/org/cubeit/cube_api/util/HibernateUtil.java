package org.cubeit.cube_api.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Hibernate Utility.
 * 
 * @author anurkris
 *
 */
public class HibernateUtil {

	private static SessionFactory sessionFactory = null;

	public static SessionFactory getSessionFactory() {
		if (sessionFactory != null) {
			return sessionFactory;
		}
		sessionFactory = new Configuration().configure("/org/cubeit/cube_api/resources/hibernate.cfg.xml")
				.buildSessionFactory();
		return sessionFactory;

	}
}
