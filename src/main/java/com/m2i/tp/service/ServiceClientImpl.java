package com.m2i.tp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.m2i.tp.dao.DaoClient;
import com.m2i.tp.dao.DaoCompte;
import com.m2i.tp.dao.DaoInfoAuth;
import com.m2i.tp.entity.Client;
import com.m2i.tp.entity.Compte;
import com.m2i.tp.entity.InfoAuth;


@Service //héritant de @Component
@Transactional
public class ServiceClientImpl implements ServiceClient {
	
	
	@Autowired
	private DaoClient daoClient; //dao vers lequel déléguer
	
	@Autowired
	private DaoInfoAuth daoInfoAuth; //dao vers lequel déléguer
	
	@Autowired
	private DaoCompte daoCompte; //dao vers lequel déléguer
	

	@Override
	public Client rechercherClientParNumero(Long numero) {
		return daoClient.findById(numero).orElse(null);
	}

	@Override
	public void saveOrUpdateClient(Client cpt) {
			daoClient.save(cpt);
	}

	@Override
	public void supprimerClient(Long numClient) {
		daoClient.deleteById(numClient);
	}

	@Override
	public List<Compte> rechercherComptesDuClient(Long numClient) {
		return daoClient.findComptesOfClient(numClient);
	}

	@Override
	public void ajouterComptePourClient(Long numClient, Long numCompte) {
		Client client = daoClient.findById(numClient).get();
		Compte compte = daoCompte.findById(numCompte).get();
		// client.getComptes().add(compte); //ne suffit pas car lien du coté secondaire ("mappedBy")
		compte.getProprietaires().add(client);
		//save automatique à l'état persistant (contexte @Transactional)
	}

	@Override
	public Client clientFromVerifInfoAuth(String username, String password) {
		//v1 à peaufiner
		InfoAuth infoAuth = daoInfoAuth.findById(username).orElse(null);
		if(infoAuth==null) {
		       return null;
		}
		if(! infoAuth.getPassword().equals(password)) {
			return null;
		}
		infoAuth.getClient().getNumero();//for no lazy exception ?
		return infoAuth.getClient();
	}

	@Override
	public void setInfoAuth(Long numClient, String username, String password) {
		//v1 à peaufiner
		Client client = daoClient.findById(numClient).get();
		InfoAuth infoAuth = new InfoAuth(username,password);
		daoInfoAuth.save(infoAuth);
		infoAuth.setClient(client);
		daoInfoAuth.save(infoAuth);
	}

	
}
