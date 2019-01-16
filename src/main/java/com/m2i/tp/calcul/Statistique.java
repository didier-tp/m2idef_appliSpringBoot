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
		return 0.0;
	}
	
    public double average() {
		return 0.0;
	}
	
	
}
