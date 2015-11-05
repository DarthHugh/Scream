package br.ifpb.monteiro.scream.dao;

import java.util.List;

import br.ifpb.monteiro.scream.entities.DefinicaoDePronto;
import br.ifpb.monteiro.scream.entities.Projeto;
import br.ifpb.monteiro.scream.entities.Projeto;
import br.ifpb.monteiro.scream.entities.enums.DefinicaoDeProntoEnum;

/**
 *
 * @author Mauricio
 */
public class DefinicaoDeProntoDAO extends GenericDAO<DefinicaoDePronto>{

	private static final long serialVersionUID = 1L;
	
	public DefinicaoDeProntoDAO() {
		super(DefinicaoDePronto.class);
	}

	@Override
	public void create(DefinicaoDePronto entity) {
		List<DefinicaoDePronto> definicoes = query("select definicao from DefinicaoDePronto definicao "
				+ "where definicao.projeto = ?1 AND definicao.tipoDefinicao = ?2", entity.getProjeto(),entity.getTipoDefinicao());

		if(definicoes.size()<1)
			super.create(entity);

	}  


	public DefinicaoDePronto findById(DefinicaoDeProntoEnum dPSE, Projeto entity) {

		List<DefinicaoDePronto> definicao = query("select definicao from DefinicaoDePronto "
				+ "definicao where definicao.projeto= ?1 AND definicao.tipoDefinicao = ?2", entity, dPSE);
		return definicao.get(0);
	}

    public List<DefinicaoDePronto> findByProjeto(Projeto entity){
    	
    	List<DefinicaoDePronto> definicaoDeProntos= (List<DefinicaoDePronto>) getEntityManager().find(getEntity(), entity);

		return definicaoDeProntos;
    	
    }
    
    
}
