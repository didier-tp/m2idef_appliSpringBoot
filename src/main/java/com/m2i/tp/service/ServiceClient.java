package com.m2i.tp.service;

import java.util.List;

import com.m2i.tp.entity.Client;
import com.m2i.tp.entity.Compte;

/**
 * 
 * Interface du "Business Service" (Service métier) "ServiceClient"
 * comportant:
 *    - des méthodes CRUD déléguées au DAO
 *    - des méthodes associées à des règles de gestion (verif...)
 *    - des méthodes avec comportement transactionnel
 *    - des méthodes spécifiques au métier
 */

public interface ServiceClient {
		public Client rechercherClientParNumero(Long numero);
		public List<Compte> rechercherComptesDuClient(Long numClient);
		public void ajouterComptePourClient(Long numClient,Long numCompte);
		public void saveOrUpdateClient(Client cpt);
		public void supprimerClient(Long numClient);
		
		public Client clientFromVerifInfoAuth(String username,String password);
		public void setInfoAuth(Long numClient,String username,String password);
}
