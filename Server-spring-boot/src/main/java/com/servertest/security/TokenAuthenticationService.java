package com.servertest.security;

import java.util.Collections;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * The Token Authentication Service.
 *
 */
public class TokenAuthenticationService {

	private static final long EXPIRATION_TIME = 864_000_000; // 10 days
	private static final String SECRET = "@PIFromR3s3@rshL@b1s";
	private static final String TOKEN_PREFIX = "Bearer";
	private static final String HEADER_STRING = "Authorization";

	protected static void addAuthentication(HttpServletResponse response, String username) {
		final String JWT = Jwts.builder().setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SECRET).compact();

		response.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
	}

	protected static Authentication getAuthentication(HttpServletRequest request) {
		final String token = request.getHeader(HEADER_STRING);

		// parse the token
		if (null != token) {
			String user = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token.replace(TOKEN_PREFIX, "")).getBody()
					.getSubject();

			return user != null ? new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList()) : null;
		}

		return null;
	}
}
