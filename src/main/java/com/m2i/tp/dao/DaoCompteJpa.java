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
	public Compte findCompteByNumero(Long numero) {
		return entityManager.find(Compte.class, numero);
	}

	@Override
	public List<Compte> findAllComptes() {
		return entityManager.createQuery("SELECT c FROM Compte c",Compte.class)
			                .getResultList();
	}

	@Override
	public void updateCompte(Compte cpt) {
		entityManager.merge(cpt);
	}

	@Override
	public void createCompte(Compte cpt) {
		entityManager.persist(cpt);
	}

	@Override
	public void deleteCompte(Long numero) {
		Compte cpt = entityManager.find(Compte.class, numero);
        entityManager.remove(cpt);
	}

}
