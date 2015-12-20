package org.cubeit.cube_api.service;

import java.util.List;

import org.cubeit.cube_api.dao.HCubeDAO;
import org.cubeit.cube_api.model.Cube;
import org.cubeit.cube_api.model.CubeContent;
import org.cubeit.cube_api.model.CubeShare;

/**
 * Cube Service.
 * 
 * @author anurkris
 *
 */
public class CubeService {

	/**
	 * Add Cube for a user
	 * 
	 * @param userId
	 *            user id
	 * @param Cube
	 *            cube
	 * @return
	 */
	public Cube addCube(long userId, Cube Cube) {
		Cube.setUserId(userId);
		return HCubeDAO.getInstance().insertCube(Cube);
	}

	/**
	 * Get all cubes of a user.
	 * 
	 * @param userId
	 *            user id
	 * @return
	 */
	public List<Cube> getAllCubes(long userId) {
		return HCubeDAO.getInstance().findAllCubes(userId);

	}

	/**
	 * Add content to a cube.
	 * 
	 * @param userId
	 *            user id
	 * @param cubeId
	 *            cube id.
	 * @param cubeContent
	 *            cube content
	 * @return
	 */
	public CubeContent addContent(long userId, long cubeId, CubeContent cubeContent) {
		return HCubeDAO.getInstance().addContent(userId, cubeId, cubeContent);
	}

	/**
	 * Remove Content from a cube.
	 * 
	 * @param cubeId
	 *            cube id.
	 * @param contentId
	 *            content id.
	 */
	public void removeContent(long cubeId, long contentId) {
		HCubeDAO.getInstance().removeContent(cubeId, contentId);
	}

	/**
	 * Remove Cube.
	 * 
	 * @param cubeId
	 *            cube id
	 */
	public void removeCube(long cubeId) {
		HCubeDAO.getInstance().deleteCube(cubeId);
	}

	/**
	 * Share a cube.
	 * 
	 * @param cubeShare
	 * @return
	 */
	public CubeShare shareCube(CubeShare cubeShare) {
		return HCubeDAO.getInstance().shareCube(cubeShare);
	}

}
