package com.m2i.tp.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="Client")
@Getter @Setter @NoArgsConstructor
@NamedQueries({
	@NamedQuery(name="Client.findAll",query="SELECT c FROM Client c"),
	@NamedQuery(name="Client.findComptesOfClient",query="SELECT cpt FROM Client cli INNER JOIN cli.comptes cpt WHERE cli.numero = ?1")
})

public class Client {
	
	@Id
	@Column(name="numClient")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long numero;
	private String nom;
	private String prenom;

	private String telephone;
	private String email;
	//private String password;
	
	
	
    @Embedded
	private Adresse adresse;
	
	@ManyToMany(mappedBy="proprietaires")
	private List<Compte> comptes;
	
	
	@Override
	public String toString() {
		return "Client [numero=" + numero + ", nom=" + nom + ", prenom="
				+ prenom + ", adresse=" + adresse + ", telephone="
				+ telephone + ", email=" + email 
				+ "]";
	}


	public Client(Long numero, String nom, String prenom, String telephone, String email) {
		super();
		this.numero = numero;
		this.nom = nom;
		this.prenom = prenom;
		this.telephone = telephone;
		this.email = email;
	}
	
	
}
