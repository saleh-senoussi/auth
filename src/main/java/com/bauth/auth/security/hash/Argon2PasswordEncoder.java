package com.bauth.auth.security.hash;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

@Service
public class Argon2PasswordEncoder implements PasswordEncoder {

	private static final Argon2 ARGON2 = 
			Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
	
	private static final int ITERATIONS = 16;
	private static final int MEMORY = 524288;
	private static final int PARALLELISM = 4;
	
	@Override
	public String encode(final CharSequence rawPassword) {
		final String hash = ARGON2.hash(ITERATIONS, MEMORY, PARALLELISM, rawPassword.toString().toCharArray());
		return hash;
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		return ARGON2.verify(encodedPassword, rawPassword.toString().toCharArray());
	}

}
