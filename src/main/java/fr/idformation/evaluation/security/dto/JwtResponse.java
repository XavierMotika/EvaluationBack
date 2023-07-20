package fr.idformation.evaluation.security.dto;

import java.util.Date;

public class JwtResponse {

	/**
	 * the jwt.
	 */
	private String jwt;

	/**
	 * the expiration date.
	 */
	private Date expiration;

	/**
	 * the user's DTO.
	 */
	private UserDto user;

	/**
	 *
	 */
	public JwtResponse() {
		super();
	}

	/**
	 *
	 * @param pJwt        the jwt token
	 * @param pExpiration the expiration date of the jwt
	 * @param pUser       the logged user
	 */
	public JwtResponse(final String pJwt, final Date pExpiration, final UserDto pUser) {
		super();
		this.jwt = pJwt;
		this.expiration = pExpiration;
		this.user = pUser;
	}

	/**
	 * @return the expiration
	 */
	public Date getExpiration() {
		return expiration;
	}

	/**
	 * @return the jwt
	 */
	public String getJwt() {
		return jwt;
	}

	/**
	 * @return the user
	 */
	public UserDto getUser() {
		return user;
	}

	/**
	 * @param pExpiration the expiration to set
	 */
	public void setExpiration(final Date pExpiration) {
		this.expiration = pExpiration;
	}

	/**
	 * @param pJwt the jwt to set
	 */
	public void setJwt(final String pJwt) {
		this.jwt = pJwt;
	}

	/**
	 * @param pUser the user to set
	 */
	public void setUser(final UserDto pUser) {
		this.user = pUser;
	}

}
