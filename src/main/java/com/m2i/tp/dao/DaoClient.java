package com.m2i.tp.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.m2i.tp.entity.Client;
import com.m2i.tp.entity.Compte;

public interface DaoClient extends CrudRepository<Client, Long> {
	//avec findById() , findAll() , save() , deleteById() , ...
	//comme méthode prédéfinie héritées

	public List<Compte> findComptesOfClient(Long numClient);
}
