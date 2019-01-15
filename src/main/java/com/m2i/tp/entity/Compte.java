package com.m2i.tp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity

//Compte.findComptesAvecSoldePositif permet de faire le rapprochement
//avec la méthode findComptesAvecSoldePositif du DaoCompte basé sur Spring-Data-jpa
@NamedQuery(name="Compte.findComptesAvecSoldePositif", 
             query="SELECT c FROM Compte c WHERE c.solde >= 0")
@Getter @Setter 
@ToString @NoArgsConstructor
public class Compte {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)//auto_increment mysql
	private Long numero;
	
	@Column(length=32) //VARCHAR(32) si table générée en fonction de java
	private String label;
	
	private Double solde;

	// +get/set , +toString() , + constructeurs avec et sans paramètres

	

	public Compte(Long numero, String label, Double solde) {
		super();
		this.numero = numero;
		this.label = label;
		this.solde = solde;
	}

	
	// ...

}
