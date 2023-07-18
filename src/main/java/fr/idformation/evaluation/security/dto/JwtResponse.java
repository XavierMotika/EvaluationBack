package fr.idformation.evaluation.security.dto;

import java.util.Date;

public class JwtResponse {

	private String jwt;
	private Date expiration;

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
	 * @param expiration the expiration to set
	 */
	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}

	/**
	 * @param jwt the jwt to set
	 */
	public void setJwt(String jwt) {
		this.jwt = jwt;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(UserDto user) {
		this.user = user;
	}

}
