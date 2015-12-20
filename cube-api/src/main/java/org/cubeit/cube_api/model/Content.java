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

/**
 * Content model class.
 * 
 * @author anurkris
 *
 */
@NamedQueries({ @NamedQuery(name = "findContentByUserID", query = "from Content c where c.userId= :userId"),
		@NamedQuery(name = "findContentByIDs", query = "from Content c where c.id in (:ids)"),
		@NamedQuery(name = "findContentByID", query = "from Content c where c.id= :id"), 
		@NamedQuery(name = "deleteContentByID", query = "delete from Content c where c.id= :id")
})
@XmlRootElement
@Entity
@Table(name = "content")
@SequenceGenerator(name = "content_seq", sequenceName = "content_seq")
public class Content {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "content_seq")
	private long id;

	@Column(name = "content_link")
	private String link;

	@Column(name = "user_id")
	private long userId;

	public Content() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

}
