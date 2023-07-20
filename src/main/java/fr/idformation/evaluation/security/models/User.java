package fr.idformation.evaluation.security.models;

import java.util.HashSet;
import java.util.Set;

import org.springframework.lang.NonNull;

import fr.idformation.evaluation.ConstantList;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = "username"),
		@UniqueConstraint(columnNames = "email") })
public class User {

	/**
	 * the entity's id.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	/**
	 * the entity's username.
	 */
	@NonNull
	@Size(max = ConstantList.MAX_SIZE)
	@Column(name = "username")
	private String username;

	/**
	 * the entity's email.
	 */
	@NonNull
	@Email
	@Size(max = ConstantList.MAX_SIZE)
	@Column(name = "email")
	private String email;

	/**
	 * the entity's password.
	 */
	@NonNull
	@Size(max = ConstantList.PASSWORD_MAX_SIZE)
	@Column(name = "password")
	private String password;

	/**
	 * the entity's roles.
	 */
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

	/**
	 * Default consturctor.
	 */
	public User() {
	}

	/**
	 * User constructor.
	 *
	 * @param pUsername
	 * @param pEmail
	 * @param pPassword
	 */
	public User(final String pUsername, final String pEmail, final String pPassword) {
		this.username = pUsername;
		this.email = pEmail;
		this.password = pPassword;
	}

	/**
	 *
	 * @return the entity's email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 *
	 * @return the entity's id
	 */
	public Long getId() {
		return id;
	}

	/**
	 *
	 * @return the entity's password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 *
	 * @return the entity's roles
	 */
	public Set<Role> getRoles() {
		return roles;
	}

	/**
	 *
	 * @return the entity's user name
	 */
	public String getUsername() {
		return username;
	}

	/**
	 *
	 * @param pEmail the email to be set
	 */
	public void setEmail(final String pEmail) {
		this.email = pEmail;
	}

	/**
	 *
	 * @param pId the id to be set
	 */
	public void setId(final Long pId) {
		this.id = pId;
	}

	/**
	 *
	 * @param pPassword the password to be set
	 */
	public void setPassword(final String pPassword) {
		this.password = pPassword;
	}

	/**
	 *
	 * @param pRoles the roles to be set
	 */
	public void setRoles(final Set<Role> pRoles) {
		this.roles = pRoles;
	}

	/**
	 *
	 * @param pUsername the username to be set
	 */
	public void setUsername(final String pUsername) {
		this.username = pUsername;
	}
}
