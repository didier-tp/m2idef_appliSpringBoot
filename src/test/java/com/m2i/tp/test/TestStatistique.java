package com.m2i.tp.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.m2i.tp.calcul.Statistique;

/*
 * NB : logger.debug() ou logger.trace() plutot que System.out.println("...")
 */
public class TestStatistique {
	
	private static Logger logger = LoggerFactory.getLogger(TestStatistique.class);
	
	private Statistique statistique; //composant à tester
	
	public TestStatistique() {
		logger.debug("instance de TestStatistique construite:" + this);
	}
	
	@Before
	public void initStatistique() {
		statistique = new Statistique();
		logger.debug("instance de Statistique inialisée:" + statistique);
	}
	
	@Test
	public void testSomme() {
		statistique.addValue(1);statistique.addValue(2);statistique.addValue(3);
		double sommeCalculee = statistique.sum();
		logger.debug("tva calculee:" + sommeCalculee + " via instance " + statistique);
		Assert.assertEquals(6.0,sommeCalculee,0.000001);
	}
	
	@Test
    public void testMoyenne() {
		statistique.addValue(1);statistique.addValue(3);
    	double moyenneCalculee = statistique.average();
    	logger.debug("moyenneCalculee calculee:" + moyenneCalculee + " via instance " + statistique);
		Assert.assertEquals(2.0,moyenneCalculee,0.000001);
	}


}
