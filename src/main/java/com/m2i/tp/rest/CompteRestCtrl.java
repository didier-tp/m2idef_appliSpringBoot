package com.m2i.tp.rest;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.m2i.tp.entity.Compte;
import com.m2i.tp.service.ServiceCompte;

@RestController //classe de WS REST avec spring MVC (cas particulier de @Component)
@RequestMapping(value="/rest/compte" , headers="Accept=application/json")
public class CompteRestCtrl {
	
	@Autowired
	private ServiceCompte serviceCompte; //service métier à injecter/utiliser
	
	@PostConstruct //pour compenser base réinitialisée au démarrage en mode jpa "drop-and-create"
	public void initialiserJeuxDeDonneesEnModeDeveloppement() {
		//code idéalement délégué à sous composant utilitaire tenant compte d'un profile spring:
		serviceCompte.saveOrUpdateCompte(new Compte(null,"compte 1",100.0));
		serviceCompte.saveOrUpdateCompte(new Compte(null,"compte 2",200.0));
		serviceCompte.saveOrUpdateCompte(new Compte(null,"compte 3",-50.0));
	}
	
	//URL = http://localhost:8080/appliSpringBoot/rest/compte en POST
	//avec comme contenu invisible de la partie body de la requete HTTP
	//des données json { "numero" : null , "label" : "compteXy" , "solde" : 50.0 } 
	//           ou    { "numero" : 234 , "label" : "compteAbc" , "solde" : 70.0 }
	@RequestMapping(value="" , method=RequestMethod.POST)
	public Compte postCompte(@RequestBody Compte compte) {
		serviceCompte.saveOrUpdateCompte(compte);//quelquefois auto_increment 
		return compte; //en retour (copie des données en entrée)
		//pour confirmer la bonne prise en compte des nouvelles valeurs
		//et pour retourner la valeur du numero quelquefois auto incrémenté
	}
	
	//URL = http://localhost:8080/appliSpringBoot/rest/compte/1
	@RequestMapping(value="/{numCpt}" , method=RequestMethod.GET)
	public Compte getCompteByNum(@PathVariable("numCpt") Long numCpt) {
		return serviceCompte.rechercherCompteParNumero(numCpt);
	}
	
	//URL = http://localhost:8080/appliSpringBoot/rest/compte
	// ou   http://localhost:8080/appliSpringBoot/rest/compte?soldePositif=true
	@RequestMapping(value="" , method=RequestMethod.GET)
	public List<Compte> getComptesByCriteria(
			@RequestParam(name="soldePositif", required=false)Boolean soldePositif){
		if(soldePositif==null)
		    return serviceCompte.rechercherTousLesComptes();
		else {
			return serviceCompte.rechercherTousLesComptesPositifs();
		}
	}

}
