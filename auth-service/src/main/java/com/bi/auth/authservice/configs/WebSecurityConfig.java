package com.bi.auth.authservice.configs;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.bi.auth.authservice.securities.JwtTokenAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsService userDetailsService;

	@Override
	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
		auth//
				.userDetailsService(userDetailsService)//
				.passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http //
				.httpBasic().disable()//
				.cors().and().csrf().disable() //
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //
				.and() //
				.exceptionHandling()
				.authenticationEntryPoint((req, resp, e) -> resp.sendError(HttpServletResponse.SC_UNAUTHORIZED)) //
				.and() //
				.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
				.authorizeRequests()//
				.antMatchers(HttpMethod.POST, "/auth/signin").permitAll() //
				.antMatchers(HttpMethod.POST, "/auth/signup").permitAll() //
				.antMatchers(HttpMethod.POST, "/auth/facebook/signin").permitAll() //
				.antMatchers(HttpMethod.POST, "/auth/google/signin").permitAll() //
				.antMatchers(HttpMethod.GET, "/").permitAll() //
				.antMatchers("/h2/**").permitAll() // TODO remove
				.antMatchers("/**.js").permitAll() // TODO remove
				.antMatchers("/**.css").permitAll() // TODO remove
				.antMatchers("/**.ico").permitAll() // TODO remove
				.antMatchers("/**.jpg").permitAll() // TODO remove
				.antMatchers("/**.png").permitAll() // TODO remove
				.anyRequest().authenticated();
		http.headers().frameOptions().disable();
	}

	@Bean
	public JwtTokenAuthenticationFilter jwtAuthenticationFilter() {
		return new JwtTokenAuthenticationFilter();
	}

	@Bean(BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}