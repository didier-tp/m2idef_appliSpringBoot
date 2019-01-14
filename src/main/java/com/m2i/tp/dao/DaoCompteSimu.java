package com.m2i.tp.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.m2i.tp.entity.Compte;

//@Repository //ou bien @Component
//id par défaut = "daoCompteSimu" (nom de la classe avec minuscule au début).
public class DaoCompteSimu implements DaoCompte {

	private Map<Long, Compte> mapComptes = new HashMap<Long, Compte>();
	private Long nbComptes = 0L;

	@Override
	public Compte findCompteByNumero(Long numero) {
		return mapComptes.get(numero);
	}

	@Override
	public void createCompte(Compte cpt) {
		// en entrée: cpt avec numero = null
		nbComptes++;// simuler auto_increment
		cpt.setNumero(nbComptes);
		mapComptes.put(cpt.getNumero(), cpt);
	}

	@Override
	public List<Compte> findAllComptes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateCompte(Compte cpt) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteCompte(Long numero) {
		// TODO Auto-generated method stub

	}

}
