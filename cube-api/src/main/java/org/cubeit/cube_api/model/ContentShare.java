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
		@NamedQuery(name = "findContentShareIDsByUserID", query = "select content_id from ContentShare where user_Id= :userId"),
		@NamedQuery(name = "deleteContentShareByID", query = "delete from ContentShare where content_id= :content_Id")
})
@XmlRootElement
@Entity
@Table(name = "content_share_with")
@SequenceGenerator(name = "content_share_seq", sequenceName = "content_share_seq")
public class ContentShare {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "content_share_seq")
	private long id;

	@Column(name = "content_id")
	private long content_id;

	@Column(name = "user_id")
	private long user_id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getContent_id() {
		return content_id;
	}

	public void setContent_id(long content_id) {
		this.content_id = content_id;
	}

	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

}
