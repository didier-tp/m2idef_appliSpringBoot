package com.m2i.tp.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.m2i.tp.entity.Compte;
import com.m2i.tp.entity.Operation;

@Repository //@Repository = composant spring de type DAO
@Transactional //en version Spring (pour commit/rollback automatique)
public class DaoCompteJpa implements DaoCompte {
	
	@PersistenceContext //annotation standardisée de Java/JEE et JPA
	                    //qui sert à initialiser entityManager
						//en fonction META-INF/persistence.xml
	                    //ou d'une config équivalente spring
	private EntityManager entityManager;

	@Override
	public Optional<Compte> findById(Long numero) {
		Compte cpt  = entityManager.find(Compte.class, numero);
		if(cpt!=null)
			return Optional.of(cpt);
		else
			return Optional.empty();
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
	public void deleteById(Long numero) {
		Compte cpt = entityManager.find(Compte.class, numero);
        entityManager.remove(cpt);
	}

	@Override
	public List<Compte> findByLabel(String label) {
		return entityManager.createQuery("SELECT c FROM Compte c WHERE c.label = :label",Compte.class)
                .setParameter("label", label)
				.getResultList();
	}

	@Override
	public List<Compte> findComptesAvecSoldePositif() {
		return entityManager.createQuery("SELECT c FROM Compte c WHERE c.solde > 0",Compte.class)
                .getResultList();
	}

	@Override
	public List<Operation> findOperationsOfCompte(Long numCpt) {
		return entityManager.createQuery("SELECT o FROM Compte c INNER JOIN c.operations o WHERE c.numero = :numCpt",Operation.class)
				.setParameter("numCpt", numCpt)
				.getResultList();
	}

}
