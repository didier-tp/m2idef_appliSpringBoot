package com.m2i.tp.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.m2i.tp.MySpringBootApplication;
import com.m2i.tp.dao.DaoCompte;
import com.m2i.tp.entity.Compte;


@RunWith(SpringRunner.class)
@SpringBootTest(classes= {MySpringBootApplication.class})

public class TestDaoCompte {
	
	@Autowired
	private DaoCompte daoCompte ; //à tester
	
	@Test
	public void testCRUD() {
		//1 . Ajouter entité en base
		Compte cpt = new Compte(null,"compte abc",70.0);
		daoCompte.save(cpt);
		Long numCpt = cpt.getNumero(); 
		//2.  Verifier entité ajoutée que l'on recharge depuis la base
		Compte cptRelu = daoCompte.findById(numCpt).get();
		Assert.assertTrue(cptRelu.getLabel().equals("compte abc"));
		System.out.println("apres ajout et relecture  , cptRelu="+cptRelu);
		//3.  modif , save() sur entité modifiée
		cptRelu.setSolde(90.0);
		daoCompte.save(cptRelu);
		//4.  Rechargement et vérification mise à jour
		Compte cptRelu2 = daoCompte.findById(numCpt).get();
		Assert.assertEquals(90.0,cptRelu2.getSolde(),0.01);
		System.out.println("apres modif et relecture , cptRelu2="+cptRelu2);
	    //5 . supprimer l'entité en base
		daoCompte.deleteById(numCpt);
		//6. Rechercher et vérifier que ça n'existe plus
		boolean existeEncore = daoCompte.findById(numCpt).isPresent();
		Assert.assertFalse(existeEncore);
	}
	
	@Test
	public void testFindByLabel() {
		daoCompte.save(new Compte(null,"compte x",50.0));
		daoCompte.save(new Compte(null,"compte y",60.0));
		daoCompte.save(new Compte(null,"compte x",90.0));
		List<Compte> listeComptes = daoCompte.findByLabel("compte x");
		Assert.assertTrue(listeComptes.size()>=2);
		System.out.println("comptes dont le label vaut compte x :" + listeComptes);
	}
	
	@Test
	public void testFindComptesAvecSoldePositif() {
		daoCompte.save(new Compte(null,"compte xx",-50.0));
		daoCompte.save(new Compte(null,"compte yy",60.0));
		daoCompte.save(new Compte(null,"compte zz",-90.0));
		daoCompte.save(new Compte(null,"compte xyz",70.0));
		List<Compte> listeComptes = daoCompte.findComptesAvecSoldePositif();
		Assert.assertTrue(listeComptes.size()>=2);
		System.out.println("comptes avec solde positif :" + listeComptes);
	}
}
