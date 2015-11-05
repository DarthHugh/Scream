package br.ifpb.monteiro.scream.dao;

import br.ifpb.monteiro.scream.entities.Atividade;

public class AtividadeDAO extends GenericDAO<Atividade>{
	
	private static final long serialVersionUID = 1L;

	public AtividadeDAO(Class<Atividade> entityClass) {
		super(entityClass);
	}

}
