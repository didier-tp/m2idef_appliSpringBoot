package com.m2i.tp.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
@NamedQueries({
	@NamedQuery(name="Compte.findAll",query="SELECT c FROM Compte c"),
	@NamedQuery(name="Compte.findComptesAvecSoldePositif", query="SELECT c FROM Compte c WHERE c.solde >= 0"),
	@NamedQuery(name="Compte.findOperationsOfCompte",query="SELECT op FROM Compte cpt INNER JOIN cpt.operations op WHERE cpt.numero = ?1")
})
public class Compte {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)//auto_increment mysql
	private Long numero;
	
	@Column(length=32) //VARCHAR(32) si table générée en fonction de java
	private String label;
	
	private Double solde;


	@ManyToMany
	@JoinTable(name="ClientCompte",joinColumns={@JoinColumn(name="numCpt")},
	inverseJoinColumns={@JoinColumn(name="numCli")})
	@JsonIgnore
	private List<Client> proprietaires;
	
	@OneToMany(mappedBy="compte",cascade={CascadeType.ALL})
	@JsonIgnore
	private List<Operation> operations;
	

	public Compte(Long numero, String label, Double solde) {
		super();
		this.numero = numero;
		this.label = label;
		this.solde = solde;
	}
	
	public void debiter(double montant) {
		solde -= montant;
	}
	
	public void crediter(double montant) {
		solde += montant;
	}
		
	public void addOperation(Operation op){
		if(operations==null)
			operations=new ArrayList<Operation>();
		op.setCompte(this);
		operations.add(op);
	}

	@Override
	public String toString() {
		return "Compte [numero=" + numero + ", label=" + label + ", solde=" + solde + "]";
	}

	

}
