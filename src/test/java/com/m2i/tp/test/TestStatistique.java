package com.m2i.tp.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.m2i.tp.calcul.Statistique;

/*
 * NB : Les traces System.out.println("...") ne sont placées ici
 *      que pour des raisons pédagogiques  : MONTRER LES INSTANCIATIONS AUTOMATIQUES EFFECTUEES PAR JUNIT4
 *      ==> SURTOUT PAS de System.out.println("...") SUR UN VRAI PROJET d'entreprise
 *      mais logger.debug() ou logger.trace() à la place.
 */
public class TestStatistique {
	
	private Statistique statistique; //composant à tester
	
	public TestStatistique() {
		System.out.println("instance de TestStatistique construite:" + this);
	}
	
	@Before
	public void initStatistique() {
		statistique = new Statistique();
		System.out.println("instance de Statistique inialisée:" + statistique);
	}
	
	@Test
	public void testSomme() {
		statistique.addValue(1);statistique.addValue(2);statistique.addValue(3);
		double sommeCalculee = statistique.sum();
		System.out.println("tva calculee:" + sommeCalculee + " via instance " + statistique);
		Assert.assertEquals(6.0,sommeCalculee,0.000001);
	}
	
	@Test
    public void testMoyenne() {
		statistique.addValue(1);statistique.addValue(3);
    	double moyenneCalculee = statistique.average();
    	System.out.println("moyenneCalculee calculee:" + moyenneCalculee + " via instance " + statistique);
		Assert.assertEquals(2.0,moyenneCalculee,0.000001);
	}


}
