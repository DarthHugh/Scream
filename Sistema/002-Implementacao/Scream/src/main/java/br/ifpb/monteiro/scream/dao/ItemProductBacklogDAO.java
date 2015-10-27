/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifpb.monteiro.scream.dao;

import java.util.List;

import javax.persistence.Query;

import br.ifpb.monteiro.scream.entities.ItemProductBacklog;
import br.ifpb.monteiro.scream.util.jpa.Transactional;

/**
 *
 * @author Mauricio
 */
public class ItemProductBacklogDAO extends GenericDAO<ItemProductBacklog>{

	private static final long serialVersionUID = 1L;

	public ItemProductBacklogDAO() {
		super(ItemProductBacklog.class);
	}

	public List<ItemProductBacklog> findItemProduto(Long id){

		List<ItemProductBacklog> lisItemProductBacklogs= query("select item from ItemProductBacklog item where item.produto.id=?1 order by item.id", id);

		/*Query queryProduto = getEntityManager().createNativeQuery("select item from item_product_backlog item, produto produto where item.produto_id=" + id);

    	List<ItemProductBacklog> lisItemProductBacklogs= (List<ItemProductBacklog>) queryProduto.getResultList();*/

		return lisItemProductBacklogs;

	} 

	@Transactional
	@Override
	public void delete(ItemProductBacklog entity) {

		if (getEntityManager().getTransaction().isActive()) {

		} else {
			getEntityManager().getTransaction().begin();
		}
		Query queryProduto = getEntityManager().createNativeQuery("DELETE FROM item_product_backlog WHERE id = " + entity.getId());
		queryProduto.executeUpdate();
	}

}
