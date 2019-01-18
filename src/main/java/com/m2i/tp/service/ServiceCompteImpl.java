package com.m2i.tp.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.m2i.tp.dao.DaoCompte;
import com.m2i.tp.dao.DaoOperation;
import com.m2i.tp.entity.Compte;
import com.m2i.tp.entity.Operation;

@Service //héritant de @Component
@Transactional
public class ServiceCompteImpl implements ServiceCompte {
	
	//NB: daoCompte pourra référencer une instance 
	//de type DaoCompteSimu ou bien DaoCompteJpa
	
	public ServiceCompteImpl() {
		//System.out.println("dans constructeur daoCompte="+daoCompte);
	}
	
	@PostConstruct
	public void init() {
		//System.out.println("dans init prefixé par @PostConstruct daoCompte=" +daoCompte);
	}
	
	private DaoCompte daoCompte; //dao vers lequel déléguer
	
	//méthode d'injection de dépendance
	@Autowired //ici ou bien directement au dessus du private
	//@Autowired de Spring ressemble à @EJB ou @Inject de DI/CDI
	//et demande à injecter un composant spring existant compatible avec l'interface DaoCompte
	public void setDaoCompte(DaoCompte daoCompte) {
		this.daoCompte = daoCompte;
	}
	
	@Autowired
	private DaoOperation daoOperation;

	@Override
	public Compte rechercherCompteParNumero(Long numero) {
		return daoCompte.findById(numero).orElse(null);
	}

	@Override
	public void saveOrUpdateCompte(Compte cpt) {
			daoCompte.save(cpt);
	}

	@Override
	//avec ou sans @Transactional ici ou au dessus de la classe entière ServiceCompteImpl
	//à tester avec un numéro de compte à créditer qui existe ou n'existe pas
	public void transferer(Double montant, Long numCptDeb, Long numCptCred) {
		Compte cptDeb = daoCompte.findById(numCptDeb).get();
		cptDeb.debiter(montant);//ou bien cptDeb.setSolde(cptDeb.getSolde() - montant);
		cptDeb.addOperation(new Operation("debit / virement ",-montant)); //mémoriser débit du virement
		//daoCompte.save(cptDeb); //uniquement nécessaire en mode non @Transactional (non persistant)
		
		Compte cptCred = daoCompte.findById(numCptCred).get();
		cptCred.crediter(montant);//ou bien cptCred.setSolde(cptCred.getSolde() + montant);
		cptCred.addOperation(new Operation("credit / virement ",+montant)); //mémoriser débit du virement
		//daoCompte.save(cptCred);
		
		//En mode @Transactional , le entityManager et la transaction 
		//ne sont finalisés (commit/rollback) et close qu'en fin de méthode
		//Les objet cptDeb et cptCred remontés par le dao sont alors 
		//dans un état "persistant" et pas "détaché"
		//A la fin de l'exécution de la méthode améliorée par spring ,
		//le commit() déclenche entityManager.flush() qui déclenche automatiquement
		// .merge() sur toutes les entités persistantes modifiées en mémoire
	}

	@Override
	public List<Operation> operationsDuCompte(Long numCpt) {
		return daoCompte.findOperationsOfCompte(numCpt);
	}

	@Override
	public void addOperation(Long numCompte, Operation op) {
		//daoOperation.save(op); //facultatif ici si cascade entre compte et operations
		Compte cpt = daoCompte.findById(numCompte).get();
		cpt.addOperation(op);//déclenchant 	cpt.getOperations().add(op);
		//daoCompte.save(cpt); automatique ici à l'état persistant dans contexte @Transactional
	}
	
	@Override
	public List<Compte> rechercherTousLesComptes() {
		return (List<Compte>) daoCompte.findAll();
	}

	@Override
	public List<Compte> rechercherTousLesComptesPositifs() {
		return daoCompte.findComptesAvecSoldePositif();
	}

}
