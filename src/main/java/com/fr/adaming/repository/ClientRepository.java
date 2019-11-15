package com.fr.adaming.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fr.adaming.entity.Client;

/**
 * @author Brias Guillaume
 *
 */
@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

	public Client findByEmail(String email);
	
	@Transactional
	@Modifying
	@Query(value = "update client set id_agent = :idA where id = :idC", nativeQuery = true)
	public void addAgent(@Param(value = "idC") Integer idC, @Param(value = "idA") Integer idA);
}
