/**
 * 
 */
package com.urbanisation_si.cours.ams.demomssecurite.controleur;

import java.security.Principal;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


import com.urbanisation_si.cours.ams.demomssecurite.modele.User;
import com.urbanisation_si.cours.ams.demomssecurite.service.UserService;

/**
 * @author Patrice
 *
 */
@RestController
@CrossOrigin
public class ConnexionControleur {
	@Autowired
	private UserService userService;

	@RequestMapping("/login")
    public boolean login(@RequestBody User user) {
		
		System.out.println(user.toString());
		User userFinded=userService.findUserByMailandpass(user.getUsername(), user.getPassword());
		
		
        return userFinded!=null;
		
    }
	
    @RequestMapping("/user")
    public Principal user(HttpServletRequest request) {
        String authToken = request.getHeader("Authorization")
          .substring("Basic".length()).trim();
        return () ->  new String(Base64.getDecoder()
          .decode(authToken)).split(":")[0];
    }
    
    
	
}
