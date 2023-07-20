package fr.idformation.evaluation.security.jwt;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

	/**
	 * The token header can be found in application.properties.
	 */
	@Value("${app.jwtTokenHeader}")
	private String tokenHeader;

	/**
	 * The token provider.
	 */
	@Autowired
	private JwtProvider tokenProvider;

	/**
	 * The service for UserDetails.
	 */
	@Autowired
	private UserDetailsService userDetailsService;

	/**
	 * If a JWT exists, it is extracted from the user, details are loaded, an
	 * authentication is created and is stored.
	 */
	@Override
	protected void doFilterInternal(final HttpServletRequest pRequest, final HttpServletResponse pResponse,
			final FilterChain pFilterChain) throws ServletException, IOException {
		try {
			String jwt = getJwtFromRequest(pRequest);
			if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
				String username = tokenProvider.getUserUsernameFromJWT(jwt);
				UserDetails userDetails = userDetailsService.loadUserByUsername(username);
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(pRequest));
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		} catch (Exception ex) {
			logger.error("Could not set user authentication in security context", ex);
		}
		pFilterChain.doFilter(pRequest, pResponse);
	}

	/**
	 * the JWT must be in the request header, it has to be an authorization one and
	 * has to begin with the defined token header.
	 *
	 * @param pRequest
	 * @return the bearer token if request successfull or null if not
	 */
	private String getJwtFromRequest(final HttpServletRequest pRequest) {
		String bearerToken = pRequest.getHeader("Authorization");
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(tokenHeader)) {
			return bearerToken.substring(tokenHeader.length() + 1, bearerToken.length());
		}
		return null;
	}
}
