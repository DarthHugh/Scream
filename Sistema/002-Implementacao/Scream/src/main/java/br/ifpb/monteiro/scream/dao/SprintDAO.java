package br.ifpb.monteiro.scream.dao;

import br.ifpb.monteiro.scream.dao.facade.SprintDaoIF;
import br.ifpb.monteiro.scream.entities.Sprint;

public class SprintDAO extends GenericDAO<Sprint> implements SprintDaoIF {
	
	private static final long serialVersionUID = 1L;
	
	public SprintDAO(Class<Sprint> entityClass) {
		super(entityClass);
	}


}
