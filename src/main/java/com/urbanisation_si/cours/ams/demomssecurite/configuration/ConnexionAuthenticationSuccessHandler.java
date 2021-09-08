/**
 * 
 */
package com.urbanisation_si.cours.ams.demomssecurite.configuration;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * @author Patrice
 *
 */
@Component
public class ConnexionAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
	        HttpServletResponse response, Authentication authentication)
	        throws IOException, ServletException {
	    // initialise la réponse avec le status OK
	    response.setStatus(HttpServletResponse.SC_OK);

	    for (GrantedAuthority auth : authentication.getAuthorities()) {
	        if ("ADMIN".equals(auth.getAuthority())) {
	            response.sendRedirect("/afficher");
	        }else response.sendRedirect("/afficheruser");
	    }
	}
}
