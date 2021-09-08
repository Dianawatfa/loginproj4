/**
 * 
 */
package com.urbanisation_si.cours.ams.demomssecurite.dao;


import org.springframework.data.repository.CrudRepository;

import com.urbanisation_si.cours.ams.demomssecurite.modele.User;

/**
 * @author Patrice
 *
 */
public interface UserRepository extends CrudRepository<User, Integer> {

	User findByUsername(String mail);
    User findByUsernameAndPassword( String mail, String mdp);

}