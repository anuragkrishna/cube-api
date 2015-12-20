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
 * Cube Model Class.
 * 
 * @author anurkris
 *
 */
@NamedQueries({ @NamedQuery(name = "findCubeByUserID", query = "from Cube c where c.userId= :userId"),
		@NamedQuery(name = "findCubeByID", query = "from Cube c where c.id= :id"),
		@NamedQuery(name = "findCubeByIDs", query = "from Cube c where c.id in (:ids)"),
		@NamedQuery(name = "deleteCubeByID", query = "delete from Cube c where c.id= :id") })
@XmlRootElement
@Entity
@Table(name = "cube")
@SequenceGenerator(name = "cube_seq", sequenceName = "cube_seq")
public class Cube {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cube_seq")
	@Column(name = "cube_id")
	private long id;

	@Column(name = "user_id")
	private long userId;

	@Column(name = "cube_name")
	private String name;

	public Cube() {

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

}
