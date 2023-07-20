package fr.idformation.evaluation.security.jwt;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import fr.idformation.evaluation.security.utils.UserPrincipal;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtProvider {
	/**
	 * the secret key, can be found in the application.porperties.
	 */
	@Value("${app.jwtSecretKey}")
	private String secret;

	/**
	 * the expiration delay, can be found in the application.porperties.
	 */
	@Value("${app.jwtExpirationInMs}")
	private int jwtExpirationInMs;

	/**
	 * The key.
	 */
	private Key key;

	// TODO J'ai changé ça en private, c'était un protected à la base, voir si ça
	// fout pas la merdre ailleurs.
	/**
	 *
	 */
	private final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * Create a JWT using data from the user and the token's life time (expiration
	 * delay).
	 *
	 * @param pAuthentication
	 * @return a newly created JWT
	 */
	public String generateToken(final Authentication pAuthentication) {
		UserPrincipal userPrincipal = (UserPrincipal) pAuthentication.getPrincipal();
		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

		return Jwts.builder().setSubject(userPrincipal.getUsername()).setIssuedAt(new Date()).setExpiration(expiryDate)
				.signWith(getSigningKey(), SignatureAlgorithm.HS512).compact();

	}

	/**
	 * Get the expiration date from the JWT.
	 *
	 * @param pToken
	 * @return the expiration Date.
	 */
	public Date getExpiryDate(final String pToken) {
		Claims claims = Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(pToken).getBody();
		return claims.getExpiration();
	}

	private Key getSigningKey() {
		if (key == null) {
			byte[] keyBytes = Base64.getUrlDecoder().decode(this.secret);
			key = Keys.hmacShaKeyFor(keyBytes);
		}
		return key;
	}

	/**
	 * Extract user name from the JWT.
	 *
	 * @param pToken the JWT used to extract the name
	 * @return the user name
	 */
	public String getUserUsernameFromJWT(final String pToken) {
		Claims claims = Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(pToken).getBody();
		return claims.getSubject();
	}

	/**
	 * Checks if the JWT is valid or not.
	 *
	 * @param pAuthToken the JWT to be checked
	 * @return true or false
	 */
	public boolean validateToken(final String pAuthToken) {
		try {
			Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(pAuthToken);
			return true;
		} catch (Exception ex) {
			logger.error("Can't validate the token", ex);
		}
		return false;
	}
}
