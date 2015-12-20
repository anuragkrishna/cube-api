package org.cubeit.cube_api.dao;

import java.util.List;

import org.cubeit.cube_api.model.Content;
import org.cubeit.cube_api.model.ContentShare;
import org.cubeit.cube_api.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 * Hibernate Content DAO.
 * 
 * @author anurkris
 *
 */
public class HContentDAO implements ContentDAO {

	private static HContentDAO hContentDAO;
	private Session session = null;

	/**
	 * Get HUserDAO object.
	 * 
	 * @return
	 */
	public static HContentDAO getInstance() {
		if (hContentDAO != null) {
			return hContentDAO;
		}
		hContentDAO = new HContentDAO();
		return hContentDAO;

	}

	/**
	 * Insert Content.
	 * 
	 * @param content
	 */
	@Override
	public Content insertContent(Content content) {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(content);
		session.getTransaction().commit();
		session.close();
		return content;
	}

	/**
	 * Find content.
	 * 
	 * @param userId
	 * @param contentId
	 */
	@Override
	public Content findContent(long userId, long contentId) {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Content content = session.get(Content.class, contentId);
		session.getTransaction().commit();
		session.close();
		return content;
	}

	/**
	 * Find all content.
	 * 
	 * @param userId
	 */
	@Override
	public List<Content> findAllContent(long userId) {
		
		Query newQuery = null;
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.getNamedQuery("findContentByUserID").setString("userId", String.valueOf(userId));
		List<Content> contents = (List<Content>) query.list();
		
		query = session.getNamedQuery("findContentShareIDsByUserID").setString("userId", String.valueOf(userId));
		List list = query.list();
		// If any content has been shared then add that to result
		if (!list.isEmpty()) {
			newQuery = session.getNamedQuery("findContentByIDs").setParameterList("ids", query.list());
			List<Content> sharedCubes = (List<Content>) newQuery.list();
			contents.addAll(sharedCubes);
		}
		
		query = session.getNamedQuery("findCubeShareIDsByUserID").setString("userId", String.valueOf(userId));
		list = query.list();
		
		// If any cube has been shared then add contents of that to result
		if (!list.isEmpty()) {
			query = session.getNamedQuery("findCubeContentIDsByCubeIDs").setParameterList("ids", query.list());
			list = query.list();
			//If cubes have contents then add that.
			if(!list.isEmpty())
			{
				newQuery = session.getNamedQuery("findContentByIDs").setParameterList("ids", query.list());
				List<Content> sharedCubesContents = (List<Content>) newQuery.list();
				contents.addAll(sharedCubesContents);
			}
		}

		session.getTransaction().commit();
		session.close();
		return contents;
	}

	/**
	 * Share a content.
	 * @param contentShare
	 */
	@Override
	public ContentShare shareContent(ContentShare contentShare) {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(contentShare);
		session.getTransaction().commit();
		session.close();
		return contentShare;
	}
	
	/**
	 * Delete a content
	 * 
	 * @param contentId content id.
	 */
	@Override
	public void deleteContent(long contentId) {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.getNamedQuery("deleteCubeContentByContentID").setString("content_Id", String.valueOf(contentId));
		query.executeUpdate();
		query = session.getNamedQuery("deleteContentShareByID").setString("content_Id", String.valueOf(contentId));
		query.executeUpdate();
		query = session.getNamedQuery("deleteContentByID").setString("id", String.valueOf(contentId));
		query.executeUpdate();
		session.getTransaction().commit();
		session.close();
	}

}
