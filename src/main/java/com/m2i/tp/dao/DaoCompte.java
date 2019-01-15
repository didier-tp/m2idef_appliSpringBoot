package com.m2i.tp.dao;

import org.springframework.data.repository.CrudRepository;

import com.m2i.tp.entity.Compte;

//NB: avec Spring-Data , plus besoin de programmer l'implémentation jpa
//cette classe est générée automatiquement si 
//spring.data.jpa.repositories.enabled=true dans application.properties

public interface DaoCompte extends CrudRepository<Compte, Long> {
	//avec findById() , findAll() , save() , deleteById() , ...
	//comme méthode prédéfinie héritées
}
/*
public interface DaoCompte {
	public Optional<Compte> findById(Long numero);

	public List<Compte> findAll();

	public void save(Compte cpt);

	public void deleteById(Long numero);
}*/
