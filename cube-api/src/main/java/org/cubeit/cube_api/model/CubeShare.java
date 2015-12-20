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
		@NamedQuery(name = "findCubeShareIDsByUserID", query = "select cube_id from CubeShare where user_Id= :userId"), 
		@NamedQuery(name = "deleteCubeShareByID", query = "delete from CubeShare where cube_id= :cube_Id")
})
@XmlRootElement
@Entity
@Table(name = "cube_share_with")
@SequenceGenerator(name = "cube_share_seq", sequenceName = "cube_share_seq")
public class CubeShare {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cube_share_seq")
	private long id;

	@Column(name = "cube_id")
	private long cube_id;

	@Column(name = "user_id")
	private long user_id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getCube_id() {
		return cube_id;
	}

	public void setCube_id(long cube_id) {
		this.cube_id = cube_id;
	}

	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

}
