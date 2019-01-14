package com.m2i.tp.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.m2i.tp.MySpringBootApplication;
import com.m2i.tp.entity.Compte;
//org.junit = Junit4
import com.m2i.tp.service.ServiceCompte;


@RunWith(SpringRunner.class)
@SpringBootTest(classes= {MySpringBootApplication.class})

public class TestServiceCompte {
	
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
    public void test2() {
		Assert.assertTrue(1+1==2);
	}

}
