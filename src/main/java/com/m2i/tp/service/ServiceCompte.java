package com.m2i.tp.service;

import java.util.List;

import com.m2i.tp.entity.Compte;
import com.m2i.tp.entity.Operation;

/**
 * 
 * Interface du "Business Service" (Service métier) "ServiceCompte"
 * comportant:
 *    - des méthodes CRUD déléguées au DAO
 *    - des méthodes associées à des règles de gestion (verif...)
 *    - des méthodes avec comportement transactionnel
 *    - des méthodes spécifiques au métier
 */

public interface ServiceCompte {
		public Compte rechercherCompteParNumero(Long numero);
		public List<Operation> operationsDuCompte(Long numCpt);
		public void addOperation(Long numCompte,Operation op);
		//...
		public void saveOrUpdateCompte(Compte cpt);
		//public boolean verifierDecouvertAutorisé();
		public void transferer(Double montant,Long numCptDeb,Long numCptCred);
		public List<Compte> rechercherTousLesComptes();
		public List<Compte> rechercherTousLesComptesPositifs();
		public void supprimerCompte(Long numCpt);
}
