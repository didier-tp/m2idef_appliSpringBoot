package com.m2i.tp.dao;

import org.springframework.data.repository.CrudRepository;

import com.m2i.tp.entity.Operation;

public interface DaoOperation extends CrudRepository<Operation, Long> {
	//avec findById() , findAll() , save() , deleteById() , ...
	//comme méthode prédéfinie héritées
}
