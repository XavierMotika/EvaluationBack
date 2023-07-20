package fr.idformation.evaluation.security.dto;

import fr.idformation.evaluation.security.models.User;

public class UserDto {

	/**
	 * the user name.
	 */
	private String username;

	/**
	 * the user role.
	 */
	private String role;

	/**
	 * The UserDTO construction.
	 *
	 * @param pUser the user to be converted to a DTO
	 */
	public UserDto(final User pUser) {
		this.username = pUser.getUsername();
		this.role = pUser.getRoles().iterator().next().getName().name();

	}

	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param pRole the role to set
	 */
	public void setRole(final String pRole) {
		this.role = pRole;
	}

	/**
	 * @param pUsername the username to set
	 */
	public void setUsername(final String pUsername) {
		this.username = pUsername;
	}

}
