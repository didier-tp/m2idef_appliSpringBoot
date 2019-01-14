package com.m2i.tp.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.m2i.tp.dao.DaoCompte;
import com.m2i.tp.entity.Compte;

@Service //héritant de @Component
@Transactional
public class ServiceCompteImpl implements ServiceCompte {
	
	//NB: daoCompte pourra référencer une instance 
	//de type DaoCompteSimu ou bien DaoCompteJpa
	
	public ServiceCompteImpl() {
		System.out.println("dans constructeur daoCompte="+daoCompte);
	}
	
	@PostConstruct
	public void init() {
		System.out.println("dans init prefixé par @PostConstruct daoCompte="
	                       +daoCompte);
	}
	
	private DaoCompte daoCompte; //dao vers lequel déléguer
	
	//méthode d'injection de dépendance
	@Autowired //ici ou bien directement au dessus du private
	//@Autowired de Spring ressemble à @EJB ou @Inject de DI/CDI
	//et demande à injecter un composant spring existant compatible avec l'interface DaoCompte
	public void setDaoCompte(DaoCompte daoCompte) {
		this.daoCompte = daoCompte;
	}

	@Override
	public Compte rechercherCompteParNumero(Long numero) {
		return daoCompte.findCompteByNumero(numero);
	}

	@Override
	public void saveOrUpdateCompte(Compte cpt) {
		if(cpt.getNumero()==null) {
			daoCompte.createCompte(cpt);
		}else 
			daoCompte.updateCompte(cpt);
	}

	@Override
	//avec ou sans @Transactional ici ou au dessus de la classe entière ServiceCompteImpl
	//à tester avec un numéro de compte à créditer qui existe ou n'existe pas
	public void transferer(Double montant, Long numCptDeb, Long numCptCred) {
		Compte cptDeb = daoCompte.findCompteByNumero(numCptDeb);
		cptDeb.setSolde(cptDeb.getSolde() - montant);
		//daoCompte.updateCompte(cptDeb); //uniquement nécessaire en mode non @Transactional 
		                                //non persistant
		Compte cptCred = daoCompte.findCompteByNumero(numCptCred);
		cptCred.setSolde(cptCred.getSolde() + montant);
		//daoCompte.updateCompte(cptCred);
		
		//En mode @Transactional , le entityManager et la transaction 
		//ne sont finalisés (commit/rollback) et close qu'en fin de méthode
		//Les objet cptDeb et cptCred remontés par le dao sont alors 
		//dans un état "persistant" et pas "détaché"
		//A la fin de l'exécution de la méthode améliorée par spring ,
		//le commit() déclenche entityManager.flush() qui déclenche automatiquement
		// .merge() sur toutes les entités persistantes modifiées en mémoire
	}

}
