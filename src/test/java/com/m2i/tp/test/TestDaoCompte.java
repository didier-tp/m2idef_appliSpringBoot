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
