package com.m2i.tp.service;

import com.m2i.tp.entity.Compte;

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
		//...
		public void saveOrUpdateCompte(Compte cpt);
		//public boolean verifierDecouvertAutorisé();
		public void transferer(Double montant,Long numCptDeb,Long numCptCred);
}
