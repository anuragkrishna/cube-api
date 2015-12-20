package org.cubeit.cube_api.service;

import org.cubeit.cube_api.dao.HUserDAO;
import org.cubeit.cube_api.model.User;

/**
 * User Service.
 * 
 * @author anurkris
 *
 */
public class UserService {

	/**
	 * Add user
	 * 
	 * @param user
	 * @return
	 */
	public User addUser(User user) {
		return HUserDAO.getInstance().insertUser(user);
	}

}
