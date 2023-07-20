package fr.idformation.evaluation.security.utils;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public final class UserPrincipal implements UserDetails {

	/**
	 *
	 */
	private static final long serialVersionUID = 928073442559839991L;

	/**
	 *
	 */
	private String username;

	/**
	 *
	 */
	private String password;

	/**
	 *
	 */
	private boolean enabled;

	/**
	 *
	 */
	private Collection<? extends GrantedAuthority> authorities;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 *
	 * @param pAuthorities the authority to be set
	 */
	public void setAuthorities(final Collection<? extends GrantedAuthority> pAuthorities) {
		this.authorities = pAuthorities;
	}

	/**
	 *
	 * @param pEnabled the boolean to be set
	 */
	public void setEnabled(final boolean pEnabled) {
		this.enabled = pEnabled;
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
	 * @param pUsername the username to be set
	 */
	public void setUsername(final String pUsername) {
		this.username = pUsername;
	}
}
