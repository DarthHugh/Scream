package br.ifpb.monteiro.scream.dao;

import java.util.List;

import javax.persistence.Query;

import br.ifpb.monteiro.scream.entities.Produto;
import br.ifpb.monteiro.scream.util.jpa.Transactional;

/**
 *
 * @author Markus
 */

public class ProdutoDAO extends GenericDAO<Produto>{

	private static final long serialVersionUID = 1L;

	public ProdutoDAO() {
		super(Produto.class);
	}

	@Transactional
	@Override
	public void delete(Produto entity) {
		
		if (getEntityManager().getTransaction().isActive()) {
		
		} else {
			
			getEntityManager().getTransaction().begin();
	
		}
		
		Query queryPro = getEntityManager().createNativeQuery("DELETE FROM teste_aceitacao USING item_product_backlog "
				+ "WHERE teste_aceitacao.item_product_backlog=item_product_backlog.id and item_product_backlog.produto_id = " + entity.getId());
		queryPro.executeUpdate();
		
		Query queryDef = getEntityManager().createNativeQuery("DELETE FROM item_product_backlog WHERE produto_id = " + entity.getId());
		queryDef.executeUpdate();
		
		Query queryProduto = getEntityManager().createNativeQuery("DELETE FROM produto WHERE id = " + entity.getId());
		queryProduto.executeUpdate();
	}
	

    public List<Produto> findAllProduto() {
		
		List<Produto> produtos = query("Select p From Produto p Order By p.id");
		
		return produtos;
    }
}
