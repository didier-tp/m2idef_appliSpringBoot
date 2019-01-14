package com.m2i.tp.dao;

import java.util.List;

import com.m2i.tp.entity.Compte;

public interface DaoCompte {
	public Compte findByPrimaryKey(Long numero);

	public List<Compte> findAll();

	public void save(Compte cpt);

	public void delete(Long numero);
}
