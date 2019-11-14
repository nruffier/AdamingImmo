package com.fr.adaming.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fr.adaming.entity.Agent;

@Repository
public interface AgentRepository extends JpaRepository<Agent, Integer> {

	public Agent findByEmailAndPwd(String email, String pwd);

	public Agent findByEmail(String email);
}
