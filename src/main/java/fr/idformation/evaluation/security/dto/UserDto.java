package fr.idformation.evaluation.security.dto;

import fr.idformation.evaluation.security.models.User;

public class UserDto {

	private String username;

	private String role;

	public UserDto(User user) {
		this.username = user.getUsername();
		this.role = user.getRoles().iterator().next().getName().name();

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
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

}
