package com.m2i.tp.dao;

import org.springframework.data.repository.CrudRepository;

import com.m2i.tp.entity.Client;
import com.m2i.tp.entity.InfoAuth;

public interface DaoInfoAuth extends CrudRepository<InfoAuth, String> {
	//avec findById() , findAll() , save() , deleteById() , ...
	//comme méthode prédéfinie héritées
	
	public Client clientFromUsername(String username);

}
