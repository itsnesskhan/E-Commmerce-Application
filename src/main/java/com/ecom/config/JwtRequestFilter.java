package com.ecom.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ecom.utills.Constants;

import lombok.extern.log4j.Log4j2;

/**
 * @author SHASHANK
 * @version 1.0
 * @since 2022-05-20
 */

@Component
@Log4j2
public class JwtRequestFilter extends OncePerRequestFilter {
	

	@Autowired
	private MyUserDetailsService myUserDetailsService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {

		final String requestTokenHeader = request.getHeader(Constants.AUTHORIZATION_HEADER_KEY);
		final String refreshTokenHeader = request.getHeader(Constants.AUTHORIZATION_REFERESH_KEY);

		String username = null;
		String jwtToken = null;
		String jwtRefreshToken = null;
		// JWT Token is in the form "Bearer token". Remove Bearer word and get
		// only the Token
		if ((requestTokenHeader != null && requestTokenHeader.startsWith(Constants.TOKEN_PREFIX))
				|| (refreshTokenHeader != null && refreshTokenHeader.startsWith(Constants.TOKEN_PREFIX))) {
			if(requestTokenHeader != null) {
			jwtToken = requestTokenHeader.substring(Constants.TOKEN_PREFIX.length());
			jwtRefreshToken = requestTokenHeader.substring(Constants.TOKEN_PREFIX.length());
			}
			username = jwtTokenUtil.getUsernameFromToken(jwtRefreshToken);
		} else {
			log.warn("JWT Token does not begin with Bearer String");
		}

		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = myUserDetailsService.loadUserByUsername(username);

			// add && userDetails.isCredentialsNonExpired() in below if condition to apply
			// password expired filter in all requests

			if (jwtTokenUtil.validateToken(jwtToken, userDetails)
					&& jwtTokenUtil.validateToken(jwtRefreshToken, userDetails)) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());

				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		chain.doFilter(request, response);
	}
}
