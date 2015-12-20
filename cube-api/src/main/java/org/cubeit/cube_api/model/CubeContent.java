package org.cubeit.cube_api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@NamedQueries({
		@NamedQuery(name = "deleteCubeContentByCubeID", query = "delete from CubeContent where cube_Id= :cube_Id"),
		@NamedQuery(name = "deleteCubeContentByContentID", query = "delete from CubeContent where content_Id= :content_Id"),
		@NamedQuery(name = "findCubeContentByCubeContentIDs", query = "from CubeContent where cube_Id= :cubeId and content_Id= :contentId"),
		@NamedQuery(name = "findCubeContentIDsByCubeIDs", query = "select content_Id from CubeContent where cube_Id in (:ids)") })

@XmlRootElement
@Entity
@Table(name = "cube_content")
@SequenceGenerator(name = "cube_cont_seq", sequenceName = "cube_cont_seq")
public class CubeContent {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cube_cont_seq")
	private long id;

	@Column(name = "cube_id")
	private long cube_Id;

	@Column(name = "content_id")
	private long content_Id;

	public CubeContent() {

	}

	public CubeContent(long cubeId, long contentId) {
		super();
		this.cube_Id = cubeId;
		this.content_Id = contentId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getCube_Id() {
		return cube_Id;
	}

	public void setCube_Id(long cube_Id) {
		this.cube_Id = cube_Id;
	}

	public long getContent_Id() {
		return content_Id;
	}

	public void setContent_Id(long content_Id) {
		this.content_Id = content_Id;
	}

}
