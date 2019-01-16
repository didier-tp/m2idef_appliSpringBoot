package com.m2i.tp.calcul;

import java.util.ArrayList;
import java.util.List;

//Code avec logique stateful 
//testable avec @Before mais pas @BeforeClass
public class Statistique {
	private List<Double> serie= new ArrayList<Double>();
	
	public void addValue(double value) {
		this.serie.add(value);
	}
	
	public int nbValues() {
		return serie.size();
	}
	
	public double sum() {
		double s = 0.0;
		for(Double val:serie) {
			s+=val;
		}
		return s;
		//return 0.0;
	}
	
    public double average() {
    	int n = nbValues();
    	return (n==0)?0:sum()/n;
		//return 0.0;
	}
	
	
}
