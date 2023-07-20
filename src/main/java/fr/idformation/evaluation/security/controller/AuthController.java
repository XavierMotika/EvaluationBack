package fr.idformation.evaluation.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.idformation.evaluation.ConstantList;
import fr.idformation.evaluation.security.dto.JwtResponse;
import fr.idformation.evaluation.security.dto.LoginRequest;
import fr.idformation.evaluation.security.dto.UserDto;
import fr.idformation.evaluation.security.jwt.JwtProvider;
import fr.idformation.evaluation.security.models.User;
import fr.idformation.evaluation.security.service.impl.UserDetailsServiceImpl;
import jakarta.validation.Valid;

/**
 * Classe permettant de gérer la sécurité.
 *
 * @author piot
 *
 */

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = { "http://localhost:3000", "https://test-cd413.web.app/" }, maxAge = ConstantList.MAX_AGE)
public final class AuthController {

	/** token header to use in JWT. */
	@Value("${app.jwtTokenHeader}")
	private String tokenHeader;

	/** import authentication manager. */
	@Autowired
	private AuthenticationManager authenticationManager;

	/** import jwtprovider. */
	@Autowired
	private JwtProvider tokenProvider;

	/** import user service. */
	@Autowired
	private UserDetailsServiceImpl userService;

	/**
	 *
	 * @param request a login + password couple
	 * @return a response with the jwt
	 */
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody final LoginRequest request) {

		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = tokenProvider.generateToken(authentication);

		User user = userService.loadUserDetails(request.getUsername());

		return ResponseEntity
				.ok(new JwtResponse(tokenHeader + " " + jwt, tokenProvider.getExpiryDate(jwt), new UserDto(user)));
	}

}
