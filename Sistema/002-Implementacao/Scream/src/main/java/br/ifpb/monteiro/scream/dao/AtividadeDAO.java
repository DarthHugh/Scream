package br.ifpb.monteiro.scream.dao;

import java.util.List;

import br.ifpb.monteiro.scream.dao.facade.AtividadeDaoIF;
import br.ifpb.monteiro.scream.entities.Atividade;

public class AtividadeDAO extends GenericDAO<Atividade> implements AtividadeDaoIF{
	
	private static final long serialVersionUID = 1L;

	public AtividadeDAO() {
		super(Atividade.class);
	}
	
	@Override
	public List<Atividade> findAll(){
		
		List<Atividade> atvList = query("Select atv From Atividade atv order By atv.id");
		
		return atvList;
	}
	
}
