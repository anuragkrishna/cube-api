package org.cubeit.cube_api.service;

import java.util.List;

import org.cubeit.cube_api.dao.HContentDAO;
import org.cubeit.cube_api.dao.HCubeDAO;
import org.cubeit.cube_api.model.Content;
import org.cubeit.cube_api.model.ContentShare;

/**
 * Content Service Class.
 * 
 * @author anurkris
 *
 */
public class ContentService {

	/**
	 * Add Content.
	 * 
	 * @param userId
	 *            user id
	 * @param content
	 *            content.
	 * @return
	 */
	public Content addContent(Long userId, Content content) {
		content.setUserId(userId);
		return HContentDAO.getInstance().insertContent(content);
	}

	/**
	 * Get all contents of a user.
	 * 
	 * @param userId
	 *            user id
	 * @return
	 */
	public List<Content> getAllContent(Long userId) {
		return HContentDAO.getInstance().findAllContent(userId);

	}

	/**
	 * Share a content with another user.
	 * 
	 * @param contentShare
	 * @return
	 */
	public ContentShare shareContent(ContentShare contentShare) {
		return HContentDAO.getInstance().shareContent(contentShare);
	}
	
	
	/**
	 * Remove content.
	 * 
	 * @param contentId
	 *            content id
	 */
	public void deleteContent(long contentId) {
		HContentDAO.getInstance().deleteContent(contentId);
	}

}
