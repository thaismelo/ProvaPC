package br.edu.ifpe.garanhuns.provapc.model;

import br.edu.ifpe.garanhuns.provapc.model.dao.RepositorioGenerico;



public interface ProfDao extends RepositorioGenerico<Professor, Integer>{
	public Professor recuperarLogin(String login);
}
