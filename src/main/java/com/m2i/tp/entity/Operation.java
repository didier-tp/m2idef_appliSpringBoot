package com.m2i.tp.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="Operation")
@Getter @Setter @NoArgsConstructor
public class Operation {
	
	@Id
	@Column(name="numOp")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long numOp;
	private String label;
	private Double montant; //positif ou negatif
	private Date dateOp;
	
	@ManyToOne
	@JoinColumn(name="ref_compte")
	private Compte compte;
	
		
	@Override
	public String toString() {
		return "Operation [numero=" + numOp + ", label=" + label
				+ ", montant=" + montant + ", dateOp=" + dateOp + "]";
	}

		

	public Operation(String label, Double montant) {
		super();
		this.label = label;
		this.montant = montant;
		this.dateOp = new Date();
	}

	

}
