package com.m2i.tp.dao;

import java.util.List;

import com.m2i.tp.entity.Compte;

public interface DaoCompte {
	public Compte findCompteByNumero(Long numero);

	public List<Compte> findAllComptes();

	// ...
	public void updateCompte(Compte cpt);

	public void createCompte(Compte cpt);

	public void deleteCompte(Long numero);
}
