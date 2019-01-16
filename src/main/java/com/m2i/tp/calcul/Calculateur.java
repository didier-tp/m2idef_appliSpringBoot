package com.m2i.tp.calcul;

//Code avec logique stateless (proche "static")
//testable avec @BeforeClass
public class Calculateur {
	
		public double tva(double ht , double tauxTva) {
			return ht * tauxTva / 100;
			//return 0.0;
		}
		
		public double ttc(double ht , double tauxTva) {
			return tva(ht,tauxTva) + ht;
			//return 0.0;
		}
}
