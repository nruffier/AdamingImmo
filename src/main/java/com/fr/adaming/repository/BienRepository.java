package com.fr.adaming.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fr.adaming.entity.Bien;

@Repository
public interface BienRepository extends JpaRepository<Bien, Long> {
	
	
	@Transactional
	@Modifying
	@Query(value = "update bien set vendu = true where id like :xId", nativeQuery = true)
	public void sellBien(@Param(value = "xId") Long id);	

}
