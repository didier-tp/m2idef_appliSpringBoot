package com.m2i.tp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.m2i.tp.entity.Compte;

@Repository //@Repository = composant spring de type DAO
@Transactional //en version Spring (pour commit/rollback automatique)
public class DaoCompteJpa implements DaoCompte {
	
	@PersistenceContext //annotation standardisée de Java/JEE et JPA
	                    //qui sert à initialiser entityManager
						//en fonction META-INF/persistence.xml
	                    //ou d'une config équivalente spring
	private EntityManager entityManager;

	@Override
	public Compte findByPrimaryKey(Long numero) {
		return entityManager.find(Compte.class, numero);
	}

	@Override
	public List<Compte> findAll() {
		return entityManager.createQuery("SELECT c FROM Compte c",Compte.class)
			                .getResultList();
	}

	@Override
	public void save(Compte cpt) {
		if(cpt.getNumero()==null)
		    entityManager.persist(cpt);
		else 
			entityManager.merge(cpt);
	}

	@Override
	public void delete(Long numero) {
		Compte cpt = entityManager.find(Compte.class, numero);
        entityManager.remove(cpt);
	}

}
