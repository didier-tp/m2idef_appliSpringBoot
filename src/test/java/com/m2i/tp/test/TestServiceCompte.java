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
import com.m2i.tp.entity.Compte;
import com.m2i.tp.entity.Operation;
//org.junit = Junit4
import com.m2i.tp.service.ServiceCompte;


@RunWith(SpringRunner.class)
@SpringBootTest(classes= {MySpringBootApplication.class})

public class TestServiceCompte {
	
	private static Logger logger = LoggerFactory.getLogger(TestServiceCompte.class);
	
	@Autowired
	private ServiceCompte serviceCompte ; //à tester
	
	@Test
	public void test1_BonTransfert() {
		Compte cptA = new Compte(null,"compte A",100.0);
		this.serviceCompte.saveOrUpdateCompte(cptA);
		Long numCptA = cptA.getNumero();
		Compte cptB = new Compte(null,"compte B",80.0);
		this.serviceCompte.saveOrUpdateCompte(cptB);
		Long numCptB = cptB.getNumero();
		serviceCompte.transferer(50.0, numCptA, numCptB);
		Compte cptAApres = serviceCompte.rechercherCompteParNumero(numCptA);
		Compte cptBApres = serviceCompte.rechercherCompteParNumero(numCptB);
		System.out.println("apres transfert cptAApres="+cptAApres 
				          + " \n et cptBApres=" + cptBApres);
		Assert.assertEquals(50.0,cptAApres.getSolde(),0.001); //100 - 50 = 50
		Assert.assertEquals(130.0,cptBApres.getSolde(),0.001); //80 + 50 = 130
	}
	
	@Test
	public void test1Bis_MauvaisTransfert() {
		Compte cptA = new Compte(null,"compte A_Bis",100.0);
		this.serviceCompte.saveOrUpdateCompte(cptA);
		Long numCptA = cptA.getNumero();
		Compte cptB = new Compte(null,"compte B_Bis",80.0);
		this.serviceCompte.saveOrUpdateCompte(cptB);
		Long numCptB = cptB.getNumero();
		try {
			serviceCompte.transferer(50.0, numCptA, numCptB+999 /* qui existe pas */);
			Assert.fail("erreur : une exception aurait du remonter");
		} catch (Exception e) {
			//e.printStackTrace();
			System.out.println("echec transfert normal pour compte inexistant:" + e.getMessage());
		}
		Compte cptAApres = serviceCompte.rechercherCompteParNumero(numCptA);
		Compte cptBApres = serviceCompte.rechercherCompteParNumero(numCptB);
		System.out.println("apres transfert cptAApres="+cptAApres 
				          + " \n et cptBApres=" + cptBApres);
		Assert.assertEquals(100.0,cptAApres.getSolde(),0.001); //reste à 100 si rollback
		Assert.assertEquals(80.0,cptBApres.getSolde(),0.001); //reste 80 si rollback
	}
	
	@Test
	public void test2_AjoutCompte() {
		Compte cpt = new Compte(null,"compte 1",100.0);
		this.serviceCompte.saveOrUpdateCompte(cpt);
		Assert.assertTrue(cpt.getNumero()!=null);
		System.out.println("cpt="+cpt.toString());
	}
	
	@Test 
	public void test_operations_du_compte() {
		
		
		Compte cptZz = new Compte(null,"compte Zz",100.0);
		this.serviceCompte.saveOrUpdateCompte(cptZz);
		
		Compte cptWw = new Compte(null,"compte Ww",200.0);
		this.serviceCompte.saveOrUpdateCompte(cptWw);
		
		Operation op1 = new Operation("achat 1" , -30.60);
		serviceCompte.addOperation(cptZz.getNumero(),op1);
		
		Operation op2 = new Operation("achat 2" , -40.60);
		serviceCompte.addOperation(cptZz.getNumero(),op2);
		
		Operation op3 = new Operation("achat 3" , -30.60);
		serviceCompte.addOperation(cptWw.getNumero(),op3);
		
		//partie haute du test à reporter dans une initialisation de la partie web/jsf (ex: @PostConstruct)
		//------------------------
		//partie basse du test servant d'inspairation pour coder l'action d'un managedBean
		
		List<Operation> listeOperations= serviceCompte.operationsDuCompte(cptZz.getNumero());
		logger.info("compte=" + cptZz);
		logger.info("operations du compte:" + listeOperations);
		Assert.assertTrue(listeOperations.size()==2);
		
	}
	
	@Test
    public void test2() {
		Assert.assertTrue(1+1==2);
	}

}
