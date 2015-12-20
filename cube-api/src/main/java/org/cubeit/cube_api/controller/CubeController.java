package org.cubeit.cube_api.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.cubeit.cube_api.model.Cube;
import org.cubeit.cube_api.model.CubeContent;
import org.cubeit.cube_api.model.CubeShare;
import org.cubeit.cube_api.service.CubeService;

/**
 * Cube Resource Controller.
 * 
 * @author anurkris
 *
 */
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CubeController {
	private CubeService cubeService = null;

	/**
	 * Get Cube service object.
	 * 
	 */
	private CubeService getCubeService() {
		if (cubeService != null) {
			return cubeService;
		}
		cubeService = new CubeService();
		return cubeService;
	}

	/**
	 * Add Cube.
	 * 
	 * @param userId
	 * @param cube
	 * @return
	 */
	@POST
	@Path("/")
	public Response addCube(@PathParam("userId") Long userId, Cube cube) {

		Cube addedCube = getCubeService().addCube(userId, cube);
		Response response = Response.status(Status.CREATED).entity(addedCube).build();
		return response;

	}

	/**
	 * Get all cubes.
	 * 
	 * @param userId
	 * @return
	 */
	@GET
	@Path("/")
	public Response getAllCube(@PathParam("userId") Long userId) {

		List<Cube> cubes = getCubeService().getAllCubes(userId);
		GenericEntity<List<Cube>> list = new GenericEntity<List<Cube>>(cubes) {
		};
		Response response = Response.status(Status.FOUND).entity(list).build();
		return response;

	}

	/**
	 * Create a conversation.
	 * 
	 * @param uriInfo
	 *            uriInfo context.
	 * @param conversation
	 *            conversation object.
	 * @return
	 */
	@DELETE
	@Path("/{cube_Id}")
	public Response removeCube(@PathParam("cube_Id") Long cubeId) {

		getCubeService().removeCube(cubeId);
		Response response = Response.status(Status.OK).build();
		return response;
	}

	/**
	 * Add content.
	 * 
	 * @param userId
	 * @param cubeId
	 * @param cubeContent
	 * @return
	 */
	@POST
	@Path("/{cube_Id}/content")
	public Response addContent(@PathParam("userId") Long userId, @PathParam("cube_Id") Long cubeId,
			CubeContent cubeContent) {

		CubeContent newCubeContent = getCubeService().addContent(userId, cubeId, cubeContent);
		Response response = Response.status(Status.OK).entity(newCubeContent).build();
		return response;

	}

	/**
	 * Remove content from a cube.
	 * 
	 * @param cubeId
	 * @param contentId
	 * @return
	 */
	@DELETE
	@Path("/{cube_Id}/content/{contentId}")
	public Response removeContent(@PathParam("cube_Id") Long cubeId, @PathParam("contentId") Long contentId) {

		getCubeService().removeContent(cubeId, contentId);
		Response response = Response.status(Status.OK).build();
		return response;

	}

	/**
	 * Share a cube.
	 * 
	 * @param cube_Id
	 * @param cubeShare
	 * @return
	 */
	@POST
	@Path("/{cube_Id}/share")
	public Response shareCube(@PathParam("cube_Id") Long cube_Id, CubeShare cubeShare) {

		cubeShare.setCube_id(cube_Id);
		CubeShare updatedCubeShare = getCubeService().shareCube(cubeShare);
		Response response = Response.status(Status.OK).entity(updatedCubeShare).build();
		return response;

	}
}
