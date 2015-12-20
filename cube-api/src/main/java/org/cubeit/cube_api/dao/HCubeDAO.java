package org.cubeit.cube_api.dao;

import java.util.List;

import org.cubeit.cube_api.model.Content;
import org.cubeit.cube_api.model.Cube;
import org.cubeit.cube_api.model.CubeContent;
import org.cubeit.cube_api.model.CubeShare;
import org.cubeit.cube_api.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 * Hibernate Cube DAO.
 * 
 * @author anurkris
 *
 */
public class HCubeDAO implements CubeDAO {

	private static HCubeDAO hCubeDAO;
	private Session session = null;

	/**
	 * Get HUserDAO object.
	 * 
	 * @return
	 */
	public static HCubeDAO getInstance() {
		if (hCubeDAO != null) {
			return hCubeDAO;
		}
		hCubeDAO = new HCubeDAO();
		return hCubeDAO;

	}

	/**
	 * Insert a cube.
	 * 
	 * @param cube
	 *            cube
	 */
	@Override
	public Cube insertCube(Cube cube) {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(cube);
		session.getTransaction().commit();
		session.close();
		return cube;
	}

	/**
	 * Add content to a cube
	 * 
	 * @param userId
	 * @param cubeId
	 * @param cubeContent
	 */
	@Override
	public CubeContent addContent(long userId, long cubeId, CubeContent cubeContent) {
		cubeContent.setCube_Id(cubeId);
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.getNamedQuery("findCubeByID").setString("id", String.valueOf(cubeContent.getCube_Id()));
		List<Cube> cubes = (List<Cube>) query.list();
		query = session.getNamedQuery("findContentByID").setString("id", String.valueOf(cubeContent.getContent_Id()));
		List<Content> contents = (List<Content>) query.list();
		session.save(cubeContent);
		session.getTransaction().commit();
		session.close();
		return cubeContent;
	}

	/**
	 * Remove content from a cube.
	 * 
	 * @param cubeId
	 * @param contentId
	 */
	@Override
	public void removeContent(long cubeId, long contentId) {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.getNamedQuery("findCubeContentByCubeContentIDs")
				.setString("cubeId", String.valueOf(cubeId)).setString("contentId", String.valueOf(contentId));
		List<CubeContent> cubeContentList = (List<CubeContent>) query.list();
		if (!cubeContentList.isEmpty()) {
			session.delete(cubeContentList.get(0));
		}
		session.getTransaction().commit();
		session.close();
	}

	/**
	 * Delete a cube
	 * 
	 * @param cubeId
	 */
	@Override
	public void deleteCube(long cubeId) {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.getNamedQuery("deleteCubeContentByCubeID").setString("cube_Id", String.valueOf(cubeId));
		query.executeUpdate();
		query = session.getNamedQuery("deleteCubeShareByID").setString("cube_Id", String.valueOf(cubeId));
		query.executeUpdate();
		query = session.getNamedQuery("deleteCubeByID").setString("id", String.valueOf(cubeId));
		query.executeUpdate();
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public Cube findCube(long userId, long cubeId) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Find all cubes.
	 * 
	 * @param userId
	 *            user id.
	 */
	@Override
	public List<Cube> findAllCubes(long userId) {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.getNamedQuery("findCubeByUserID").setString("userId", String.valueOf(userId));
		List<Cube> cubes = (List<Cube>) query.list();
		query = session.getNamedQuery("findCubeShareIDsByUserID").setString("userId", String.valueOf(userId));
		List list = query.list();

		// If any cube has been shared then add that to result
		if (!list.isEmpty()) {
			Query newQuery = session.getNamedQuery("findCubeByIDs").setParameterList("ids", query.list());
			List<Cube> sharedCubes = (List<Cube>) newQuery.list();
			cubes.addAll(sharedCubes);
		}
		session.getTransaction().commit();
		session.close();

		return cubes;
	}

	/**
	 * Share a cube.
	 * 
	 * @param cubeShare
	 */
	public CubeShare shareCube(CubeShare cubeShare) {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(cubeShare);
		session.getTransaction().commit();
		session.close();
		return cubeShare;
	}

}
