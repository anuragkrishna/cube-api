package org.cubeit.cube_api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * User Model Class.
 * 
 * @author anurkris
 *
 */
@XmlRootElement
@Entity
@Table(name = "cube_user")
@SequenceGenerator(name = "user_seq", sequenceName = "user_seq")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
	@Column(name = "user_id")
	private long id;

	@Column(name = "user_name")
	private String name;

	@Column(name = "user_city")
	private String city;

	public User() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

}
