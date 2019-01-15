package com.m2i.tp.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.m2i.tp.entity.Compte;
import com.m2i.tp.service.ServiceCompte;

@RestController //classe de WS REST avec spring MVC (cas particulier de @Component)
@RequestMapping(value="/rest/compte" , headers="Accept=application/json")
public class CompteRestCtrl {
	
	@Autowired
	private ServiceCompte serviceCompte; //service métier à injecter/utiliser
	
	public List<Compte> getComptesByCriteria(){
		return serviceCompte.rechercherTousLesComptes();
	}

}
