package com.fr.adaming.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fr.adaming.entity.Bien;

@Repository
public interface BienRepository extends JpaRepository<Bien, Long> {

}
