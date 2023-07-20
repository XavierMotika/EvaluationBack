package fr.idformation.evaluation.security.models;

import fr.idformation.evaluation.ConstantList;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "roles")
public class Role {

	/*
	 * The entity's id.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	/**
	 * The entity's role.
	 */
	@Enumerated(EnumType.STRING)
	@Column(name = "name")
	@Size(max = ConstantList.ROLENAME_MAX_SIZE)
	private RoleName name;

	/**
	 * Role's default constructor.
	 */
	public Role() {
	}

	/**
	 * Role's contructor using enum parameter.
	 *
	 * @param pName
	 */
	public Role(final RoleName pName) {
		this.name = pName;
	}

	/**
	 *
	 * @return the entity's id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 *
	 * @return the entity's role
	 */
	public RoleName getName() {
		return name;
	}

	/**
	 *
	 * @param pId the entity's id
	 */
	public void setId(final Integer pId) {
		this.id = pId;
	}

	/**
	 *
	 * @param pName the entity's role
	 */
	public void setName(final RoleName pName) {
		this.name = pName;
	}
}
