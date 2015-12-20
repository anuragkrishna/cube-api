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

import org.cubeit.cube_api.model.Content;
import org.cubeit.cube_api.model.ContentShare;
import org.cubeit.cube_api.service.ContentService;

/**
 * Content Resource Controller.
 * 
 * @author anurkris
 *
 */
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ContentController {
	private ContentService contentService = null;

	/**
	 * Get Content service object.
	 * 
	 */
	private ContentService getContentService() {
		if (contentService != null) {
			return contentService;
		}
		contentService = new ContentService();
		return contentService;
	}

	/**
	 * Add content.
	 * 
	 * @param userId
	 * @param content
	 * @return
	 */
	@POST
	@Path("/")
	public Response addContent(@PathParam("userId") Long userId, Content content) {

		Content addedContent = getContentService().addContent(userId, content);
		Response response = Response.status(Status.CREATED).entity(addedContent).build();
		return response;

	}

	/**
	 * Get all content.
	 * 
	 * @param userId
	 * @return
	 */
	@GET
	@Path("/")
	public Response getAllContent(@PathParam("userId") Long userId) {

		List<Content> contents = getContentService().getAllContent(userId);
		GenericEntity<List<Content>> list = new GenericEntity<List<Content>>(contents) {
		};
		Response response = Response.status(Status.FOUND).entity(list).build();
		return response;

	}

	/**
	 * Share a content.
	 * 
	 * @param content_id
	 * @param contentShare
	 * @return
	 */
	@POST
	@Path("/{content_Id}/share")
	public Response shareContent(@PathParam("content_Id") Long content_id, ContentShare contentShare) {

		contentShare.setContent_id(content_id);
		ContentShare updatedCubeShare = getContentService().shareContent(contentShare);
		Response response = Response.status(Status.OK).entity(updatedCubeShare).build();
		return response;

	}
	
	
	/**
	 * Delete a content.
	 * @param content_Id
	 * @return
	 */
	@DELETE
	@Path("/{content_Id}")
	public Response deleteContent(@PathParam("content_Id") Long content_Id) {

		getContentService().deleteContent(content_Id);
		Response response = Response.status(Status.OK).build();
		return response;

	}
}
