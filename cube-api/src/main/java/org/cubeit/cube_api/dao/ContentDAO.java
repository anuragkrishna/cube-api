package org.cubeit.cube_api.dao;

import java.util.List;

import org.cubeit.cube_api.model.Content;
import org.cubeit.cube_api.model.ContentShare;

public interface ContentDAO {

	public Content insertContent(Content content);

	public Content findContent(long userId, long contentId);

	public List<Content> findAllContent(long userId);

	public ContentShare shareContent(ContentShare cubeShare);

	public void deleteContent(long contentId);

}
