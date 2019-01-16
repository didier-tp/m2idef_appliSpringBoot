package web.dev.only;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.m2i.tp.entity.Compte;
import com.m2i.tp.service.ServiceCompte;

@Component
public class InitDataSetInDevelopmentMode {
	
	@Autowired
	private ServiceCompte serviceCompte; //service métier à injecter/utiliser
	
	@PostConstruct //pour compenser base réinitialisée au démarrage en mode jpa "drop-and-create"
	public void initialiserJeuxDeDonneesEnModeDeveloppement() {
		//code déclenché seulement si profile spring "web.dev" activé
		System.out.println("init DataSet in web.dev profile only");
		serviceCompte.saveOrUpdateCompte(new Compte(null,"compte 1",100.0));
		serviceCompte.saveOrUpdateCompte(new Compte(null,"compte 2",200.0));
		serviceCompte.saveOrUpdateCompte(new Compte(null,"compte 3",-50.0));
	}

}
