package com.bauth.auth.security.jwt.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.annotation.PostConstruct;
import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtTokenUtil implements Serializable {

	private static final long serialVersionUID = -8635856387861723518L;
	
	public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

	@Value("${jwt.token.pathKeyStore}")
	private String keyStorePath;
	@Value("${jwt.token.typeKey}")
	private String keyType;
	@Value("${jwt.token.keyStorePassword}")
	private String keyStorePassword;;
	@Value("${jwt.token.aliasKey}")
	private String jwtKeyAlias;
	@Value("${jwt.token.keyPassword}")
	private String jwtKeyPassword;

	private SecretKey jwtSecretKey;

	private String secret = "this.jwtSecretKey";

	/*@PostConstruct
	public void initialize() {
		try {
			/*KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
			InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(pathKeyStore);
			keyStore.load(resourceAsStream, keyStorePassword.toCharArray());
			//return keyStore;
			KeyStore keyStore = KeyStore.getInstance(this.keyType);
			InputStream keyInputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(keyStorePath);
			keyStore.load(keyInputStream, 
							this.keyStorePassword.toCharArray());
			if (keyStore.containsAlias(this.jwtKeyAlias)) {
				this.jwtSecretKey = (SecretKey) keyStore.getKey(this.jwtKeyAlias, this.jwtKeyPassword.toCharArray());
			} else {
				throw new IllegalStateException("Invalid keyStore");
			}
		} catch (Exception e) {
			throw new IllegalStateException(e.getMessage());
		}
	}
/*
	@Bean
	public KeyStore keyStore() {
		try {
			KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
			InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(keyStorePath);
			keyStore.load(resourceAsStream, keyStorePassword.toCharArray());
			return keyStore;
		} catch (IOException | CertificateException | NoSuchAlgorithmException | KeyStoreException e) {
			throw new IllegalArgumentException("Unable to load keystore: " + keyStorePath);
		}
	}

	@Bean
	public RSAPrivateKey jwtSigningKey(KeyStore keyStore) {
		try {
			Key key = keyStore.getKey(jwtKeyAlias, jwtKeyPassword.toCharArray());
			if (key instanceof RSAPrivateKey) {
				return (RSAPrivateKey) key;
			}
		} catch (UnrecoverableKeyException | NoSuchAlgorithmException | KeyStoreException e) {
			throw new IllegalArgumentException("Unable to load private key from keystore: " + keyStorePath);
		}
		
		throw new IllegalArgumentException("Unable to load private key");
	}

	@Bean
	public RSAPublicKey jwtValidationKey(KeyStore keyStore) {
		try {
			Certificate certificate = keyStore.getCertificate(jwtKeyAlias);
			PublicKey publicKey = certificate.getPublicKey();
			
			if (publicKey instanceof RSAPublicKey) {
				return (RSAPublicKey) publicKey;
			}
		} catch (KeyStoreException e) {
			throw new IllegalArgumentException("Unable to load private key from keystore: " + keyStorePath);
			//log.error("Unable to load private key from keystore: {}", keyStorePath, e);
		}
		
		throw new IllegalArgumentException("Unable to load RSA public key");
	}*/

	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}

	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		return doGenerateToken(claims, userDetails.getUsername());
	}

	private String doGenerateToken(Map<String, Object> claims, String subject) {
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = getUsernameFromToken(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	public Boolean isCurrentUser(String username, String token) {
		String jwtToken = token.substring(7);
		return getUsernameFromToken(jwtToken).equals(username);
	}
}
