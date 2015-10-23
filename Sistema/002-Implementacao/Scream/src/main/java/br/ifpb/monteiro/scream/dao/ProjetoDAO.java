package br.ifpb.monteiro.scream.dao;

import javax.persistence.Query;

import br.ifpb.monteiro.scream.entities.Projeto;
import br.ifpb.monteiro.scream.util.jpa.Transactional;

/**
 *
 * @author Mauricio
 */
public class ProjetoDAO extends GenericDAO<Projeto>{

	private static final long serialVersionUID = 1L;

	public ProjetoDAO() {
		super(Projeto.class);
	}

	@Transactional
	@Override
	public void delete(Projeto entity) {
		if (getEntityManager().getTransaction().isActive()) {

		} else {

			getEntityManager().getTransaction().begin();

		}
//		Query queryAltera = getEntityManager().createQuery("UPDATE table definicao_pronto DROP CONSTRAINT fk_definicao_pronto_projeto_id");
//		queryAltera.executeUpdate();

		Query queryProjeto = getEntityManager().createNativeQuery("DELETE FROM projeto WHERE id = " + entity.getId());
		queryProjeto.executeUpdate();	

	}

}
