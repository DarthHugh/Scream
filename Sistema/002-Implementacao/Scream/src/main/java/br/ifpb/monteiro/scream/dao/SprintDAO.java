package br.ifpb.monteiro.scream.dao;

import java.util.List;

import br.ifpb.monteiro.scream.entities.Projeto;
import br.ifpb.monteiro.scream.entities.Sprint;

/**
*
* @author Mauricio
*/
public class SprintDAO extends GenericDAO<Sprint>{
	
	private static final long serialVersionUID = 1L;
	
	public SprintDAO() {
		super(Sprint.class);
	}
	
	public List<Sprint> findByProjeto(Projeto entity){
		
		List<Sprint> sprints = query("Select sprint From Sprint sprint where sprint.projeto.id=?1 order by sprint.id", entity.getId());

//		List<Sprint> sprints= (List<Sprint>) getEntityManager().find(getEntity(), entity);
		return sprints;
	}

}
