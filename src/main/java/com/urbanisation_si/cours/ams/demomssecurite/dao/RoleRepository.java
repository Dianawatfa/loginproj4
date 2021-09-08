/**
 * 
 */
package com.urbanisation_si.cours.ams.demomssecurite.dao;



import org.springframework.data.repository.CrudRepository;

import com.urbanisation_si.cours.ams.demomssecurite.modele.Role;

/**
 * @author Patrice
 *
 */
public interface RoleRepository extends CrudRepository<Role, Integer> {

	Role findByRole(String role);
}