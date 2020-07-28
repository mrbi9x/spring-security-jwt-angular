package com.bi.auth.authservice.securities;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.bi.auth.authservice.dtos.ObjectMapperUtils;
import com.bi.auth.authservice.dtos.UserDetailsPrincipal;
import com.bi.auth.authservice.entities.UserEntity;
import com.bi.auth.authservice.services.JwtTokenProvider;
import com.bi.auth.authservice.services.UserService;

import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtTokenAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private JwtConfig jwtConfig;
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	@Autowired
	private UserService userService;

	@Override
	protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response,
			final FilterChain filterChain) throws ServletException, IOException {
		try {
			final String jwtToken = this.getJwtTokenFromRequest(request);
			if (StringUtils.hasText(jwtToken) && jwtTokenProvider.validateJwtToken(jwtToken)) {
				Claims claims = jwtTokenProvider.getClaimsFromJWT(jwtToken);
				Long id = Long.parseLong(claims.getSubject());
				Optional<UserEntity> userEntity = this.userService.getUserById(id);
				if (userEntity.isPresent()) {
					UserDetailsPrincipal userDetailsPrincipal = ObjectMapperUtils.getInstance()
							.mapToUserDetailsPrincipal(userEntity.get());
					UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
							userDetailsPrincipal, null, userDetailsPrincipal.getAuthorities());
					authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authenticationToken);
				}
			}
		} catch (Exception e) {
			log.error("Could not set user authentication in security context ", e.getMessage());
		}
		filterChain.doFilter(request, response);
	}

	private String getJwtTokenFromRequest(final HttpServletRequest request) {
		final String bearerToken = request.getHeader(jwtConfig.getHeader());
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(jwtConfig.getPrefix())) {
			return bearerToken.replace(jwtConfig.getPrefix(), "").trim();
		}
		return null;
	}

}