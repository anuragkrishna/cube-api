package org.cubeit.cube_api.dao;

import org.cubeit.cube_api.model.User;
import org.cubeit.cube_api.util.HibernateUtil;
import org.hibernate.Session;

/**
 * Hibernate User DAO.
 * 
 * @author anurkris
 *
 */
public class HUserDAO implements UserDAO {

	private static HUserDAO hUserDAO;
	private Session session = null;

	/**
	 * Get HUserDAO object.
	 * 
	 * @return
	 */
	public static HUserDAO getInstance() {
		if (hUserDAO != null) {
			return hUserDAO;
		}
		hUserDAO = new HUserDAO();
		return hUserDAO;

	}

	/**
	 * Insert a user.
	 */
	@Override
	public User insertUser(User user) {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
		session.close();
		return user;
	}

}
