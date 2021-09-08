/**
 * 
 */
package com.urbanisation_si.cours.ams.demomssecurite.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Patrice
 *
 */
@Configuration
public class PagesConfiguration implements WebMvcConfigurer {
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
	    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	    return bCryptPasswordEncoder;
	}
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
	    registry.addViewController("/accueil").setViewName("accueil");
	    registry.addViewController("/").setViewName("accueil");
	    registry.addViewController("/afficher").setViewName("afficher");
	    registry.addViewController("/connecter").setViewName("connecter");
	    registry.addViewController("/deconnecter").setViewName("deconnecter");
	    registry.addViewController("/afficheruser").setViewName("afficheruser");
	}
}
