package br.ifpb.monteiro.scream.dao;

import br.ifpb.monteiro.scream.dao.facade.AtividadeDaoIF;
import br.ifpb.monteiro.scream.entities.Atividade;

public class AtividadeDAO extends GenericDAO<Atividade> implements AtividadeDaoIF{
	
	private static final long serialVersionUID = 1L;

	public AtividadeDAO() {
		super(Atividade.class);
	}
	
}
