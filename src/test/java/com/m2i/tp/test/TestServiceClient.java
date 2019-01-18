package com.m2i.tp.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.m2i.tp.MySpringBootApplication;
import com.m2i.tp.entity.Adresse;
import com.m2i.tp.entity.Client;
import com.m2i.tp.entity.Compte;
import com.m2i.tp.service.ServiceClient;
import com.m2i.tp.service.ServiceCompte;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= {MySpringBootApplication.class})
public class TestServiceClient {
	
	private static Logger logger = LoggerFactory.getLogger(TestServiceClient.class);
	
	@Autowired
	private ServiceClient serviceClient ; //à tester
	@Autowired
	private ServiceCompte serviceCompte ; //annexe pour  tester
	
	@Test 
	public void test_comptes_du_client() {
		Client cli = new Client(null,"Therieur","alain","0102030405" ,"alain.therieur@ici_ou_la.fr");
		   cli.setAdresse(new Adresse("12, rue Elle " , "75000" , "Paris"));
		serviceClient.saveOrUpdateClient(cli);
		
		serviceClient.setInfoAuth(cli.getNumero(), "alain.therieur", "pwd1");
		Client cFromGoodIa = serviceClient.clientFromVerifInfoAuth("alain.therieur", "pwd1");
		Assert.assertTrue(cFromGoodIa!=null  && cFromGoodIa.getNumero() == cli.getNumero());
		Client cFromBadUsername = serviceClient.clientFromVerifInfoAuth("wrong username", "pwd1");
		Assert.assertNull(cFromBadUsername);
		Client cFromBadPwd = serviceClient.clientFromVerifInfoAuth("alain.therieur", "wrong pwd");
		Assert.assertNull(cFromBadPwd);
		
		
		Client autreCli = new Client(null,"Therieur","alain","0102030405" ,"alain.therieur@ici_ou_la.fr");
		  autreCli.setAdresse(new Adresse("25, rue des pas perdus " , "76000" , "Rouen"));
		serviceClient.saveOrUpdateClient(autreCli);
		
		Compte cptA = new Compte(null,"compte A",100.0);
		this.serviceCompte.saveOrUpdateCompte(cptA);
		
		Compte cptB = new Compte(null,"compte B",80.0);
		this.serviceCompte.saveOrUpdateCompte(cptB);
		
		Compte cptC = new Compte(null,"compte C",60.0);
		this.serviceCompte.saveOrUpdateCompte(cptC);
		
		Compte cptD = new Compte(null,"compte D",60.0);
		this.serviceCompte.saveOrUpdateCompte(cptD);
		
		serviceClient.ajouterComptePourClient(cli.getNumero(), cptA.getNumero());
		serviceClient.ajouterComptePourClient(cli.getNumero(), cptB.getNumero());
		
		serviceClient.ajouterComptePourClient(autreCli.getNumero(), cptC.getNumero());
		serviceClient.ajouterComptePourClient(autreCli.getNumero(), cptD.getNumero());
		//partie haute du test à reporter dans une initialisation de la partie web/jsf (ex: @PostConstruct)
		//------------------------
		//partie basse du test servant d'inspairation pour coder l'action d'un managedBean
		
		List<Compte> listeComptes = serviceClient.rechercherComptesDuClient(cli.getNumero());
		logger.info("client=" + cli);
		logger.info("comptes du client:" + listeComptes);
		Assert.assertTrue(listeComptes.size()==2);
		
	}
	
	
	@Test
	public void test_CRUD_Client_via_Service_deleguant_au_dao() {
			//1 . Ajouter entité en base
			Client cli = new Client(null,"Therieur","alain","0102030405" ,"alain.therieur@ici_ou_la.fr");
			serviceClient.saveOrUpdateClient(cli);
			Long numCli = cli.getNumero(); 
			//2.  Verifier entité ajoutée que l'on recharge depuis la base
			Client cliRelu = serviceClient.rechercherClientParNumero(numCli);
			Assert.assertTrue(cliRelu.getPrenom().equals("alain"));
			logger.info("apres ajout et relecture  , cliRelu="+cliRelu);
			//3.  modif , save() sur entité modifiée
			cliRelu.setPrenom("alex");
			serviceClient.saveOrUpdateClient(cliRelu);
			//4.  Rechargement et vérification mise à jour
			Client cliRelu2 = serviceClient.rechercherClientParNumero(numCli);
			Assert.assertTrue(cliRelu2.getPrenom().equals("alex"));
			logger.info("apres modif et relecture , cliRelu2="+cliRelu2);
		    //5 . supprimer l'entité en base
			serviceClient.supprimerClient(numCli);
			//6. Rechercher et vérifier que ça n'existe plus
			boolean existeEncore = (serviceClient.rechercherClientParNumero(numCli) != null) ;
			Assert.assertFalse(existeEncore);
	}
	
	
}
