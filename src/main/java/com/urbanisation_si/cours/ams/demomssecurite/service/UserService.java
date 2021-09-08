/**
 * 
 */
package com.urbanisation_si.cours.ams.demomssecurite.service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.urbanisation_si.cours.ams.demomssecurite.dao.RoleRepository;
import com.urbanisation_si.cours.ams.demomssecurite.dao.UserRepository;
import com.urbanisation_si.cours.ams.demomssecurite.modele.Role;
import com.urbanisation_si.cours.ams.demomssecurite.modele.User;

/**
 * @author Patrice
 *
 */
@Service
public class UserService implements UserDetailsService {
	@Autowired
	private UserRepository UserRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	
	
	public User findUserByMailandpass(String mail, String pass) {
		
		return UserRepository.findByUsernameAndPassword(mail,pass);
	}
	
	public User findUserByMail(String mail) {
		return UserRepository.findByUsername(mail);
	}

	public void sauveUser(User user) {
		user.setPassword(encoderMotDePasse(user.getPassword()));
		user.setActive(true);
		Role gestionnairePrevoyanceRole = roleRepository.findByRole("USER");
		user.setRoles(gestionnairePrevoyanceRole);
		UserRepository.save(user);
	}

	private static String encoderMotDePasse(String mdpOriginal) {

		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] encodedhash = digest.digest(mdpOriginal.getBytes(StandardCharsets.UTF_8));
			return bytesEnHex(encodedhash);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Encodage du mot de passe impossible");
		}

	}
	private static String bytesEnHex(byte[] hash) {
		StringBuilder hexString = new StringBuilder(2 * hash.length);
		for (int i = 0; i < hash.length; i++) {
			String hex = Integer.toHexString(0xff & hash[i]);
			if (hex.length() == 1) {
				hexString.append('0');
			}
			hexString.append(hex);
		}
		return hexString.toString();
	}
	@Override
	public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {

		User user  = UserRepository.findByUsername(mail);
		if(user != null) {
			List<GrantedAuthority> autorisations = getUserAutorisation(user.getRoles());
			return autoriserGestionnairePrevoyance(user, autorisations);
		} else {
			throw new UsernameNotFoundException("Non autorisé.");
		}
	}

	private List<GrantedAuthority> getUserAutorisation(Role gestionnairePrevoyanceRoles) {
		Set<GrantedAuthority> roles = new HashSet<>();
		
			roles.add(new SimpleGrantedAuthority(gestionnairePrevoyanceRoles.getRole()));
	
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);
		return grantedAuthorities;
	}

	private UserDetails autoriserGestionnairePrevoyance(User user, List<GrantedAuthority> autorisations) {
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), autorisations);
	}

}
