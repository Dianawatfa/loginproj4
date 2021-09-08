/**
 * 
 */
package com.urbanisation_si.cours.ams.demomssecurite.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


import com.urbanisation_si.cours.ams.demomssecurite.service.UserService;

/**
 * @author Patrice
 *
 */
@Configuration
@EnableWebSecurity
public class SecuriteWebConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	ConnexionAuthenticationSuccessHandler connexionAuthenticationSuccessHandler;
	
	@Bean
	public UserDetailsService mongoGestionnairePrevoyanceDetails() {
	    return new UserService();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	    UserDetailsService userDetailsService = mongoGestionnairePrevoyanceDetails();
	    auth
	        .userDetailsService(userDetailsService)
	        .passwordEncoder(bCryptPasswordEncoder);

	}
	
	 @Override
	    protected void configure(HttpSecurity http) 
	      throws Exception {
	        http.csrf().disable()
	          .authorizeRequests()
	          .antMatchers("/login").permitAll()
	          .anyRequest()
	          .authenticated()
	          .and()
	          .httpBasic();
	    }

	@Override
	public void configure(WebSecurity web) throws Exception {
	    web
	        .ignoring()
	        .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
	}
}