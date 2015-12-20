package org.cubeit.cube_api.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.cubeit.cube_api.model.User;
import org.cubeit.cube_api.service.UserService;

/**
 * User Resource Controller.
 * 
 * @author anurkris
 *
 */
@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {

	private UserService userService = null;

	/**
	 * Get User service object.
	 * 
	 */
	private UserService getUserService() {
		if (userService != null) {
			return userService;
		}
		userService = new UserService();
		return userService;
	}

	/**
	 * Register user.
	 * 
	 * @param user
	 * @return
	 */
	@POST
	public Response registerUser(User user) {

		User createdUser = getUserService().addUser(user);
		Response response = Response.status(Status.CREATED).entity(createdUser).build();
		return response;

	}

	/**
	 * Get Content Controller sub resource.
	 * 
	 * @return
	 */
	@Path("/{userId}/content")
	public ContentController getContentController() {
		return new ContentController();
	}

	/**
	 * Get cube controller sub resource.
	 * 
	 * @return
	 */
	@Path("/{userId}/cube")
	public CubeController getCubeController() {
		return new CubeController();
	}
}
