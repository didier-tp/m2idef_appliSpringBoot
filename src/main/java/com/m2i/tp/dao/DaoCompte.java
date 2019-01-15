package com.m2i.tp.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.m2i.tp.entity.Compte;

//NB: avec Spring-Data , plus besoin de programmer l'implémentation jpa
//cette classe est générée automatiquement si 
//spring.data.jpa.repositories.enabled=true dans application.properties

public interface DaoCompte extends CrudRepository<Compte, Long> {
	//avec findById() , findAll() , save() , deleteById() , ...
	//comme méthode prédéfinie héritées
	
	public List<Compte> findByLabel(String label); //via des conventions de nom de méthode
	                                               //le code de la requete va etre generé automatiquement
	
	//exemple de nom de méthode nom associée au conventions pour la génération automatique
	//la requête devra être codée dans @NamedQuery de nom ="Compte.findComptesAvecSoldePositif"
	public List<Compte> findComptesAvecSoldePositif();
	
}
/*
public interface DaoCompte {
	public Optional<Compte> findById(Long numero);

	public List<Compte> findAll();

	public void save(Compte cpt);

	public void deleteById(Long numero);
}*/
