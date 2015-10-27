/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifpb.monteiro.scream.dao;

import java.util.List;

import javax.persistence.Query;

import br.ifpb.monteiro.scream.entities.ItemProductBacklog;
import br.ifpb.monteiro.scream.entities.TesteAceitacao;
import br.ifpb.monteiro.scream.util.jpa.Transactional;

/**
 *
 * @author Mauricio
 */
public class TesteAceitacaoDAO extends  GenericDAO<TesteAceitacao>{
	
	private static final long serialVersionUID = 1L;

	public TesteAceitacaoDAO() {
		super(TesteAceitacao.class);
	}    

	public List<TesteAceitacao> findTesteAceitacao(Long id){

		List<TesteAceitacao> listTesteAceitacao= query("select ta from TesteAceitacao ta where ta.itemProductBacklog.id=?1 order by ta.id", id);

		/*Query queryProduto = getEntityManager().createNativeQuery("select item from item_product_backlog item, produto produto where item.produto_id=" + id);

    	List<ItemProductBacklog> lisItemProductBacklogs= (List<ItemProductBacklog>) queryProduto.getResultList();*/

		return listTesteAceitacao;

	}
	
	@Transactional
	@Override
	public void delete(TesteAceitacao entity) {

		if (getEntityManager().getTransaction().isActive()) {

		} else {
			getEntityManager().getTransaction().begin();
		}
		Query queryProduto = getEntityManager().createNativeQuery("DELETE FROM teste_aceitacao WHERE id = " + entity.getId());
		queryProduto.executeUpdate();
	}

}
