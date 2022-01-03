package com.bauth.auth.security.jwt.service;

import java.util.List;
import java.util.stream.Collectors;

import com.bauth.auth.security.jwt.config.JwtTokenUtil;
import com.bauth.auth.security.jwt.model.JwtRequest;
import com.bauth.auth.security.jwt.model.JwtResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JwtService {

	private final AuthenticationManager authenticationManager;
	private final JwtTokenUtil jwtTokenUtil;
	private final JwtUserDetailsService userDetailsService;

	public JwtResponse createAuthenticationToken(JwtRequest authenticationRequest) {
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());

		String token = this.jwtTokenUtil.generateToken(userDetails);
		List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.collect(Collectors.toList());

		return new JwtResponse(token, userDetails.getUsername(), roles);
	}

	private void authenticate(String username, String password) {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new IllegalStateException("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new IllegalStateException("INVALID_CREDENTIALS", e);
		}
	}
}
